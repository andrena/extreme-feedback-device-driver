package de.andrena.et2016.extremeFeedbackDevice.control.advanced.adapter;

import de.andrena.et2016.extremeFeedbackDevice.driver.FeedbackDeviceFactory;
import de.andrena.et2016.extremeFeedbackDevice.driver.FeedbackDeviceFactoryRegistry;
import de.andrena.et2016.extremeFeedbackDevice.util.Sleeper;
import de.andrena.et2016.extremeFeedbackDevice.util.ThreadSleeper;

public class ManualToAdvancedMissileLauncherAdapterConfig {
	public ManualToAdvancedMissileLauncherAdapterConfig(FeedbackDeviceFactoryRegistry registry,
			FeedbackDeviceFactory factory) {
		registry.addFactory(createAdvancedControlDeviceFactory(factory));
	}

	public ManualToAdvancedMissileLauncherAdapterFeedbackDeviceFactory createAdvancedControlDeviceFactory(
			FeedbackDeviceFactory manualControlDeviceFactory) {
		Sleeper sleeper = new ThreadSleeper();
		ManualToAdvancedMissileLauncherAdapterFactory adapterFactory = new ManualToAdvancedMissileLauncherAdapterFactory(
				sleeper);
		return new ManualToAdvancedMissileLauncherAdapterFeedbackDeviceFactory(manualControlDeviceFactory,
				adapterFactory);
	}
}
