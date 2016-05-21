package de.andrena.et2016.extremeFeedbackDevice.driver;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;

import org.junit.Test;
import org.mockito.InOrder;

public class TimeBasedTargettingMissileLauncherTest {
	private static final long SLEEP_TIME = 12L;

	private Sleeper sleeper = mock(Sleeper.class);
	private MissileLauncher launcher = mock(MissileLauncher.class);
	private TimeBasedTargettingMissileLauncher adapter = new TimeBasedTargettingMissileLauncher(launcher, sleeper);

	@Test
	public void testLeft() {
		adapter.left(12);

		InOrder inOrder = inOrder(sleeper, launcher);
		inOrder.verify(launcher).startLeft();
		inOrder.verify(sleeper).sleep(12L);
		inOrder.verify(launcher).stop();
	}

	@Test
	public void testRight() {
		adapter.right(12);

		InOrder inOrder = inOrder(sleeper, launcher);
		inOrder.verify(launcher).startRight();
		inOrder.verify(sleeper).sleep(12L);
		inOrder.verify(launcher).stop();
	}

	@Test
	public void testUp() {
		adapter.up(12);

		InOrder inOrder = inOrder(sleeper, launcher);
		inOrder.verify(launcher).startUp();
		inOrder.verify(sleeper).sleep(12L);
		inOrder.verify(launcher).stop();
	}

	@Test
	public void testDown() {
		adapter.down(SLEEP_TIME);

		InOrder inOrder = inOrder(sleeper, launcher);
		inOrder.verify(launcher).startDown();
		inOrder.verify(sleeper).sleep(12L);
		inOrder.verify(launcher).stop();
	}
}
