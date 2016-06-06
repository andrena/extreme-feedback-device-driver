package de.andrena.et2016.extremeFeedbackDevice.driver.thunder.advanced;

import de.andrena.et2016.extremeFeedbackDevice.control.advanced.ManualToAdvancedMissileLauncherAdapterFactory;
import de.andrena.et2016.extremeFeedbackDevice.driver.FeedbackDeviceFactory;
import de.andrena.et2016.extremeFeedbackDevice.util.Sleeper;
import de.andrena.et2016.extremeFeedbackDevice.util.ThreadSleeper;

public class ThunderAdvancedControlConfig {
	public ThunderAdvancedControlMissileLauncherFeedbackDeviceFactory createAdvancedControlDeviceFactory(
			FeedbackDeviceFactory manualControlDeviceFactory) {
		Sleeper sleeper = new ThreadSleeper();
		ManualToAdvancedMissileLauncherAdapterFactory adapterFactory = new ManualToAdvancedMissileLauncherAdapterFactory(
				sleeper);
		return new ThunderAdvancedControlMissileLauncherFeedbackDeviceFactory(manualControlDeviceFactory,
				adapterFactory);
	}
}
