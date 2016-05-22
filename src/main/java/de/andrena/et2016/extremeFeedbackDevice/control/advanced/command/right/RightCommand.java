package de.andrena.et2016.extremeFeedbackDevice.control.advanced.command.right;

import de.andrena.et2016.extremeFeedbackDevice.control.advanced.command.Command;
import de.andrena.et2016.extremeFeedbackDevice.control.advanced.command.TimeBasedMoveCommand;

public class RightCommand extends TimeBasedMoveCommand implements Command {
	public RightCommand() {
	}

	public RightCommand(long turnForMillis) {
		super(turnForMillis);
	}
}
