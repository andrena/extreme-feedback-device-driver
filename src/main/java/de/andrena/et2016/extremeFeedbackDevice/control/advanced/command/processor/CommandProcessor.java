package de.andrena.et2016.extremeFeedbackDevice.control.advanced.command.processor;

import de.andrena.et2016.extremeFeedbackDevice.control.advanced.command.Command;

public interface CommandProcessor<T extends Command> {

	void process(T command);
}
