package de.andrena.et2016.extremeFeedbackDevice.driver.thunder.manual;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.rules.ExpectedException.none;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.usb.UsbControlIrp;
import javax.usb.UsbDevice;
import javax.usb.UsbDisconnectedException;
import javax.usb.UsbException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InOrder;
import org.mockito.stubbing.OngoingStubbing;

import de.andrena.et2016.extremeFeedbackDevice.control.advanced.launcher.MultiLauncherDeviceSpecification;

public class ThunderManualControlMissileLauncherTest {
	private static final byte REQUEST_TYPE = (byte) 0x21;
	private static final byte REQUEST = (byte) 0x09;
	private static final short REQUEST_INDEX = (short) 0;
	private static final short REQUEST_VALUE = (short) 0;

	@Rule
	public ExpectedException thrown = none();

	private UsbDevice usbDevice = mock(UsbDevice.class);
	private ThunderManualControlMissileLauncher thunderMissileLauncher = new ThunderManualControlMissileLauncher(
			usbDevice);
	private UsbControlIrp irp = mock(UsbControlIrp.class);

	@Test
	public void testStartDown() throws Exception {
		whenExpectedUsbControlIrpIsCreated().thenReturn(irp);

		thunderMissileLauncher.startDown();

		verifyCommandIsSynchronouslySubmittedWithCode(usbDevice, irp, (byte) 0x01);
	}

	@Test
	public void testStartDownPassesOnUsbDisconnectException() throws Exception {
		whenExpectedUsbControlIrpIsCreated().thenReturn(irp);
		UsbDisconnectedException usbException = new UsbDisconnectedException();
		doThrow(usbException).when(usbDevice)
				.syncSubmit(irp);

		thrown.expect(RuntimeException.class);
		thrown.expectMessage("Failed to send launcher command: USB syncSubmit failed");
		thrown.expectCause(is(usbException));
		thunderMissileLauncher.startDown();
	}

	@Test
	public void testStartUp() throws Exception {
		whenExpectedUsbControlIrpIsCreated().thenReturn(irp);

		thunderMissileLauncher.startUp();

		verifyCommandIsSynchronouslySubmittedWithCode(usbDevice, irp, (byte) 0x02);
	}

	@Test
	public void testStartLeft() throws Exception {
		whenExpectedUsbControlIrpIsCreated().thenReturn(irp);

		thunderMissileLauncher.startLeft();

		verifyCommandIsSynchronouslySubmittedWithCode(usbDevice, irp, (byte) 0x04);
	}

	@Test
	public void testRight() throws Exception {
		whenExpectedUsbControlIrpIsCreated().thenReturn(irp);

		thunderMissileLauncher.startRight();

		verifyCommandIsSynchronouslySubmittedWithCode(usbDevice, irp, (byte) 0x08);
	}

	@Test
	public void testFireOnce() throws Exception {
		whenExpectedUsbControlIrpIsCreated().thenReturn(irp);

		thunderMissileLauncher.fireOnce();

		verifyCommandIsSynchronouslySubmittedWithCode(usbDevice, irp, (byte) 0x10);
	}

	@Test
	public void testStop() throws Exception {
		whenExpectedUsbControlIrpIsCreated().thenReturn(irp);

		thunderMissileLauncher.stop();

		verifyCommandIsSynchronouslySubmittedWithCode(usbDevice, irp, (byte) 0x20);
	}

	@Test
	public void testDeviceHasInitialDelay() {
		MultiLauncherDeviceSpecification spec = thunderMissileLauncher.getMultiLauncherDeviceSpecification();

		assertThat(spec.minimumInitialStabilizationDelay(), is(600L));
	}

	@Test
	public void testDeviceHasFiringDelay() {
		MultiLauncherDeviceSpecification spec = thunderMissileLauncher.getMultiLauncherDeviceSpecification();

		assertThat(spec.minimumFiringDelay(), is(3100L));
	}

	private void verifyCommandIsSynchronouslySubmittedWithCode(UsbDevice usbDevice, UsbControlIrp irp, byte commandCode)
			throws UsbException {
		InOrder inOrder = inOrder(irp, usbDevice);
		inOrder.verify(irp)
				.setData(new byte[] { 0x02, commandCode, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 });
		inOrder.verify(usbDevice)
				.syncSubmit(irp);
	}

	private OngoingStubbing<UsbControlIrp> whenExpectedUsbControlIrpIsCreated() {
		return when(usbDevice.createUsbControlIrp(REQUEST_TYPE, REQUEST, REQUEST_VALUE, REQUEST_INDEX));
	}
}
