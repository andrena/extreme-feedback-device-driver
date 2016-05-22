package de.andrena.et2016.extremeFeedbackDevice.util;

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
