package de.andrena.et2016.extremeFeedbackDevice.control.advanced.command.fire;

import de.andrena.et2016.extremeFeedbackDevice.control.advanced.command.processor.CommandProcessor;
import de.andrena.et2016.extremeFeedbackDevice.control.advanced.launcher.MultiLauncher;

public class FireProcessor implements CommandProcessor<FireCommand> {

	private MultiLauncher launcher;

	public FireProcessor(MultiLauncher launcher) {
		this.launcher = launcher;
	}

	@Override
	public void process(FireCommand command) {
		launcher.fire(command.getNumberOfShots());
	}
}
