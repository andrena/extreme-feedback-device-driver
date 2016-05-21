package de.andrena.et2016.extremeFeedbackDevice.driver;

public class TimeBasedTargettingMissileLauncher implements TimeBasedTargetter {

	private MissileLauncher launcher;
	private Sleeper sleeper;

	public TimeBasedTargettingMissileLauncher(MissileLauncher launcher, Sleeper sleeper) {
		this.launcher = launcher;
		this.sleeper = sleeper;
	}

	@Override
	public void left(long forMillis) {
		launcher.startLeft();
		sleepForMillisAndStop(forMillis);
	}

	@Override
	public void right(long forMillis) {
		launcher.startRight();
		sleepForMillisAndStop(forMillis);
	}

	@Override
	public void up(long forMillis) {
		launcher.startUp();
		sleepForMillisAndStop(forMillis);
	}

	@Override
	public void down(long forMillis) {
		launcher.startDown();
		sleepForMillisAndStop(forMillis);
	}

	private void sleepForMillisAndStop(long forMillis) {
		sleeper.sleep(forMillis);
		launcher.stop();
	}
}
