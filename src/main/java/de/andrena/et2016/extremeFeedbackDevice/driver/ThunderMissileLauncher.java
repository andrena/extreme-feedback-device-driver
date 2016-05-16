package de.andrena.et2016.extremeFeedbackDevice.driver;

import javax.usb.UsbConst;
import javax.usb.UsbControlIrp;
import javax.usb.UsbDevice;
import javax.usb.UsbDisconnectedException;
import javax.usb.UsbException;

public class ThunderMissileLauncher implements MissileLauncher {
	private enum Command {
		DOWN(0x01), UP(0x02), LEFT(0x04), RIGHT(0x08), FIRE(0x10), STOP(0x20);

		private byte opCode;

		private Command(int opCode) {
			this.opCode = (byte) opCode;
		}
	}

	private UsbDevice device;

	public ThunderMissileLauncher(UsbDevice device) {
		this.device = device;
	}

	private void sendMessage(byte[] message) {
		UsbControlIrp irp = device.createUsbControlIrp(
				(byte) (UsbConst.REQUESTTYPE_TYPE_CLASS | UsbConst.REQUESTTYPE_RECIPIENT_INTERFACE), (byte) 0x09,
				(short) 0, (short) 0);
		irp.setData(message);
		try {
			device.syncSubmit(irp);
		} catch (IllegalArgumentException | UsbDisconnectedException | UsbException e) {
			throw new RuntimeException("Failed to send launcher command: USB syncSubmit failed", e);
		}
	}

	private void sendCommand(Command command) {
		byte[] message = new byte[] { 0x02, command.opCode, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 };
		sendMessage(message);
	}

	@Override
	public void startLeft() {
		sendCommand(Command.LEFT);
	}

	@Override
	public void startRight() {
		sendCommand(Command.RIGHT);
	}

	@Override
	public void startDown() {
		sendCommand(Command.DOWN);
	}

	@Override
	public void startUp() {
		sendCommand(Command.UP);
	}

	@Override
	public void stop() {
		sendCommand(Command.STOP);
	}

	@Override
	public void fireOnce() {
		sendCommand(Command.FIRE);
	}
}