package de.andrena.et2016.extremeFeedbackDevice.control.advanced.launcher;

import de.andrena.et2016.extremeFeedbackDevice.control.manual.SingleLauncher;
import de.andrena.et2016.extremeFeedbackDevice.util.Sleeper;

public class SingleToMultiLauncherAdapter implements MultiLauncher {

	private static final long FIRING_DELAY = 3100L;
	private static final long INITIAL_STABILIZATION = 600L;
	private SingleLauncher singleLauncher;
	private Sleeper sleeper;

	public SingleToMultiLauncherAdapter(SingleLauncher singleLauncher, Sleeper sleeper) {
		this.singleLauncher = singleLauncher;
		this.sleeper = sleeper;
	}

	@Override
	public void fire(int numberOfShots) {
		sleeper.sleep(INITIAL_STABILIZATION);
		for (int i = 0; i < numberOfShots; ++i) {
			singleLauncher.fireOnce();
			sleeper.sleep(FIRING_DELAY);
		}
	}
}
