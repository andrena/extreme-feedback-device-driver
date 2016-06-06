package de.andrena.et2016.extremeFeedbackDevice.driver.dummy;

import de.andrena.et2016.extremeFeedbackDevice.driver.FeedbackDeviceFactoryRegistry;

public class DummyRegisteringConfig {
	public DummyRegisteringConfig(FeedbackDeviceFactoryRegistry registry) {
		registry.addFactory(new DummyFeedbackDeviceFactory());
	}
}
