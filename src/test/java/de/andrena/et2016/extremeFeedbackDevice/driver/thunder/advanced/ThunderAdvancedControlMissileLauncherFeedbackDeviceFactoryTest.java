package de.andrena.et2016.extremeFeedbackDevice.driver.thunder.advanced;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import de.andrena.et2016.extremeFeedbackDevice.control.advanced.AdvancedControlMissileLauncher;
import de.andrena.et2016.extremeFeedbackDevice.control.advanced.ManualToAdvancedMissileLauncherAdapter;
import de.andrena.et2016.extremeFeedbackDevice.control.advanced.ManualToAdvancedMissileLauncherAdapterFactory;
import de.andrena.et2016.extremeFeedbackDevice.control.manual.ManualControlMissileLauncher;
import de.andrena.et2016.extremeFeedbackDevice.driver.FeedbackDeviceFactory;

public class ThunderAdvancedControlMissileLauncherFeedbackDeviceFactoryTest {
	private interface UnsupportedInterface {
	}

	private FeedbackDeviceFactory manualFactory;
	private ThunderAdvancedControlMissileLauncherFeedbackDeviceFactory feedbackDeviceFactory;
	private ManualToAdvancedMissileLauncherAdapterFactory adapterFactory = mock(
			ManualToAdvancedMissileLauncherAdapterFactory.class);

	@Before
	public void setUpManualFactory() {
		manualFactory = mock(FeedbackDeviceFactory.class);
		when(manualFactory.findFeedbackDeviceSupportingControlInterface(ManualControlMissileLauncher.class))
				.thenReturn(Optional.empty());
	}

	@Before
	public void setUpFeedbackDeviceFactory() {
		feedbackDeviceFactory = new ThunderAdvancedControlMissileLauncherFeedbackDeviceFactory(manualFactory,
				adapterFactory);
	}

	@Test
	public void testRejectsUnsupportedInterface() throws Exception {
		Optional<UnsupportedInterface> feedbackDevice = feedbackDeviceFactory
				.findFeedbackDeviceSupportingControlInterface(UnsupportedInterface.class);

		assertThat(feedbackDevice.isPresent(), is(false));
		verifyZeroInteractions(manualFactory);
	}

	@Test
	public void testPassesOnRequestForManualControlMissileLauncher() throws Exception {
		Optional<AdvancedControlMissileLauncher> feedbackDevice = feedbackDeviceFactory
				.findFeedbackDeviceSupportingControlInterface(AdvancedControlMissileLauncher.class);

		assertThat(feedbackDevice.isPresent(), is(false));
	}

	@Test
	public void testCreatesInstanceUsingAdapter() throws Exception {
		ManualControlMissileLauncher manualControl = mock(ManualControlMissileLauncher.class);
		when(manualFactory.findFeedbackDeviceSupportingControlInterface(ManualControlMissileLauncher.class))
				.thenReturn(Optional.of(manualControl));
		ManualToAdvancedMissileLauncherAdapter adapter = mock(ManualToAdvancedMissileLauncherAdapter.class);
		when(adapterFactory.createFrom(manualControl)).thenReturn(adapter);
		Optional<AdvancedControlMissileLauncher> feedbackDevice = feedbackDeviceFactory
				.findFeedbackDeviceSupportingControlInterface(AdvancedControlMissileLauncher.class);

		assertThat(feedbackDevice.isPresent(), is(true));
		assertThat(feedbackDevice.get(), is(adapter));
	}
}
