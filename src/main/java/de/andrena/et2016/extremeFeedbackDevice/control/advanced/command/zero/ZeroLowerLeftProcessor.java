package de.andrena.et2016.extremeFeedbackDevice.control.advanced.command.zero;

import de.andrena.et2016.extremeFeedbackDevice.control.advanced.command.processor.CommandProcessor;
import de.andrena.et2016.extremeFeedbackDevice.control.advanced.targetter.TimeBasedTargetter;

public class ZeroLowerLeftProcessor implements CommandProcessor<ZeroLowerLeftCommand> {

	private TimeBasedTargetter launcher;

	public ZeroLowerLeftProcessor(TimeBasedTargetter launcher) {
		this.launcher = launcher;
	}

	@Override
	public void process(ZeroLowerLeftCommand command) {
		launcher.left(6000L);
		launcher.down(1500L);
	}
}
