package de.andrena.et2016.extremeFeedbackDevice.control.advanced.adapter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import org.junit.Test;

import de.andrena.et2016.extremeFeedbackDevice.control.advanced.adapter.ManualToAdvancedMissileLauncherAdapter;
import de.andrena.et2016.extremeFeedbackDevice.control.advanced.launcher.SingleToMultiLauncherAdapter;
import de.andrena.et2016.extremeFeedbackDevice.control.advanced.targetter.TimeBasedTargetter;

public class ManualToAdvancedMissileLauncherAdapterTest {
	private TimeBasedTargetter timeBasedTargetter = mock(TimeBasedTargetter.class);
	private SingleToMultiLauncherAdapter multiLauncherAdapter = mock(SingleToMultiLauncherAdapter.class);
	private ManualToAdvancedMissileLauncherAdapter adapter = new ManualToAdvancedMissileLauncherAdapter(
			timeBasedTargetter, multiLauncherAdapter);

	@Test
	public void testLeftDelegatesToTimeBasedTargetterAdapter() {
		adapter.left(12L);

		verify(timeBasedTargetter).left(12L);
		verifyNoMoreInteractions(timeBasedTargetter);
	}

	@Test
	public void testRightDelegatesToTimeBasedTargetterAdapter() {
		adapter.right(12L);

		verify(timeBasedTargetter).right(12L);
		verifyNoMoreInteractions(timeBasedTargetter);
	}

	@Test
	public void testUpDelegatesToTimeBasedTargetterAdapter() {
		adapter.up(12L);

		verify(timeBasedTargetter).up(12L);
		verifyNoMoreInteractions(timeBasedTargetter);
	}

	@Test
	public void testDownDelegatesToTimeBasedTargetterAdapter() {
		adapter.down(12L);

		verify(timeBasedTargetter).down(12L);
		verifyNoMoreInteractions(timeBasedTargetter);
	}

	@Test
	public void testFireDelegatesToSingleToMultiLauncherAdapter() {
		adapter.fire(12);

		verify(multiLauncherAdapter).fire(12);
		verifyNoMoreInteractions(multiLauncherAdapter);
	}
}
