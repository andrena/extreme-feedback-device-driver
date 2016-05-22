package de.andrena.et2016.extremeFeedbackDevice.control.advanced.command.processor;

public class UnknownCommandException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public UnknownCommandException(String description) {
		super(description);
	}
}
