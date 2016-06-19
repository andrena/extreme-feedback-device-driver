package de.andrena.et2016.extremeFeedbackDevice.driver.dummy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.andrena.et2016.extremeFeedbackDevice.control.advanced.launcher.MultiLauncherDeviceSpecification;
import de.andrena.et2016.extremeFeedbackDevice.control.manual.ManualControlMissileLauncher;

public class DummyManualControlMissileLauncher implements ManualControlMissileLauncher {
	private static final Logger log = LoggerFactory.getLogger(DummyManualControlMissileLauncher.class);

	@Override
	public void fireOnce() {
		log.info("firing once");
	}

	@Override
	public void startLeft() {
		log.info("starting left");
	}

	@Override
	public void startRight() {
		log.info("starting right");
	}

	@Override
	public void startDown() {
		log.info("starting down");
	}

	@Override
	public void startUp() {
		log.info("starting up");
	}

	@Override
	public void stop() {
		log.info("stopping");
	}

	@Override
	public MultiLauncherDeviceSpecification getMultiLauncherDeviceSpecification() {
		return new MultiLauncherDeviceSpecification() {

			@Override
			public long minimumInitialStabilizationDelay() {
				return 0L;
			}

			@Override
			public long minimumFiringDelay() {
				return 0L;
			}
		};
	}
}