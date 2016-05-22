package de.andrena.et2016.extremeFeedbackDevice.control.advanced.command.down;

import de.andrena.et2016.extremeFeedbackDevice.control.advanced.command.processor.CommandProcessor;
import de.andrena.et2016.extremeFeedbackDevice.control.advanced.targetter.TimeBasedTargetter;

public class DownProcessor implements CommandProcessor<DownCommand> {

	private TimeBasedTargetter launcher;

	public DownProcessor(TimeBasedTargetter launcher) {
		this.launcher = launcher;
	}

	@Override
	public void process(DownCommand command) {
		launcher.down(command.getTurnForMillis());
	}
}
