package de.andrena.et2016.extremeFeedbackDevice.driver.thunder.advanced;

import java.util.Optional;

import de.andrena.et2016.extremeFeedbackDevice.control.advanced.AdvancedControlMissileLauncher;
import de.andrena.et2016.extremeFeedbackDevice.control.advanced.ManualToAdvancedMissileLauncherAdapterFactory;
import de.andrena.et2016.extremeFeedbackDevice.control.manual.ManualControlMissileLauncher;
import de.andrena.et2016.extremeFeedbackDevice.driver.FeedbackDeviceFactory;

public class ThunderAdvancedControlMissileLauncherFeedbackDeviceFactory implements FeedbackDeviceFactory {

	private FeedbackDeviceFactory manualFactory;
	private ManualToAdvancedMissileLauncherAdapterFactory adapterFactory;

	public ThunderAdvancedControlMissileLauncherFeedbackDeviceFactory(FeedbackDeviceFactory manualFactory,
			ManualToAdvancedMissileLauncherAdapterFactory adapterFactory) {
		this.manualFactory = manualFactory;
		this.adapterFactory = adapterFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> Optional<T> findFeedbackDeviceSupportingControlInterface(Class<T> supportedInterface) {
		if (!AdvancedControlMissileLauncher.class.equals(supportedInterface)) {
			return Optional.empty();
		}
		Optional<ManualControlMissileLauncher> manualDevice = manualFactory
				.findFeedbackDeviceSupportingControlInterface(ManualControlMissileLauncher.class);
		if (!manualDevice.isPresent())
			return Optional.empty();
		return Optional.of((T) adapterFactory.createFrom(manualDevice.get()));
	}
}
