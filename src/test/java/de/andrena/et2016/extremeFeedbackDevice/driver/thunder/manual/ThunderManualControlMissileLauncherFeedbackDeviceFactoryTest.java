package de.andrena.et2016.extremeFeedbackDevice.driver.thunder.manual;

import static java.util.Optional.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import java.util.Optional;

import javax.usb.UsbDevice;

import org.junit.Before;
import org.junit.Test;

import de.andrena.et2016.extremeFeedbackDevice.control.manual.ManualControlMissileLauncher;

public class ThunderManualControlMissileLauncherFeedbackDeviceFactoryTest {
	private interface UnsupportedInterface {

	}

	private ThunderMissileLauncherUsbDeviceLocator locator = mock(ThunderMissileLauncherUsbDeviceLocator.class);
	private ThunderManualControlMissileLauncherFactory factory = mock(ThunderManualControlMissileLauncherFactory.class);
	private ThunderManualControlMissileLauncherFeedbackDeviceFactory feedbackDeviceFactory = new ThunderManualControlMissileLauncherFeedbackDeviceFactory(
			locator, factory);

	@Before
	public void setUpLocator() {
		when(locator.findAndClaimMissileLauncher()).thenReturn(empty());
	}

	@Test
	public void testRejectsUnsupportedInterface() throws Exception {
		Optional<UnsupportedInterface> feedbackDevice = feedbackDeviceFactory
				.findFeedbackDeviceSupportingControlInterface(UnsupportedInterface.class);

		assertThat(feedbackDevice.isPresent(), is(false));
		verifyZeroInteractions(locator);
	}

	@Test
	public void testPassesOnRequestForManualControlMissileLauncher() throws Exception {
		Optional<ManualControlMissileLauncher> feedbackDevice = feedbackDeviceFactory
				.findFeedbackDeviceSupportingControlInterface(ManualControlMissileLauncher.class);

		assertThat(feedbackDevice.isPresent(), is(false));
	}

	@Test
	public void testCreatesInstance() throws Exception {
		UsbDevice expectedUsbDevice = mock(UsbDevice.class);
		when(locator.findAndClaimMissileLauncher()).thenReturn(Optional.of(expectedUsbDevice));
		ThunderManualControlMissileLauncher expectedLauncher = mock(ThunderManualControlMissileLauncher.class);
		when(factory.create(expectedUsbDevice)).thenReturn(expectedLauncher);
		Optional<ManualControlMissileLauncher> feedbackDevice = feedbackDeviceFactory
				.findFeedbackDeviceSupportingControlInterface(ManualControlMissileLauncher.class);

		assertThat(feedbackDevice.isPresent(), is(true));
		assertThat(feedbackDevice.get(), is(expectedLauncher));
	}
}
