package de.andrena.et2016.extremeFeedbackDevice.driver.dummy;

import java.util.Optional;

import de.andrena.et2016.extremeFeedbackDevice.control.manual.ManualControlMissileLauncher;
import de.andrena.et2016.extremeFeedbackDevice.driver.FeedbackDeviceFactory;

public class DummyFeedbackDeviceFactory implements FeedbackDeviceFactory {
	@SuppressWarnings("unchecked")
	@Override
	public <T> Optional<T> findFeedbackDeviceSupportingControlInterface(Class<T> supportedInterface) {
		if (!ManualControlMissileLauncher.class.equals(supportedInterface))
			return Optional.empty();
		return Optional.of((T) new DummyManualControlMissileLauncher());
	}
}