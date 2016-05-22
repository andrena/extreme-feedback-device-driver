package de.andrena.et2016.extremeFeedbackDevice.driver;

import java.util.Optional;

public interface FeedbackDeviceFactory {
	<T> Optional<T> findFeedbackDeviceSupportingControlInterface(Class<T> supportedInterface);
}
