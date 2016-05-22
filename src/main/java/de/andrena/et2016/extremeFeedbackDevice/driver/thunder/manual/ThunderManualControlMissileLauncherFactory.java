package de.andrena.et2016.extremeFeedbackDevice.driver.thunder.manual;

import javax.usb.UsbDevice;

public class ThunderManualControlMissileLauncherFactory {
	public ThunderManualControlMissileLauncher create(UsbDevice device) {
		return new ThunderManualControlMissileLauncher(device);
	}
}
