package de.andrena.et2016.extremeFeedbackDevice.control.advanced.command.left;

import de.andrena.et2016.extremeFeedbackDevice.control.advanced.command.Command;
import de.andrena.et2016.extremeFeedbackDevice.control.advanced.command.TimeBasedMoveCommand;

public class LeftCommand extends TimeBasedMoveCommand implements Command {
	public LeftCommand() {
	}

	public LeftCommand(long turnForMillis) {
		super(turnForMillis);
	}
}
