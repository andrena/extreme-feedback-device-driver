package de.andrena.et2016.extremeFeedbackDevice.control.advanced.command.up;

import de.andrena.et2016.extremeFeedbackDevice.control.advanced.command.Command;
import de.andrena.et2016.extremeFeedbackDevice.control.advanced.command.TimeBasedMoveCommand;

public class UpCommand extends TimeBasedMoveCommand implements Command {
	public UpCommand() {
	}

	public UpCommand(long turnForMillis) {
		super(turnForMillis);
	}
}
