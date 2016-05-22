package de.andrena.et2016.extremeFeedbackDevice.control.advanced.command.processor;

import de.andrena.et2016.extremeFeedbackDevice.control.advanced.command.Command;

public interface CommandProcessorRegistry {
	<T extends Command> void register(Class<T> commandClass, CommandProcessor<T> processor);
}