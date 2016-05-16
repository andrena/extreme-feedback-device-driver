package de.andrena.et2016.extremeFeedbackDevice.driver;

public interface MissileLauncher {
	void startLeft();

	void startRight();

	void startDown();

	void startUp();

	void stop();

	void fireOnce();
}
