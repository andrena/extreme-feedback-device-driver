package de.andrena.et2016.extremeFeedbackDevice.control.advanced.targetter;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;

import org.junit.Test;
import org.mockito.InOrder;

import de.andrena.et2016.extremeFeedbackDevice.control.manual.StartStopTargetter;
import de.andrena.et2016.extremeFeedbackDevice.util.Sleeper;

public class TimeBasedToStartStopTargettingAdapterTest {
	private static final long SLEEP_TIME = 12L;

	private Sleeper sleeper = mock(Sleeper.class);
	private StartStopTargetter startStopTargetter = mock(StartStopTargetter.class);
	private TimeBasedToStartStopTargettingAdapter adapter = new TimeBasedToStartStopTargettingAdapter(
			startStopTargetter, sleeper);

	@Test
	public void testLeft() {
		adapter.left(12);

		InOrder inOrder = inOrder(sleeper, startStopTargetter);
		inOrder.verify(startStopTargetter)
				.startLeft();
		inOrder.verify(sleeper)
				.sleep(12L);
		inOrder.verify(startStopTargetter)
				.stop();
	}

	@Test
	public void testRight() {
		adapter.right(12);

		InOrder inOrder = inOrder(sleeper, startStopTargetter);
		inOrder.verify(startStopTargetter)
				.startRight();
		inOrder.verify(sleeper)
				.sleep(12L);
		inOrder.verify(startStopTargetter)
				.stop();
	}

	@Test
	public void testUp() {
		adapter.up(12);

		InOrder inOrder = inOrder(sleeper, startStopTargetter);
		inOrder.verify(startStopTargetter)
				.startUp();
		inOrder.verify(sleeper)
				.sleep(12L);
		inOrder.verify(startStopTargetter)
				.stop();
	}

	@Test
	public void testDown() {
		adapter.down(SLEEP_TIME);

		InOrder inOrder = inOrder(sleeper, startStopTargetter);
		inOrder.verify(startStopTargetter)
				.startDown();
		inOrder.verify(sleeper)
				.sleep(12L);
		inOrder.verify(startStopTargetter)
				.stop();
	}
}
