package de.andrena.et2016.extremeFeedbackDevice.driver;

public class ThreadSleeper implements Sleeper {

	@Override
	public void sleep(long millisToSleep) {
		try {
			Thread.sleep(millisToSleep);
		} catch (InterruptedException e) {
			throw new RuntimeException("Sleep failed", e);
		}
	}
}
