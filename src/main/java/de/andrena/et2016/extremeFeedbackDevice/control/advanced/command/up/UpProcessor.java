package de.andrena.et2016.extremeFeedbackDevice.control.advanced.command.up;

import de.andrena.et2016.extremeFeedbackDevice.control.advanced.command.processor.CommandProcessor;
import de.andrena.et2016.extremeFeedbackDevice.control.advanced.targetter.TimeBasedTargetter;

public class UpProcessor implements CommandProcessor<UpCommand> {

	private TimeBasedTargetter launcher;

	public UpProcessor(TimeBasedTargetter launcher) {
		this.launcher = launcher;
	}

	@Override
	public void process(UpCommand command) {
		launcher.up(command.getTurnForMillis());
	}
}
