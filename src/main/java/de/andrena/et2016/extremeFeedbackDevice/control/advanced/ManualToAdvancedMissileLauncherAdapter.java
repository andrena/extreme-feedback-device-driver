package de.andrena.et2016.extremeFeedbackDevice.control.advanced;

import de.andrena.et2016.extremeFeedbackDevice.control.advanced.launcher.SingleToMultiLauncherAdapter;
import de.andrena.et2016.extremeFeedbackDevice.control.advanced.launcher.SingleToMultiLauncherAdapterFactory;
import de.andrena.et2016.extremeFeedbackDevice.control.advanced.targetter.TimeBasedTargetter;
import de.andrena.et2016.extremeFeedbackDevice.control.advanced.targetter.TimeBasedToStartStopTargettingAdapterFactory;
import de.andrena.et2016.extremeFeedbackDevice.control.manual.ManualControlMissileLauncher;

public class ManualToAdvancedMissileLauncherAdapter implements AdvancedControlMissileLauncher {

	private TimeBasedTargetter timeBasedAdapter;
	private SingleToMultiLauncherAdapter multiLauncherAdapter;

	public ManualToAdvancedMissileLauncherAdapter(TimeBasedToStartStopTargettingAdapterFactory timeBasedAdapterFactory,
			SingleToMultiLauncherAdapterFactory multiLauncherAdapterFactory,
			ManualControlMissileLauncher manualControlMissileLauncher) {
		this.timeBasedAdapter = timeBasedAdapterFactory.createFor(manualControlMissileLauncher);
		this.multiLauncherAdapter = multiLauncherAdapterFactory.createFor(manualControlMissileLauncher);
	}

	@Override
	public void left(long forMillis) {
		timeBasedAdapter.left(forMillis);
	}

	@Override
	public void right(long forMillis) {
		timeBasedAdapter.right(forMillis);
	}

	@Override
	public void up(long forMillis) {
		timeBasedAdapter.up(forMillis);
	}

	@Override
	public void down(long forMillis) {
		timeBasedAdapter.down(forMillis);
	}

	@Override
	public void fire(int numberOfShots) {
		multiLauncherAdapter.fire(numberOfShots);
	}
}
