package de.andrena.et2016.extremeFeedbackDevice.control.advanced.command.right;

import com.fasterxml.jackson.annotation.JsonTypeName;

import de.andrena.et2016.extremeFeedbackDevice.control.advanced.command.Command;
import de.andrena.et2016.extremeFeedbackDevice.control.advanced.command.TimeBasedMoveCommand;

@JsonTypeName("right")
public class RightCommand extends TimeBasedMoveCommand implements Command {
	public RightCommand() {
	}

	public RightCommand(long turnForMillis) {
		super(turnForMillis);
	}
}
