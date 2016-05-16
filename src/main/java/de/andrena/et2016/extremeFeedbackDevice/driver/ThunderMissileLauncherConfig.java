package de.andrena.et2016.extremeFeedbackDevice.driver;

import javax.usb.UsbException;
import javax.usb.UsbHostManager;
import javax.usb.UsbServices;

public class ThunderMissileLauncherConfig {
	public ThunderMissileLauncherConfig(MissileLauncherFactoryRegistry registry) {
		ThunderMissileLauncherLocator locator = new ThunderMissileLauncherLocator(getUsbServices(),
				new ThunderMissileLauncherFactory());
		registry.addFactory(locator);
	}

	private UsbServices getUsbServices() {
		try {
			return UsbHostManager.getUsbServices();
		} catch (SecurityException | UsbException e) {
			throw new RuntimeException("Could not initialize USB services", e);
		}
	}
}
