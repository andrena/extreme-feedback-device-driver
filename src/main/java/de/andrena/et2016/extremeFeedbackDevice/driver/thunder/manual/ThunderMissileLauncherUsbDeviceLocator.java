package de.andrena.et2016.extremeFeedbackDevice.driver.thunder.manual;

import java.util.List;
import java.util.Optional;

import javax.usb.UsbConfiguration;
import javax.usb.UsbDevice;
import javax.usb.UsbDeviceDescriptor;
import javax.usb.UsbDisconnectedException;
import javax.usb.UsbException;
import javax.usb.UsbHub;
import javax.usb.UsbInterface;
import javax.usb.UsbInterfacePolicy;
import javax.usb.UsbNotActiveException;
import javax.usb.UsbServices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThunderMissileLauncherUsbDeviceLocator {
	private static Logger log = LoggerFactory.getLogger(ThunderMissileLauncherUsbDeviceLocator.class);

	private static final short VENDOR_ID = 0x2123;
	private static final short PRODUCT_ID = 0x1010;

	private UsbServices usbServices;

	public ThunderMissileLauncherUsbDeviceLocator(UsbServices usbServices) {
		this.usbServices = usbServices;
	}

	public Optional<UsbDevice> findAndClaimMissileLauncher() {
		UsbHub rootUsbHub;
		try {
			rootUsbHub = usbServices.getRootUsbHub();
		} catch (SecurityException | UsbException e) {
			log.warn("Could not retrieve USB root hub", e);
			return Optional.empty();
		}
		return findAndClaimMissileLauncherOnHub(rootUsbHub);
	}

	@SuppressWarnings("unchecked")
	private Optional<UsbDevice> findAndClaimMissileLauncherOnHub(UsbHub hub) {
		for (UsbDevice device : (List<UsbDevice>) hub.getAttachedUsbDevices()) {
			if (device.isUsbHub()) {
				Optional<UsbDevice> innerDevice = findAndClaimMissileLauncherOnHub((UsbHub) device);
				if (innerDevice.isPresent()) {
					return innerDevice;
				}
			} else {
				UsbDeviceDescriptor desc = device.getUsbDeviceDescriptor();
				if (desc.idVendor() == VENDOR_ID && desc.idProduct() == PRODUCT_ID) {
					UsbConfiguration configuration = device.getUsbConfiguration((byte) 1);
					UsbInterface iface = configuration.getUsbInterface((byte) 0);
					try {
						iface.claim(new UsbInterfacePolicy() {
							@Override
							public boolean forceClaim(UsbInterface usbInterface) {
								return true;
							}
						});
					} catch (UsbNotActiveException | UsbDisconnectedException | UsbException e) {
						log.warn("Could not claim USB device", e);
						continue;
					}

					return Optional.of(device);
				}
			}
		}
		return Optional.empty();
	}
}
