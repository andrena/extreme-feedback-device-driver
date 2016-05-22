package de.andrena.et2016.extremeFeedbackDevice.control.advanced;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import de.andrena.et2016.extremeFeedbackDevice.control.advanced.launcher.SingleToMultiLauncherAdapter;
import de.andrena.et2016.extremeFeedbackDevice.control.advanced.launcher.SingleToMultiLauncherAdapterFactory;
import de.andrena.et2016.extremeFeedbackDevice.control.advanced.targetter.TimeBasedTargetter;
import de.andrena.et2016.extremeFeedbackDevice.control.advanced.targetter.TimeBasedToStartStopTargettingAdapterFactory;
import de.andrena.et2016.extremeFeedbackDevice.control.manual.ManualControlMissileLauncher;

public class ManualToAdvancedMissileLauncherAdapterTest {
	private ManualControlMissileLauncher manualControlMissileLauncher = mock(ManualControlMissileLauncher.class);
	private TimeBasedTargetter timeBasedTargetter = mock(TimeBasedTargetter.class);
	private SingleToMultiLauncherAdapter multiLauncherAdapter = mock(SingleToMultiLauncherAdapter.class);
	private TimeBasedToStartStopTargettingAdapterFactory timeBasedAdapterFactory;
	private ManualToAdvancedMissileLauncherAdapter adapter;
	private SingleToMultiLauncherAdapterFactory multiLauncherAdapterFactory;

	@Before
	public void setUpTimeBasedAdapterFactory() {
		timeBasedAdapterFactory = mock(TimeBasedToStartStopTargettingAdapterFactory.class);
		when(timeBasedAdapterFactory.createFor(manualControlMissileLauncher)).thenReturn(timeBasedTargetter);
	}

	@Before
	public void setUpMultiLauncherAdapterFactory() {
		multiLauncherAdapterFactory = mock(SingleToMultiLauncherAdapterFactory.class);
		when(multiLauncherAdapterFactory.createFor(manualControlMissileLauncher)).thenReturn(multiLauncherAdapter);
	}

	@Before
	public void setUpManualToAdvancedMissileLauncherAdapter() {
		adapter = new ManualToAdvancedMissileLauncherAdapter(timeBasedAdapterFactory, multiLauncherAdapterFactory,
				manualControlMissileLauncher);
	}

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
