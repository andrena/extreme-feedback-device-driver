package de.andrena.et2016.extremeFeedbackDevice.driver.thunder.manual;

import de.andrena.et2016.extremeFeedbackDevice.control.advanced.launcher.MultiLauncherDeviceSpecification;

public class ThunderMissileLauncherMultiLauncherSpecification implements MultiLauncherDeviceSpecification {
	@Override
	public long minimumInitialStabilizationDelay() {
		return 600L;
	}

	@Override
	public long minimumFiringDelay() {
		return 3100L;
	}
}