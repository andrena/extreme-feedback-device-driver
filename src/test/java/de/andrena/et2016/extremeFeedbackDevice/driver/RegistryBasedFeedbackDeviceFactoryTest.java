package de.andrena.et2016.extremeFeedbackDevice.driver;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;

public class RegistryBasedFeedbackDeviceFactoryTest {
	private RegistryBasedFeedbackDeviceFactory registry = new RegistryBasedFeedbackDeviceFactory();

	private static interface SampleDeviceInterface {
	}

	@Test
	public void testNoRegisteredFactoriesFindsNoMissileLauncher() {
		Optional<SampleDeviceInterface> launcher = registry
				.findFeedbackDeviceSupportingControlInterface(SampleDeviceInterface.class);

		assertThat(launcher.isPresent(), is(false));
	}

	@Test
	public void testSingleRegisteredFactoryIsCalledOnFindMissileLauncher() {
		FeedbackDeviceFactory factory = mock(FeedbackDeviceFactory.class);
		when(factory.findFeedbackDeviceSupportingControlInterface(SampleDeviceInterface.class))
				.thenReturn(Optional.empty());
		registry.addFactory(factory);

		Optional<SampleDeviceInterface> launcher = registry
				.findFeedbackDeviceSupportingControlInterface(SampleDeviceInterface.class);

		assertThat(launcher.isPresent(), is(false));
	}

	@Test
	public void testSingleRegisteredFactoryCallResultIsPassedOn() {
		SampleDeviceInterface sampleDevice = mock(SampleDeviceInterface.class);
		FeedbackDeviceFactory factory = mock(FeedbackDeviceFactory.class);
		when(factory.findFeedbackDeviceSupportingControlInterface(SampleDeviceInterface.class))
				.thenReturn(Optional.of(sampleDevice));
		registry.addFactory(factory);

		Optional<SampleDeviceInterface> launcher = registry
				.findFeedbackDeviceSupportingControlInterface(SampleDeviceInterface.class);

		assertThat(launcher.isPresent(), is(true));
		assertThat(launcher.get(), is(sampleDevice));
	}

	@Test
	public void testForMultipleFactoriesTheFirstSuccessfulFactoryReturnsInstance() {
		FeedbackDeviceFactory firstFactory = mock(FeedbackDeviceFactory.class);
		when(firstFactory.findFeedbackDeviceSupportingControlInterface(SampleDeviceInterface.class))
				.thenReturn(Optional.empty());
		registry.addFactory(firstFactory);

		FeedbackDeviceFactory secondFactory = mock(FeedbackDeviceFactory.class);
		SampleDeviceInterface sampleDevice = mock(SampleDeviceInterface.class);
		when(firstFactory.findFeedbackDeviceSupportingControlInterface(SampleDeviceInterface.class))
				.thenReturn(Optional.of(sampleDevice));
		registry.addFactory(secondFactory);

		Optional<SampleDeviceInterface> launcher = registry
				.findFeedbackDeviceSupportingControlInterface(SampleDeviceInterface.class);

		assertThat(launcher.isPresent(), is(true));
		assertThat(launcher.get(), is(sampleDevice));
	}

	@Test
	public void testForMultipleFactoriesFollowingFactoriesAreNotCalled() {
		FeedbackDeviceFactory firstFactory = mock(FeedbackDeviceFactory.class);
		SampleDeviceInterface sampleDevice = mock(SampleDeviceInterface.class);
		when(firstFactory.findFeedbackDeviceSupportingControlInterface(SampleDeviceInterface.class))
				.thenReturn(Optional.of(sampleDevice));
		registry.addFactory(firstFactory);

		FeedbackDeviceFactory secondFactory = mock(FeedbackDeviceFactory.class);
		when(secondFactory.findFeedbackDeviceSupportingControlInterface(SampleDeviceInterface.class))
				.thenReturn(Optional.empty());
		registry.addFactory(secondFactory);

		Optional<SampleDeviceInterface> launcher = registry
				.findFeedbackDeviceSupportingControlInterface(SampleDeviceInterface.class);

		assertThat(launcher.isPresent(), is(true));
		assertThat(launcher.get(), is(sampleDevice));
	}
}
