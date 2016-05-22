package de.andrena.et2016.extremeFeedbackDevice.driver.thunder.manual;

import java.util.Optional;

import javax.usb.UsbDevice;

import de.andrena.et2016.extremeFeedbackDevice.control.manual.ManualControlMissileLauncher;
import de.andrena.et2016.extremeFeedbackDevice.driver.FeedbackDeviceFactory;

public class ThunderManualControlMissileLauncherFeedbackDeviceFactory implements FeedbackDeviceFactory {

	private ThunderMissileLauncherUsbDeviceLocator locator;
	private ThunderManualControlMissileLauncherFactory factory;

	public ThunderManualControlMissileLauncherFeedbackDeviceFactory(ThunderMissileLauncherUsbDeviceLocator locator,
			ThunderManualControlMissileLauncherFactory factory) {
		this.locator = locator;
		this.factory = factory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> Optional<T> findFeedbackDeviceSupportingControlInterface(Class<T> supportedInterface) {
		if (!ManualControlMissileLauncher.class.equals(supportedInterface)) {
			return Optional.empty();
		}
		Optional<UsbDevice> usbDevice = locator.findAndClaimMissileLauncher();
		if (!usbDevice.isPresent()) {
			return Optional.empty();
		}
		return Optional.of((T) factory.create(usbDevice.get()));
	}
}
