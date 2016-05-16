package de.andrena.et2016.extremeFeedbackDevice.driver;

import javax.usb.UsbDevice;

public class ThunderMissileLauncherFactory {
	public ThunderMissileLauncher create(UsbDevice device) {
		return new ThunderMissileLauncher(device);
	}
}
