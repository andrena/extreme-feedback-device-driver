package de.andrena.et2016.extremeFeedbackDevice.control.advanced.sequence;

import java.util.Optional;

public class ControlSequencesProcessor {

	private ControlSequenceProcessor processor;
	private ControlSequences sequences;

	public ControlSequencesProcessor(ControlSequenceProcessor processor, ControlSequences sequences) {
		this.processor = processor;
		this.sequences = sequences;
	}

	public void processSequence(String controlSequenceName) {
		Optional<ControlSequence> controlSequence = sequences.getControlSequence(controlSequenceName);
		if (!controlSequence.isPresent()) {
			throw new UnknownControlSequenceException("Unknown control sequence " + controlSequenceName);
		}
		processor.processSequence(controlSequence.get());
	}
}
