package de.andrena.et2016.extremeFeedbackDevice.control.advanced.command.processor;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import de.andrena.et2016.extremeFeedbackDevice.control.advanced.command.Command;

public class CommandProcessorsTest {
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	private CommandProcessors registry = new CommandProcessors();
	@SuppressWarnings("unchecked")
	private CommandProcessor<SampleCommand> processor = mock(CommandProcessor.class);
	@SuppressWarnings("unchecked")
	private CommandProcessor<OtherSampleCommand> otherProcessor = mock(CommandProcessor.class);

	private static class SampleCommand implements Command {
	}

	private static class OtherSampleCommand implements Command {
	}

	@Test
	public void testUnknownCommandThrowsException() {
		CommandProcessors registry = new CommandProcessors();

		thrown.expect(UnknownCommandException.class);
		registry.processCommand(mock(Command.class));
	}

	@Test
	public void testRegisteredCommandIsProcessed() {
		SampleCommand command = new SampleCommand();
		registry.register(SampleCommand.class, processor);

		registry.processCommand(command);

		verify(processor).process(command);
	}

	@Test
	public void testMultipleCommandsAreProcessed() {
		SampleCommand sampleCommand = new SampleCommand();
		OtherSampleCommand otherSampleCommand = new OtherSampleCommand();
		registry.register(SampleCommand.class, processor);
		registry.register(OtherSampleCommand.class, otherProcessor);

		registry.processCommand(otherSampleCommand);
		registry.processCommand(sampleCommand);

		verify(otherProcessor).process(otherSampleCommand);
		verifyNoMoreInteractions(otherProcessor);
		verify(processor).process(sampleCommand);
		verifyNoMoreInteractions(processor);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testGetCommandClassesArrayReturnsAllRegisteredCommands() {
		registry.register(SampleCommand.class, processor);
		registry.register(OtherSampleCommand.class, otherProcessor);

		assertThat(asList(registry.getCommandClassesArray()),
				containsInAnyOrder(SampleCommand.class, OtherSampleCommand.class));
	}

}
