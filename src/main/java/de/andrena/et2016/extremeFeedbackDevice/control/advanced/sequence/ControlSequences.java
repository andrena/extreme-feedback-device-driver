package de.andrena.et2016.extremeFeedbackDevice.control.advanced.sequence;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ControlSequences {
	private Map<String, ControlSequence> sequences = new HashMap<>();

	@JsonIgnore
	public Optional<ControlSequence> getControlSequence(String controlSequenceName) {
		return Optional.ofNullable(sequences.get(controlSequenceName));
	}

	public void addControlSequence(String controlSequenceName, ControlSequence controlSequence) {
		sequences.put(controlSequenceName, controlSequence);
	}

	@JsonIgnore
	public Set<String> getSequenceNames() {
		return sequences.keySet();
	}

	public Map<String, ControlSequence> getSequences() {
		return sequences;
	}
}
