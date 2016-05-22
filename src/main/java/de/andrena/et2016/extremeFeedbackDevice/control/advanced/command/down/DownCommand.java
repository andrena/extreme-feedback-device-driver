package de.andrena.et2016.extremeFeedbackDevice.control.advanced.command.down;

import com.fasterxml.jackson.annotation.JsonTypeName;

import de.andrena.et2016.extremeFeedbackDevice.control.advanced.command.Command;
import de.andrena.et2016.extremeFeedbackDevice.control.advanced.command.TimeBasedMoveCommand;

@JsonTypeName("down")
public class DownCommand extends TimeBasedMoveCommand implements Command {
	public DownCommand() {
	}

	public DownCommand(long turnForMillis) {
		super(turnForMillis);
	}
}
