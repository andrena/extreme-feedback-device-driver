package de.andrena.et2016.extremeFeedbackDevice.control.advanced.command.left;

import com.fasterxml.jackson.annotation.JsonTypeName;

import de.andrena.et2016.extremeFeedbackDevice.control.advanced.command.Command;
import de.andrena.et2016.extremeFeedbackDevice.control.advanced.command.TimeBasedMoveCommand;

@JsonTypeName("left")
public class LeftCommand extends TimeBasedMoveCommand implements Command {
	public LeftCommand() {
	}

	public LeftCommand(long turnForMillis) {
		super(turnForMillis);
	}
}
