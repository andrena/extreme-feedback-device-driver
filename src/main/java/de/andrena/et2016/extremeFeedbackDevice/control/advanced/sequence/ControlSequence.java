package de.andrena.et2016.extremeFeedbackDevice.control.advanced.sequence;

import java.util.ArrayList;
import java.util.List;

import de.andrena.et2016.extremeFeedbackDevice.control.advanced.command.Command;

public class ControlSequence {
	private List<Command> commands = new ArrayList<>();

	public List<Command> getCommands() {
		return commands;
	}

	public ControlSequence addCommand(Command command) {
		commands.add(command);
		return this;
	}
}
