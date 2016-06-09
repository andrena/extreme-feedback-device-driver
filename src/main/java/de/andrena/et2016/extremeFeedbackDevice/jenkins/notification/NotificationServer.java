package de.andrena.et2016.extremeFeedbackDevice.jenkins.notification;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;

import javax.net.ServerSocketFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.andrena.et2016.extremeFeedbackDevice.control.advanced.CommandSequenceExecutor;
import de.andrena.et2016.extremeFeedbackDevice.jenkins.culprit.Culprits;

public class NotificationServer {
	private static final Logger log = LoggerFactory.getLogger(NotificationServer.class);

	public static void main(String[] args) {
		int portNumber = Integer.parseInt(args[0]);
		File controlSequencesDefinitionFile = new File(args[1]);
		CommandSequenceExecutor commandSequenceExecutor = new CommandSequenceExecutor(controlSequencesDefinitionFile);
		ServerSocketFactory serverSockerFactory = ServerSocketFactory.getDefault();

		try (ServerSocket serverSocket = serverSockerFactory.createServerSocket()) {
			serverSocket.setReuseAddress(true);
			serverSocket.bind(new InetSocketAddress(portNumber));
			log.info("Listening on port " + portNumber + " ...");

			while (true) {
				try (Socket clientSocket = serverSocket.accept()) {
					log.info("New connection from " + clientSocket.getInetAddress() + ":" + clientSocket.getPort());

					ObjectMapper objectMapper = new ObjectMapper();
					Notification notification = objectMapper.readValue(clientSocket.getInputStream(),
							Notification.class);
					String status = notification.getBuild()
							.getStatus();
					log.info("Status: " + status);
					log.info("Full URL: " + notification.getBuild()
							.getFullUrl());

					if ("SUCCESS".equals(status) || "ABORTED".equals(status)) {
						log.info("Skipping retaliation");
						continue;
					}
					URL culpritsUrl = new URL(notification.getBuild()
							.getFullUrl() + "/api/json?pretty=true&tree=culprits[fullName]");
					log.info("Requesting culprits from " + culpritsUrl + " ...");
					Culprits culprits = objectMapper.readValue(culpritsUrl, Culprits.class);
					log.info("Culprits: " + culprits.getFullNames());
					for (String culprit : culprits.getFullNames()) {
						log.info("Retaliating against " + culprit);
						commandSequenceExecutor.runControlSequence(culprit);
					}
				} catch (IOException e) {
					log.error(e.toString());
				}
			}
		} catch (IOException e) {
			log.error("Error listening on port " + portNumber + ": " + e.toString());
		}
	}
}
