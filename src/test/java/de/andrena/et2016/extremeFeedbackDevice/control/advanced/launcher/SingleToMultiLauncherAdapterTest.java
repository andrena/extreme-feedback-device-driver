package de.andrena.et2016.extremeFeedbackDevice.control.advanced.launcher;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;

import org.junit.Test;
import org.mockito.InOrder;

import de.andrena.et2016.extremeFeedbackDevice.control.manual.SingleLauncher;
import de.andrena.et2016.extremeFeedbackDevice.util.Sleeper;

public class SingleToMultiLauncherAdapterTest {
	private static final long INITIAL_DELAY = 600L;
	private static final long FIRING_DELAY = 3100L;
	private SingleLauncher singleLauncher = mock(SingleLauncher.class);
	private Sleeper sleeper = mock(Sleeper.class);
	private MultiLauncherDeviceSpecification specification = new MultiLauncherDeviceSpecification() {
		@Override
		public long minimumInitialStabilizationDelay() {
			return INITIAL_DELAY;
		}

		@Override
		public long minimumFiringDelay() {
			return FIRING_DELAY;
		}
	};
	private SingleToMultiLauncherAdapter adapter = new SingleToMultiLauncherAdapter(singleLauncher, sleeper,
			specification);

	@Test
	public void testFireStabilizesBeforeFirstFire() {
		adapter.fire(1);

		InOrder inOrder = inOrder(singleLauncher, sleeper);
		inOrder.verify(sleeper).sleep(INITIAL_DELAY);
		inOrder.verify(singleLauncher).fireOnce();
	}

	@Test
	public void testFirePausesAfterFirings() {
		adapter.fire(2);

		InOrder inOrder = inOrder(singleLauncher, sleeper);
		inOrder.verify(singleLauncher).fireOnce();
		inOrder.verify(sleeper).sleep(FIRING_DELAY);
		inOrder.verify(singleLauncher).fireOnce();
		inOrder.verify(sleeper).sleep(FIRING_DELAY);
	}
}
