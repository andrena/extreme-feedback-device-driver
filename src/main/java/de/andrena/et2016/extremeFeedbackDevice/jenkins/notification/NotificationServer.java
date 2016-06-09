package de.andrena.et2016.extremeFeedbackDevice.jenkins.notification;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;

import javax.net.ServerSocketFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.andrena.et2016.extremeFeedbackDevice.control.advanced.CommandSequenceExecutor;
import de.andrena.et2016.extremeFeedbackDevice.jenkins.culprit.Culprits;

public class NotificationServer {
	public static void main(String[] args) {
		int portNumber = Integer.parseInt(args[0]);
		File controlSequencesDefinitionFile = new File(args[1]);
		CommandSequenceExecutor commandSequenceExecutor = new CommandSequenceExecutor(controlSequencesDefinitionFile);
		ServerSocketFactory serverSockerFactory = ServerSocketFactory.getDefault();

		try (ServerSocket serverSocket = serverSockerFactory.createServerSocket()) {
			serverSocket.setReuseAddress(true);
			serverSocket.bind(new InetSocketAddress(portNumber));
			System.out.println("Listening on port " + portNumber + " ...");

			while (true) {
				try (Socket clientSocket = serverSocket.accept()) {
					System.out.println(
							"New connection from " + clientSocket.getInetAddress() + ":" + clientSocket.getPort());

					ObjectMapper objectMapper = new ObjectMapper();
					Notification notification = objectMapper.readValue(clientSocket.getInputStream(),
							Notification.class);
					String status = notification.getBuild()
							.getStatus();
					System.out.println("Status: " + status);
					System.out.println("Full URL: " + notification.getBuild()
							.getFullUrl());

					if ("SUCCESS".equals(status) || "ABORTED".equals(status)) {
						System.out.println("Skipping retaliation");
						continue;
					}
					URL culpritsUrl = new URL(notification.getBuild()
							.getFullUrl() + "/api/json?pretty=true&tree=culprits[fullName]");
					System.out.println("Requesting culprits from " + culpritsUrl + " ...");
					Culprits culprits = objectMapper.readValue(culpritsUrl, Culprits.class);
					System.out.println("Culprits: " + culprits.getFullNames());
					for (String culprit : culprits.getFullNames()) {
						System.out.println("Retaliating against " + culprit);
						commandSequenceExecutor.runControlSequence(culprit);
					}
				} catch (IOException e) {
					System.err.println(e.toString());
				}
			}
		} catch (IOException e) {
			System.err.println("Error listening on port " + portNumber + ": " + e.toString());
		}
	}
}
