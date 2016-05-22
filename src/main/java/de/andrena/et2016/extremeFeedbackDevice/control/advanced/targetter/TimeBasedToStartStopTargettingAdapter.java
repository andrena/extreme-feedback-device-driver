package de.andrena.et2016.extremeFeedbackDevice.control.advanced.targetter;

import de.andrena.et2016.extremeFeedbackDevice.control.manual.StartStopTargetter;
import de.andrena.et2016.extremeFeedbackDevice.util.Sleeper;

public class TimeBasedToStartStopTargettingAdapter implements TimeBasedTargetter {

	private StartStopTargetter startStopTargetter;
	private Sleeper sleeper;

	public TimeBasedToStartStopTargettingAdapter(StartStopTargetter startStopTargetter, Sleeper sleeper) {
		this.startStopTargetter = startStopTargetter;
		this.sleeper = sleeper;
	}

	@Override
	public void left(long forMillis) {
		startStopTargetter.startLeft();
		sleepForMillisAndStop(forMillis);
	}

	@Override
	public void right(long forMillis) {
		startStopTargetter.startRight();
		sleepForMillisAndStop(forMillis);
	}

	@Override
	public void up(long forMillis) {
		startStopTargetter.startUp();
		sleepForMillisAndStop(forMillis);
	}

	@Override
	public void down(long forMillis) {
		startStopTargetter.startDown();
		sleepForMillisAndStop(forMillis);
	}

	private void sleepForMillisAndStop(long forMillis) {
		sleeper.sleep(forMillis);
		startStopTargetter.stop();
	}
}
