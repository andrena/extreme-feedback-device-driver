package de.andrena.et2016.extremeFeedbackDevice.control.advanced.sequence;

import java.util.List;

import de.andrena.et2016.extremeFeedbackDevice.control.advanced.command.Command;
import de.andrena.et2016.extremeFeedbackDevice.control.advanced.command.processor.CommandProcessors;

public class ControlSequenceProcessor {

	private CommandProcessors commandProcessor;

	public ControlSequenceProcessor(CommandProcessors commandProcessor) {
		this.commandProcessor = commandProcessor;
	}

	public void processSequence(ControlSequence controlSequence) {
		List<Command> commands = controlSequence.getCommands();
		for (Command command : commands) {
			commandProcessor.processCommand(command);
		}
	}
}
