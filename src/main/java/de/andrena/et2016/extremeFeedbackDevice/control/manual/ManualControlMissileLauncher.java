package de.andrena.et2016.extremeFeedbackDevice.control.manual;

import de.andrena.et2016.extremeFeedbackDevice.control.advanced.launcher.MultiLauncherDeviceSpecification;

public interface ManualControlMissileLauncher extends SingleLauncher, StartStopTargetter {
	MultiLauncherDeviceSpecification getMultiLauncherDeviceSpecification();
}
