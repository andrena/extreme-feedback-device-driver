package de.andrena.et2016.extremeFeedbackDevice.driver;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FeedbackDeviceSingletonProxyFactory implements FeedbackDeviceFactory {
	private FeedbackDeviceFactory innerFactory;
	private Map<Class<?>, Object> cache = new HashMap<>();

	public FeedbackDeviceSingletonProxyFactory(FeedbackDeviceFactory innerFactory) {
		this.innerFactory = innerFactory;
	}

	@Override
	public <T> Optional<T> findFeedbackDeviceSupportingControlInterface(Class<T> supportedInterface) {
		@SuppressWarnings("unchecked")
		T cachedValue = (T) cache.get(supportedInterface);
		if (cachedValue != null) {
			return Optional.of(cachedValue);
		}
		Optional<T> newValue = innerFactory.findFeedbackDeviceSupportingControlInterface(supportedInterface);
		if (newValue.isPresent()) {
			cache.put(supportedInterface, newValue.get());
			return newValue;
		}
		return Optional.empty();
	}
}
