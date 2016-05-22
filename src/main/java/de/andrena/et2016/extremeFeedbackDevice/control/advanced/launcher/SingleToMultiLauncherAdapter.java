package de.andrena.et2016.extremeFeedbackDevice.control.advanced.launcher;

import de.andrena.et2016.extremeFeedbackDevice.control.manual.SingleLauncher;

public class SingleToMultiLauncherAdapter implements MultiLauncher {

	private SingleLauncher singleLauncher;

	public SingleToMultiLauncherAdapter(SingleLauncher singleLauncher) {
		this.singleLauncher = singleLauncher;
	}

	@Override
	public void fire(int numberOfShots) {
		for (int i = 0; i < numberOfShots; ++i) {
			singleLauncher.fireOnce();
		}
	}
}
