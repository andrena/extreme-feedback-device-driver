package de.andrena.et2016.extremeFeedbackDevice.control.advanced.launcher;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;

import de.andrena.et2016.extremeFeedbackDevice.control.advanced.launcher.SingleToMultiLauncherAdapter;
import de.andrena.et2016.extremeFeedbackDevice.control.manual.SingleLauncher;

public class SingleToMultiLauncherAdapterTest {
	@Test
	public void testFireCallsSingleFire() {
		SingleLauncher singleLauncher = mock(SingleLauncher.class);
		SingleToMultiLauncherAdapter adapter = new SingleToMultiLauncherAdapter(singleLauncher);

		adapter.fire(12);

		verify(singleLauncher, times(12)).fireOnce();
	}
}
