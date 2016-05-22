package de.andrena.et2016.extremeFeedbackDevice.control.advanced.command.right;

import de.andrena.et2016.extremeFeedbackDevice.control.advanced.command.processor.CommandProcessor;
import de.andrena.et2016.extremeFeedbackDevice.control.advanced.targetter.TimeBasedTargetter;

public class RightProcessor implements CommandProcessor<RightCommand> {

	private TimeBasedTargetter launcher;

	public RightProcessor(TimeBasedTargetter launcher) {
		this.launcher = launcher;
	}

	@Override
	public void process(RightCommand command) {
		launcher.right(command.getTurnForMillis());
	}
}
