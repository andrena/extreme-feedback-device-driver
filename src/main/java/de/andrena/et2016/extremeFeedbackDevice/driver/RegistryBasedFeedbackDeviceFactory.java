package de.andrena.et2016.extremeFeedbackDevice.driver;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RegistryBasedFeedbackDeviceFactory implements FeedbackDeviceFactory, FeedbackDeviceFactoryRegistry {

	private List<FeedbackDeviceFactory> factories = new ArrayList<>();

	@Override
	public <T> Optional<T> findFeedbackDeviceSupportingControlInterface(Class<T> supportedInterface) {
		for (FeedbackDeviceFactory factory : factories) {
			Optional<T> result = factory.findFeedbackDeviceSupportingControlInterface(supportedInterface);
			if (result.isPresent())
				return result;
		}
		return Optional.empty();
	}

	@Override
	public void addFactory(FeedbackDeviceFactory factory) {
		this.factories.add(factory);
	}
}
