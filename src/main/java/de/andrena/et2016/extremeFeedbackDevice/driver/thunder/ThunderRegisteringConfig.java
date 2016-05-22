package de.andrena.et2016.extremeFeedbackDevice.driver.thunder;

import de.andrena.et2016.extremeFeedbackDevice.driver.FeedbackDeviceFactory;
import de.andrena.et2016.extremeFeedbackDevice.driver.FeedbackDeviceFactoryRegistry;
import de.andrena.et2016.extremeFeedbackDevice.driver.FeedbackDeviceSingletonProxyFactory;
import de.andrena.et2016.extremeFeedbackDevice.driver.thunder.advanced.ThunderAdvancedControlConfig;
import de.andrena.et2016.extremeFeedbackDevice.driver.thunder.manual.ThunderManualControlConfig;
import de.andrena.et2016.extremeFeedbackDevice.driver.thunder.manual.ThunderManualControlMissileLauncherFeedbackDeviceFactory;

public class ThunderRegisteringConfig {
	public ThunderRegisteringConfig(FeedbackDeviceFactoryRegistry registry) {
		ThunderManualControlMissileLauncherFeedbackDeviceFactory manualControlDeviceFactory = new ThunderManualControlConfig()
				.createManualControlDeviceFactory();
		FeedbackDeviceFactory singletonManualControlDeviceFactory = new FeedbackDeviceSingletonProxyFactory(
				manualControlDeviceFactory);
		registry.addFactory(singletonManualControlDeviceFactory);

		FeedbackDeviceFactory advancedControlDeviceFactory = new ThunderAdvancedControlConfig()
				.createAdvancedControlDeviceFactory(singletonManualControlDeviceFactory);
		registry.addFactory(advancedControlDeviceFactory);
	}
}
