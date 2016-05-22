package de.andrena.et2016.extremeFeedbackDevice.control.advanced.launcher;

import de.andrena.et2016.extremeFeedbackDevice.control.manual.SingleLauncher;

public class SingleToMultiLauncherAdapterFactory {
	public SingleToMultiLauncherAdapter createFor(SingleLauncher singleLauncher) {
		return new SingleToMultiLauncherAdapter(singleLauncher);
	}
}
