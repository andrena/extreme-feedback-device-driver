package de.andrena.et2016.extremeFeedbackDevice.control.advanced.launcher;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;

import org.junit.Test;
import org.mockito.InOrder;

import de.andrena.et2016.extremeFeedbackDevice.control.manual.SingleLauncher;
import de.andrena.et2016.extremeFeedbackDevice.util.Sleeper;

public class SingleToMultiLauncherAdapterTest {
	private SingleLauncher singleLauncher = mock(SingleLauncher.class);
	private Sleeper sleeper = mock(Sleeper.class);
	private SingleToMultiLauncherAdapter adapter = new SingleToMultiLauncherAdapter(singleLauncher, sleeper);

	@Test
	public void testFireStabilizesBeforeFirstFire() {
		adapter.fire(1);

		InOrder inOrder = inOrder(singleLauncher, sleeper);
		inOrder.verify(sleeper).sleep(500L);
		inOrder.verify(singleLauncher).fireOnce();
	}

	@Test
	public void testFirePausesAfterFirings() {
		adapter.fire(2);

		InOrder inOrder = inOrder(singleLauncher, sleeper);
		inOrder.verify(singleLauncher).fireOnce();
		inOrder.verify(sleeper).sleep(2500L);
		inOrder.verify(singleLauncher).fireOnce();
		inOrder.verify(sleeper).sleep(2500L);
	}
}
