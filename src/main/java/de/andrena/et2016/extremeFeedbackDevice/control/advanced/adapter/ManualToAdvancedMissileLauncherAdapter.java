package de.andrena.et2016.extremeFeedbackDevice.control.advanced.adapter;

import de.andrena.et2016.extremeFeedbackDevice.control.advanced.AdvancedControlMissileLauncher;
import de.andrena.et2016.extremeFeedbackDevice.control.advanced.launcher.SingleToMultiLauncherAdapter;
import de.andrena.et2016.extremeFeedbackDevice.control.advanced.targetter.TimeBasedTargetter;

public class ManualToAdvancedMissileLauncherAdapter implements AdvancedControlMissileLauncher {

	private TimeBasedTargetter timeBasedAdapter;
	private SingleToMultiLauncherAdapter multiLauncherAdapter;

	public ManualToAdvancedMissileLauncherAdapter(TimeBasedTargetter timeBasedAdapter,
			SingleToMultiLauncherAdapter multiLauncherAdapter) {
		this.timeBasedAdapter = timeBasedAdapter;
		this.multiLauncherAdapter = multiLauncherAdapter;
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
