package de.andrena.et2016.extremeFeedbackDevice.driver;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.usb.UsbDevice;
import javax.usb.UsbDeviceDescriptor;
import javax.usb.UsbException;
import javax.usb.UsbHub;
import javax.usb.UsbServices;

public class ThunderMissileLauncherLocator implements MissileLauncherFactory {
	private static Logger LOG = Logger.getLogger(ThunderMissileLauncherLocator.class.getName());
	private static final short VENDOR_ID = 0x2123;
	private static final short PRODUCT_ID = 0x1010;

	private UsbServices usbServices;
	private ThunderMissileLauncherFactory factory;

	public ThunderMissileLauncherLocator(UsbServices usbServices, ThunderMissileLauncherFactory factory) {
		this.usbServices = usbServices;
		this.factory = factory;
	}

	@Override
	public Optional<MissileLauncher> findMissileLauncher() {
		UsbHub rootUsbHub;
		try {
			rootUsbHub = usbServices.getRootUsbHub();
		} catch (SecurityException | UsbException e) {
			LOG.log(Level.WARNING, "Could not retrieve USB root hub", e);
			return Optional.empty();
		}
		return findMissileLauncherOnHub(rootUsbHub);
	}

	@SuppressWarnings("unchecked")
	private Optional<MissileLauncher> findMissileLauncherOnHub(UsbHub hub) {
		for (UsbDevice device : (List<UsbDevice>) hub.getAttachedUsbDevices()) {
			if (device.isUsbHub()) {
				Optional<MissileLauncher> launcher = findMissileLauncherOnHub((UsbHub) device);
				if (launcher.isPresent()) {
					return launcher;
				}
			} else {
				UsbDeviceDescriptor desc = device.getUsbDeviceDescriptor();
				if (desc.idVendor() == VENDOR_ID && desc.idProduct() == PRODUCT_ID) {
					return Optional.of(factory.create(device));
				}
			}
		}
		return Optional.empty();
	}
}
