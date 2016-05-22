package de.andrena.et2016.extremeFeedbackDevice.control.advanced.command;

public abstract class TimeBasedMoveCommand {

	private long turnForMillis;

	public TimeBasedMoveCommand() {
	}

	public TimeBasedMoveCommand(long turnForMillis) {
		this.turnForMillis = turnForMillis;
	}

	public void setTurnForMillis(long forMillis) {
		this.turnForMillis = forMillis;
	}

	public long getTurnForMillis() {
		return turnForMillis;
	}
}