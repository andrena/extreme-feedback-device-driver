package de.andrena.et2016.extremeFeedbackDevice.driver.thunder.advanced;

import de.andrena.et2016.extremeFeedbackDevice.control.advanced.ManualToAdvancedMissileLauncherAdapterFactory;
import de.andrena.et2016.extremeFeedbackDevice.control.advanced.launcher.SingleToMultiLauncherAdapterFactory;
import de.andrena.et2016.extremeFeedbackDevice.control.advanced.targetter.TimeBasedToStartStopTargettingAdapterFactory;
import de.andrena.et2016.extremeFeedbackDevice.driver.FeedbackDeviceFactory;
import de.andrena.et2016.extremeFeedbackDevice.util.Sleeper;
import de.andrena.et2016.extremeFeedbackDevice.util.ThreadSleeper;

public class ThunderAdvancedControlConfig {
	public ThunderAdvancedControlMissileLauncherFeedbackDeviceFactory createAdvancedControlDeviceFactory(
			FeedbackDeviceFactory manualControlDeviceFactory) {
		ManualToAdvancedMissileLauncherAdapterFactory adapterFactory = createManualToAdvancedControlAdapterFactory();
		return new ThunderAdvancedControlMissileLauncherFeedbackDeviceFactory(manualControlDeviceFactory,
				adapterFactory);
	}

	private ManualToAdvancedMissileLauncherAdapterFactory createManualToAdvancedControlAdapterFactory() {
		Sleeper sleeper = new ThreadSleeper();
		TimeBasedToStartStopTargettingAdapterFactory timeBasedAdapterFactory = new TimeBasedToStartStopTargettingAdapterFactory(
				sleeper);
		SingleToMultiLauncherAdapterFactory multiLauncherAdapterFactory = new SingleToMultiLauncherAdapterFactory();
		return new ManualToAdvancedMissileLauncherAdapterFactory(timeBasedAdapterFactory, multiLauncherAdapterFactory);
	}
}
