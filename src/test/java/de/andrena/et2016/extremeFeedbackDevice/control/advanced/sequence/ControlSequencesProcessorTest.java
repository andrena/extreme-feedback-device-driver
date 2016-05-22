package de.andrena.et2016.extremeFeedbackDevice.control.advanced.sequence;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ControlSequencesProcessorTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	private ControlSequenceProcessor controlSequenceProcessor = mock(ControlSequenceProcessor.class);
	private ControlSequences controlSequences = mock(ControlSequences.class);
	private ControlSequencesProcessor sequencesProcessor = new ControlSequencesProcessor(controlSequenceProcessor,
			controlSequences);

	@Test
	public void testMissingSequenceThrowsException() {
		when(controlSequences.getControlSequence("unknown")).thenReturn(Optional.empty());

		thrown.expect(UnknownControlSequenceException.class);
		sequencesProcessor.processSequence("unknown");
	}

	@Test
	public void testSequenceIsProcessed() {
		ControlSequence controlSequence = mock(ControlSequence.class);
		when(controlSequences.getControlSequence("aSequence")).thenReturn(Optional.of(controlSequence));

		sequencesProcessor.processSequence("aSequence");

		verify(controlSequenceProcessor).processSequence(controlSequence);
	}
}
