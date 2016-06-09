package de.andrena.et2016.extremeFeedbackDevice.control.advanced.adapter;

import de.andrena.et2016.extremeFeedbackDevice.control.advanced.launcher.SingleToMultiLauncherAdapter;
import de.andrena.et2016.extremeFeedbackDevice.control.advanced.targetter.TimeBasedTargetter;
import de.andrena.et2016.extremeFeedbackDevice.control.advanced.targetter.TimeBasedToStartStopTargettingAdapter;
import de.andrena.et2016.extremeFeedbackDevice.control.manual.ManualControlMissileLauncher;
import de.andrena.et2016.extremeFeedbackDevice.util.Sleeper;

public class ManualToAdvancedMissileLauncherAdapterFactory {
	private Sleeper sleeper;

	public ManualToAdvancedMissileLauncherAdapterFactory(Sleeper sleeper) {
		this.sleeper = sleeper;
	}

	public ManualToAdvancedMissileLauncherAdapter createFrom(
			ManualControlMissileLauncher manualControlMissileLauncher) {
		TimeBasedTargetter timeBasedTargettingAdapter = new TimeBasedToStartStopTargettingAdapter(
				manualControlMissileLauncher, sleeper);
		SingleToMultiLauncherAdapter multiLauncherAdapter = new SingleToMultiLauncherAdapter(
				manualControlMissileLauncher, sleeper);
		return new ManualToAdvancedMissileLauncherAdapter(timeBasedTargettingAdapter, multiLauncherAdapter);
	}
}
