package de.andrena.et2016.extremeFeedbackDevice.control.advanced.launcher;

import de.andrena.et2016.extremeFeedbackDevice.control.manual.SingleLauncher;
import de.andrena.et2016.extremeFeedbackDevice.util.Sleeper;

public class SingleToMultiLauncherAdapter implements MultiLauncher {

	private SingleLauncher singleLauncher;
	private Sleeper sleeper;
	private MultiLauncherDeviceSpecification specification;

	public SingleToMultiLauncherAdapter(SingleLauncher singleLauncher, Sleeper sleeper,
			MultiLauncherDeviceSpecification specification) {
		this.singleLauncher = singleLauncher;
		this.sleeper = sleeper;
		this.specification = specification;
	}

	@Override
	public void fire(int numberOfShots) {
		sleeper.sleep(specification.minimumInitialStabilizationDelay());
		for (int i = 0; i < numberOfShots; ++i) {
			singleLauncher.fireOnce();
			sleeper.sleep(specification.minimumFiringDelay());
		}
	}
}
