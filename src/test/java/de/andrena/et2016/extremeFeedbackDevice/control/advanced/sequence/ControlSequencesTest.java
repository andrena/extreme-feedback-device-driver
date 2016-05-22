package de.andrena.et2016.extremeFeedbackDevice.control.advanced.sequence;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

import java.util.Optional;
import java.util.Set;

import org.junit.Test;

public class ControlSequencesTest {
	private ControlSequences sequences = new ControlSequences();

	@Test
	public void testSequenceNotFound() {
		Optional<ControlSequence> sequence = sequences.getControlSequence("sSequence");

		assertThat(sequence.isPresent(), is(false));
	}

	@Test
	public void testSequenceFound() {
		ControlSequence aControlSequence = mock(ControlSequence.class);
		sequences.addControlSequence("aSequence", aControlSequence);

		Optional<ControlSequence> sequence = sequences.getControlSequence("aSequence");

		assertThat(sequence.isPresent(), is(true));
		assertThat(sequence.get(), is(aControlSequence));
	}

	@Test
	public void testSequenceFoundByName() {
		ControlSequence aControlSequence = mock(ControlSequence.class);
		sequences.addControlSequence("aSequence", aControlSequence);
		sequences.addControlSequence("otherSequence", mock(ControlSequence.class));

		Optional<ControlSequence> sequence = sequences.getControlSequence("aSequence");

		assertThat(sequence.isPresent(), is(true));
		assertThat(sequence.get(), is(aControlSequence));
	}

	@Test
	public void testGetSequenceNames() {
		sequences.addControlSequence("sequence", mock(ControlSequence.class));
		sequences.addControlSequence("otherSequence", mock(ControlSequence.class));

		Set<String> sequenceNames = sequences.getSequenceNames();

		assertThat(sequenceNames, contains("sequence", "otherSequence"));
	}
}
