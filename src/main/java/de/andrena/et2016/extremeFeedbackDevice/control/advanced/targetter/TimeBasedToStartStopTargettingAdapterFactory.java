package de.andrena.et2016.extremeFeedbackDevice.control.advanced.targetter;

import de.andrena.et2016.extremeFeedbackDevice.control.manual.StartStopTargetter;
import de.andrena.et2016.extremeFeedbackDevice.util.Sleeper;

public class TimeBasedToStartStopTargettingAdapterFactory {

	private Sleeper sleeper;

	public TimeBasedToStartStopTargettingAdapterFactory(Sleeper sleeper) {
		this.sleeper = sleeper;
	}

	public TimeBasedTargetter createFor(StartStopTargetter startStopTargetter) {
		return new TimeBasedToStartStopTargettingAdapter(startStopTargetter, sleeper);
	}
}
