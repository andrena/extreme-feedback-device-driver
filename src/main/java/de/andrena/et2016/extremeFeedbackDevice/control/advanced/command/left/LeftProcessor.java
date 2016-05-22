package de.andrena.et2016.extremeFeedbackDevice.control.advanced.command.left;

import de.andrena.et2016.extremeFeedbackDevice.control.advanced.command.processor.CommandProcessor;
import de.andrena.et2016.extremeFeedbackDevice.control.advanced.targetter.TimeBasedTargetter;

public class LeftProcessor implements CommandProcessor<LeftCommand> {

	private TimeBasedTargetter launcher;

	public LeftProcessor(TimeBasedTargetter launcher) {
		this.launcher = launcher;
	}

	@Override
	public void process(LeftCommand command) {
		launcher.left(command.getTurnForMillis());
	}
}
