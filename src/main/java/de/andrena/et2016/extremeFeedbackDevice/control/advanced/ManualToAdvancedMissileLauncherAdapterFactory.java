package de.andrena.et2016.extremeFeedbackDevice.control.advanced;

import de.andrena.et2016.extremeFeedbackDevice.control.advanced.launcher.SingleToMultiLauncherAdapterFactory;
import de.andrena.et2016.extremeFeedbackDevice.control.advanced.targetter.TimeBasedToStartStopTargettingAdapterFactory;
import de.andrena.et2016.extremeFeedbackDevice.control.manual.ManualControlMissileLauncher;

public class ManualToAdvancedMissileLauncherAdapterFactory {
	private TimeBasedToStartStopTargettingAdapterFactory timeBasedAdapterFactory;
	private SingleToMultiLauncherAdapterFactory multiLauncherAdapterFactory;

	public ManualToAdvancedMissileLauncherAdapterFactory(
			TimeBasedToStartStopTargettingAdapterFactory timeBasedAdapterFactory,
			SingleToMultiLauncherAdapterFactory multiLauncherAdapterFactory) {
		this.timeBasedAdapterFactory = timeBasedAdapterFactory;
		this.multiLauncherAdapterFactory = multiLauncherAdapterFactory;
	}

	public ManualToAdvancedMissileLauncherAdapter createFrom(
			ManualControlMissileLauncher manualControlMissileLauncher) {
		return new ManualToAdvancedMissileLauncherAdapter(timeBasedAdapterFactory, multiLauncherAdapterFactory,
				manualControlMissileLauncher);
	}
}
