package de.andrena.et2016.extremeFeedbackDevice.control.advanced.command.fire;

import com.fasterxml.jackson.annotation.JsonTypeName;

import de.andrena.et2016.extremeFeedbackDevice.control.advanced.command.Command;

@JsonTypeName("fire")
public class FireCommand implements Command {

	private int numberOfShots;

	public FireCommand() {
	}

	public FireCommand(int numberOfShots) {
		this.numberOfShots = numberOfShots;
	}

	public void setNumberOfShots(int numberOfShots) {
		this.numberOfShots = numberOfShots;
	}

	public int getNumberOfShots() {
		return numberOfShots;
	}
}
