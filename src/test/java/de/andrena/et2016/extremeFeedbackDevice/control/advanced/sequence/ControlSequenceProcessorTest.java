package de.andrena.et2016.extremeFeedbackDevice.control.advanced.sequence;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

import org.junit.Test;
import org.mockito.InOrder;

import de.andrena.et2016.extremeFeedbackDevice.control.advanced.command.Command;
import de.andrena.et2016.extremeFeedbackDevice.control.advanced.command.processor.CommandProcessors;

public class ControlSequenceProcessorTest {
	private CommandProcessors commandProcessor = mock(CommandProcessors.class);
	private ControlSequenceProcessor processor = new ControlSequenceProcessor(commandProcessor);
	private ControlSequence controlSequence = new ControlSequence();

	@Test
	public void testEmptySequenceIsNoOp() {
		processor.processSequence(controlSequence);

		verifyZeroInteractions(commandProcessor);
	}

	@Test
	public void testCommandIsProcessed() {
		Command command = mock(Command.class);
		controlSequence.addCommand(command);

		processor.processSequence(controlSequence);

		verify(commandProcessor, times(1)).processCommand(command);
		verifyNoMoreInteractions(commandProcessor);
	}

	@Test
	public void testMultipleCommandAreProcessedInSequence() {
		Command commandA = mock(Command.class);
		Command commandB = mock(Command.class);
		controlSequence.addCommand(commandA);
		controlSequence.addCommand(commandB);
		controlSequence.addCommand(commandA);

		processor.processSequence(controlSequence);

		InOrder inOrder = inOrder(commandProcessor);
		inOrder.verify(commandProcessor, times(1))
				.processCommand(commandA);
		inOrder.verify(commandProcessor, times(1))
				.processCommand(commandB);
		inOrder.verify(commandProcessor, times(1))
				.processCommand(commandA);
		inOrder.verifyNoMoreInteractions();
	}
}
