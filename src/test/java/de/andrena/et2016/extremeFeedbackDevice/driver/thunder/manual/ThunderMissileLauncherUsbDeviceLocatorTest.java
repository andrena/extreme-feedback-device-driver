package de.andrena.et2016.extremeFeedbackDevice.driver.thunder.manual;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentCaptor.forClass;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import javax.usb.UsbConfiguration;
import javax.usb.UsbDevice;
import javax.usb.UsbDeviceDescriptor;
import javax.usb.UsbException;
import javax.usb.UsbHub;
import javax.usb.UsbInterface;
import javax.usb.UsbInterfacePolicy;
import javax.usb.UsbServices;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

public class ThunderMissileLauncherUsbDeviceLocatorTest {
	private static final short VENDOR_ID = 0x2123;
	private static final short OTHER_VENDOR_ID = 0x42;
	private static final short PRODUCT_ID = 0x1010;
	private static final short OTHER_PRODUCT_ID = 0x42;

	private UsbServices usbServices = mock(UsbServices.class);
	private ThunderMissileLauncherUsbDeviceLocator locator = new ThunderMissileLauncherUsbDeviceLocator(usbServices);

	private class MatchingDevice {
		UsbDevice device = mock(UsbDevice.class);
		UsbDeviceDescriptor descriptor = mock(UsbDeviceDescriptor.class);
		UsbConfiguration configuration = mock(UsbConfiguration.class);
		UsbInterface usbInterface = mock(UsbInterface.class);

		MatchingDevice(UsbHub rootHub) {
			when(device.getUsbDeviceDescriptor()).thenReturn(descriptor);
			when(descriptor.idVendor()).thenReturn(VENDOR_ID);
			when(descriptor.idProduct()).thenReturn(PRODUCT_ID);
			when(device.getUsbConfiguration((byte) 1)).thenReturn(configuration);
			when(configuration.getUsbInterface((byte) 0)).thenReturn(usbInterface);
		}

		public void assertConfiguration() throws Exception {
			ArgumentCaptor<UsbInterfacePolicy> policyCaptor = forClass(UsbInterfacePolicy.class);
			verify(usbInterface).claim(policyCaptor.capture());
			assertThat(policyCaptor.getValue()
					.forceClaim(usbInterface), is(true));
		}
	}

	@Test
	public void testRootHubNotAvailable() throws Exception {
		when(usbServices.getRootUsbHub()).thenThrow(new UsbException());

		Optional<UsbDevice> missileLauncher = locator.findAndClaimMissileLauncher();

		assertThat(missileLauncher.isPresent(), is(false));
	}

	@Test
	public void testMatchingDeviceIsFoundAndClaimed() throws Exception {
		UsbHub rootHub = mock(UsbHub.class);
		when(usbServices.getRootUsbHub()).thenReturn(rootHub);
		MatchingDevice matchingUsbDevice = new MatchingDevice(rootHub);
		when(rootHub.getAttachedUsbDevices()).thenReturn(asList(matchingUsbDevice.device));

		Optional<UsbDevice> missileLauncher = locator.findAndClaimMissileLauncher();

		assertThat(missileLauncher.isPresent(), is(true));
		assertThat(missileLauncher.get(), is(matchingUsbDevice.device));
		matchingUsbDevice.assertConfiguration();
	}

	@Test
	public void testMatchingDeviceIsSkippedIfClaimFails() throws Exception {
		UsbHub rootHub = mock(UsbHub.class);
		when(usbServices.getRootUsbHub()).thenReturn(rootHub);
		MatchingDevice matchingUsbDevice = new MatchingDevice(rootHub);
		when(rootHub.getAttachedUsbDevices()).thenReturn(asList(matchingUsbDevice.device));
		doThrow(new UsbException()).when(matchingUsbDevice.usbInterface)
				.claim(any(UsbInterfacePolicy.class));

		Optional<UsbDevice> missileLauncher = locator.findAndClaimMissileLauncher();

		assertThat(missileLauncher.isPresent(), is(false));
	}

	@Test
	public void testNoDeviceIsFound() throws Exception {
		UsbHub rootHub = mock(UsbHub.class);
		when(usbServices.getRootUsbHub()).thenReturn(rootHub);
		when(rootHub.getAttachedUsbDevices()).thenReturn(Collections.emptyList());

		Optional<UsbDevice> missileLauncher = locator.findAndClaimMissileLauncher();

		assertThat(missileLauncher.isPresent(), is(false));
	}

	@Test
	public void testMatchingDeviceIsFoundOnChildHub() throws Exception {
		UsbHub rootHub = mock(UsbHub.class);
		when(usbServices.getRootUsbHub()).thenReturn(rootHub);
		UsbHub childHub = mock(UsbHub.class);
		when(childHub.isUsbHub()).thenReturn(true);
		when(rootHub.getAttachedUsbDevices()).thenReturn(Arrays.asList(childHub));
		MatchingDevice matchingUsbDevice = new MatchingDevice(rootHub);
		when(childHub.getAttachedUsbDevices()).thenReturn(Arrays.asList(matchingUsbDevice.device));

		Optional<UsbDevice> missileLauncher = locator.findAndClaimMissileLauncher();

		assertThat(missileLauncher.isPresent(), is(true));
		assertThat(missileLauncher.get(), is(matchingUsbDevice.device));
		matchingUsbDevice.assertConfiguration();
	}

	@Test
	public void testNoDeviceIsFoundOnChildHub() throws Exception {
		UsbHub rootHub = mock(UsbHub.class);
		when(usbServices.getRootUsbHub()).thenReturn(rootHub);
		UsbHub childHub = mock(UsbHub.class);
		when(childHub.isUsbHub()).thenReturn(true);
		when(rootHub.getAttachedUsbDevices()).thenReturn(Arrays.asList(childHub));
		when(childHub.getAttachedUsbDevices()).thenReturn(Collections.emptyList());

		Optional<UsbDevice> missileLauncher = locator.findAndClaimMissileLauncher();

		assertThat(missileLauncher.isPresent(), is(false));
	}

	@Test
	public void testNonMatchingVendorIdCausesDeviceToNotBeFound() throws Exception {
		UsbHub rootHub = mock(UsbHub.class);
		when(usbServices.getRootUsbHub()).thenReturn(rootHub);
		UsbDevice usbDevice = mock(UsbDevice.class);
		when(rootHub.getAttachedUsbDevices()).thenReturn(Arrays.asList(usbDevice));
		UsbDeviceDescriptor descriptor = mock(UsbDeviceDescriptor.class);
		when(usbDevice.getUsbDeviceDescriptor()).thenReturn(descriptor);
		when(descriptor.idVendor()).thenReturn(OTHER_VENDOR_ID);
		when(descriptor.idProduct()).thenReturn(PRODUCT_ID);

		Optional<UsbDevice> missileLauncher = locator.findAndClaimMissileLauncher();

		assertThat(missileLauncher.isPresent(), is(false));
	}

	@Test
	public void testNonMatchingProductIdCausesDeviceToNotBeFound() throws Exception {
		UsbHub rootHub = mock(UsbHub.class);
		when(usbServices.getRootUsbHub()).thenReturn(rootHub);
		UsbDevice usbDevice = mock(UsbDevice.class);
		when(rootHub.getAttachedUsbDevices()).thenReturn(Arrays.asList(usbDevice));
		UsbDeviceDescriptor descriptor = mock(UsbDeviceDescriptor.class);
		when(usbDevice.getUsbDeviceDescriptor()).thenReturn(descriptor);
		when(descriptor.idVendor()).thenReturn(VENDOR_ID);
		when(descriptor.idProduct()).thenReturn(OTHER_PRODUCT_ID);

		Optional<UsbDevice> missileLauncher = locator.findAndClaimMissileLauncher();

		assertThat(missileLauncher.isPresent(), is(false));
	}
}
