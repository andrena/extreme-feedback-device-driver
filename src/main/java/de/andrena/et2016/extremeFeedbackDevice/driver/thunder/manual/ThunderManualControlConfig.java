package de.andrena.et2016.extremeFeedbackDevice.driver.thunder.manual;

import javax.usb.UsbException;
import javax.usb.UsbHostManager;
import javax.usb.UsbServices;

public class ThunderManualControlConfig {
	public ThunderManualControlMissileLauncherFeedbackDeviceFactory createManualControlDeviceFactory() {
		ThunderMissileLauncherUsbDeviceLocator usbDeviceLocator = createUsbDeviceLocator();
		ThunderManualControlMissileLauncherFactory factory = new ThunderManualControlMissileLauncherFactory();
		return new ThunderManualControlMissileLauncherFeedbackDeviceFactory(usbDeviceLocator, factory);
	}

	private ThunderMissileLauncherUsbDeviceLocator createUsbDeviceLocator() {
		UsbServices usbServices = getUsbServices();
		return new ThunderMissileLauncherUsbDeviceLocator(usbServices);
	}

	private UsbServices getUsbServices() {
		try {
			return UsbHostManager.getUsbServices();
		} catch (SecurityException | UsbException e) {
			throw new RuntimeException("Could not initialize USB services", e);
		}
	}
}
