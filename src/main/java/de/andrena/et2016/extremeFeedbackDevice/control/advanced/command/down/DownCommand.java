package de.andrena.et2016.extremeFeedbackDevice.control.advanced.command.down;

import de.andrena.et2016.extremeFeedbackDevice.control.advanced.command.Command;
import de.andrena.et2016.extremeFeedbackDevice.control.advanced.command.TimeBasedMoveCommand;

public class DownCommand extends TimeBasedMoveCommand implements Command {
	public DownCommand() {
	}

	public DownCommand(long turnForMillis) {
		super(turnForMillis);
	}
}
