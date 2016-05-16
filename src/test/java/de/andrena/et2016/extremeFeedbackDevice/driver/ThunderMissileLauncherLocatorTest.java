package de.andrena.et2016.extremeFeedbackDevice.driver;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import javax.usb.UsbDevice;
import javax.usb.UsbDeviceDescriptor;
import javax.usb.UsbException;
import javax.usb.UsbHub;
import javax.usb.UsbServices;

import org.junit.Test;

public class ThunderMissileLauncherLocatorTest {
	private static final short VENDOR_ID = 0x2123;
	private static final short OTHER_VENDOR_ID = 0x42;
	private static final short PRODUCT_ID = 0x1010;
	private static final short OTHER_PRODUCT_ID = 0x42;

	private UsbServices usbServices = mock(UsbServices.class);
	private ThunderMissileLauncherFactory factory = mock(ThunderMissileLauncherFactory.class);
	private ThunderMissileLauncherLocator locator = new ThunderMissileLauncherLocator(usbServices, factory);

	@Test
	public void testRootHubNotAvailable() throws Exception {
		when(usbServices.getRootUsbHub()).thenThrow(new UsbException());

		Optional<MissileLauncher> missileLauncher = locator.findMissileLauncher();

		assertThat(missileLauncher.isPresent(), is(false));
	}

	@Test
	public void testMatchingDeviceIsFound() throws Exception {
		UsbHub rootHub = mock(UsbHub.class);
		when(usbServices.getRootUsbHub()).thenReturn(rootHub);
		UsbDevice usbDevice = mock(UsbDevice.class);
		when(rootHub.getAttachedUsbDevices()).thenReturn(Arrays.asList(usbDevice));
		UsbDeviceDescriptor descriptor = mock(UsbDeviceDescriptor.class);
		when(usbDevice.getUsbDeviceDescriptor()).thenReturn(descriptor);
		when(descriptor.idVendor()).thenReturn(VENDOR_ID);
		when(descriptor.idProduct()).thenReturn(PRODUCT_ID);
		ThunderMissileLauncher expectedLauncher = mock(ThunderMissileLauncher.class);
		when(factory.create(usbDevice)).thenReturn(expectedLauncher);

		Optional<MissileLauncher> missileLauncher = locator.findMissileLauncher();

		assertThat(missileLauncher.isPresent(), is(true));
		assertThat(missileLauncher.get(), is(expectedLauncher));
	}

	@Test
	public void testNoDeviceIsFound() throws Exception {
		UsbHub rootHub = mock(UsbHub.class);
		when(usbServices.getRootUsbHub()).thenReturn(rootHub);
		when(rootHub.getAttachedUsbDevices()).thenReturn(Collections.emptyList());

		Optional<MissileLauncher> missileLauncher = locator.findMissileLauncher();

		assertThat(missileLauncher.isPresent(), is(false));
	}

	@Test
	public void testMatchingDeviceIsFoundOnChildHub() throws Exception {
		UsbHub rootHub = mock(UsbHub.class);
		when(usbServices.getRootUsbHub()).thenReturn(rootHub);
		UsbHub childHub = mock(UsbHub.class);
		when(childHub.isUsbHub()).thenReturn(true);
		when(rootHub.getAttachedUsbDevices()).thenReturn(Arrays.asList(childHub));
		UsbDevice usbDevice = mock(UsbDevice.class);
		when(childHub.getAttachedUsbDevices()).thenReturn(Arrays.asList(usbDevice));
		UsbDeviceDescriptor descriptor = mock(UsbDeviceDescriptor.class);
		when(usbDevice.getUsbDeviceDescriptor()).thenReturn(descriptor);
		when(descriptor.idVendor()).thenReturn(VENDOR_ID);
		when(descriptor.idProduct()).thenReturn(PRODUCT_ID);
		ThunderMissileLauncher expectedLauncher = mock(ThunderMissileLauncher.class);
		when(factory.create(usbDevice)).thenReturn(expectedLauncher);

		Optional<MissileLauncher> missileLauncher = locator.findMissileLauncher();

		assertThat(missileLauncher.isPresent(), is(true));
		assertThat(missileLauncher.get(), is(expectedLauncher));

	}

	@Test
	public void testNoDeviceIsFoundOnChildHub() throws Exception {
		UsbHub rootHub = mock(UsbHub.class);
		when(usbServices.getRootUsbHub()).thenReturn(rootHub);
		UsbHub childHub = mock(UsbHub.class);
		when(childHub.isUsbHub()).thenReturn(true);
		when(rootHub.getAttachedUsbDevices()).thenReturn(Arrays.asList(childHub));
		when(childHub.getAttachedUsbDevices()).thenReturn(Collections.emptyList());

		Optional<MissileLauncher> missileLauncher = locator.findMissileLauncher();

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

		Optional<MissileLauncher> missileLauncher = locator.findMissileLauncher();

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

		Optional<MissileLauncher> missileLauncher = locator.findMissileLauncher();

		assertThat(missileLauncher.isPresent(), is(false));
	}
}
