package de.andrena.et2016.extremeFeedbackDevice.driver;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;

public class FeedbackDeviceSingletonProxyFactoryTest {
	private interface SampleControlInterface {
	}

	private interface OtherSampleControlInterface {
	}

	private FeedbackDeviceFactory innerFactory = mock(FeedbackDeviceFactory.class);
	private FeedbackDeviceSingletonProxyFactory singletonFactory = new FeedbackDeviceSingletonProxyFactory(
			innerFactory);
	private SampleControlInterface sampleFeedbackDevice = mock(SampleControlInterface.class);
	private OtherSampleControlInterface otherSampleFeedbackDevice = mock(OtherSampleControlInterface.class);

	@Test
	public void testEmptyReturnsEmpty() {
		when(innerFactory.findFeedbackDeviceSupportingControlInterface(SampleControlInterface.class))
				.thenReturn(Optional.empty());

		Optional<SampleControlInterface> feedbackDevice = singletonFactory
				.findFeedbackDeviceSupportingControlInterface(SampleControlInterface.class);

		assertThat(feedbackDevice.isPresent(), is(false));
	}

	@Test
	public void testPassesThroughInitially() {
		SampleControlInterface expectedFeedbackDevice = mock(SampleControlInterface.class);
		when(innerFactory.findFeedbackDeviceSupportingControlInterface(SampleControlInterface.class))
				.thenReturn(Optional.of(expectedFeedbackDevice));

		Optional<SampleControlInterface> feedbackDevice = singletonFactory
				.findFeedbackDeviceSupportingControlInterface(SampleControlInterface.class);

		assertThat(feedbackDevice.isPresent(), is(true));
		assertThat(feedbackDevice.get(), is(expectedFeedbackDevice));
	}

	@Test
	public void testCachesSuccessfulCall() {
		when(innerFactory.findFeedbackDeviceSupportingControlInterface(SampleControlInterface.class))
				.thenReturn(Optional.of(sampleFeedbackDevice));
		singletonFactory.findFeedbackDeviceSupportingControlInterface(SampleControlInterface.class);

		Optional<SampleControlInterface> feedbackDevice = singletonFactory
				.findFeedbackDeviceSupportingControlInterface(SampleControlInterface.class);

		verify(innerFactory, times(1)).findFeedbackDeviceSupportingControlInterface(SampleControlInterface.class);
		assertThat(feedbackDevice.isPresent(), is(true));
		assertThat(feedbackDevice.get(), is(sampleFeedbackDevice));
	}

	@Test
	public void testReturnsInterfaceSpecificResults() {
		when(innerFactory.findFeedbackDeviceSupportingControlInterface(SampleControlInterface.class))
				.thenReturn(Optional.of(sampleFeedbackDevice));
		singletonFactory.findFeedbackDeviceSupportingControlInterface(SampleControlInterface.class);
		when(innerFactory.findFeedbackDeviceSupportingControlInterface(OtherSampleControlInterface.class))
				.thenReturn(Optional.of(otherSampleFeedbackDevice));

		Optional<OtherSampleControlInterface> feedbackDevice = singletonFactory
				.findFeedbackDeviceSupportingControlInterface(OtherSampleControlInterface.class);

		assertThat(feedbackDevice.isPresent(), is(true));
		assertThat(feedbackDevice.get(), is(otherSampleFeedbackDevice));
	}
}
