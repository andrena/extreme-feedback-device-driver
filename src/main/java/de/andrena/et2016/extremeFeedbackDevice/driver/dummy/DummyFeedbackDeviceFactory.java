package de.andrena.et2016.extremeFeedbackDevice.driver.dummy;

import java.util.Optional;

import de.andrena.et2016.extremeFeedbackDevice.control.advanced.AdvancedControlMissileLauncher;
import de.andrena.et2016.extremeFeedbackDevice.driver.FeedbackDeviceFactory;

public class DummyFeedbackDeviceFactory implements FeedbackDeviceFactory {
	@SuppressWarnings("unchecked")
	@Override
	public <T> Optional<T> findFeedbackDeviceSupportingControlInterface(Class<T> supportedInterface) {
		if (!AdvancedControlMissileLauncher.class.equals(supportedInterface))
			return Optional.empty();
		return Optional.of((T) new DummyAdvancedControlMissileLauncher());
	}
}