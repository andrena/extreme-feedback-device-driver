package de.andrena.et2016.extremeFeedbackDevice.driver.dummy;

import de.andrena.et2016.extremeFeedbackDevice.control.advanced.AdvancedControlMissileLauncher;

public class DummyAdvancedControlMissileLauncher implements AdvancedControlMissileLauncher {
	@Override
	public void fire(int numberOfShots) {
		System.out.println("firing " + numberOfShots + " shots");
	}

	@Override
	public void up(long forMillis) {
		System.out.println("moving up for " + forMillis + "ms");
	}

	@Override
	public void right(long forMillis) {
		System.out.println("moving right for " + forMillis + "ms");
	}

	@Override
	public void left(long forMillis) {
		System.out.println("moving left for " + forMillis + "ms");
	}

	@Override
	public void down(long forMillis) {
		System.out.println("moving down for " + forMillis + "ms");
	}
}