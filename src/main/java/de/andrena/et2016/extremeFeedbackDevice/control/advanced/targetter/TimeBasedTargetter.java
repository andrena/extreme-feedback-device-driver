package de.andrena.et2016.extremeFeedbackDevice.control.advanced.targetter;

public interface TimeBasedTargetter {
	void left(long forMillis);

	void right(long forMillis);

	void up(long forMillis);

	void down(long forMillis);
}
