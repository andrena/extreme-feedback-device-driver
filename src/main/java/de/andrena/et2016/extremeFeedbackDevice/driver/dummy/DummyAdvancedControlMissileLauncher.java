package de.andrena.et2016.extremeFeedbackDevice.driver.dummy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.andrena.et2016.extremeFeedbackDevice.control.advanced.AdvancedControlMissileLauncher;

public class DummyAdvancedControlMissileLauncher implements AdvancedControlMissileLauncher {
	private static final Logger log = LoggerFactory.getLogger(DummyAdvancedControlMissileLauncher.class);

	@Override
	public void fire(int numberOfShots) {
		log.info("firing " + numberOfShots + " shots");
	}

	@Override
	public void up(long forMillis) {
		log.info("moving up for " + forMillis + "ms");
	}

	@Override
	public void right(long forMillis) {
		log.info("moving right for " + forMillis + "ms");
	}

	@Override
	public void left(long forMillis) {
		log.info("moving left for " + forMillis + "ms");
	}

	@Override
	public void down(long forMillis) {
		log.info("moving down for " + forMillis + "ms");
	}
}