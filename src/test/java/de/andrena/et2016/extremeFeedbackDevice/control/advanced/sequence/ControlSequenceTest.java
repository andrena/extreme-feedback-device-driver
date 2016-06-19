package de.andrena.et2016.extremeFeedbackDevice.control.advanced.sequence;

import static java.util.Collections.emptyList;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

import org.junit.Test;

import de.andrena.et2016.extremeFeedbackDevice.control.advanced.command.Command;

public class ControlSequenceTest {
	@Test
	public void testInitiallyNoCommands() {
		ControlSequence sequence = new ControlSequence();

		assertThat(sequence.getCommands(), is(emptyList()));
	}

	@Test
	public void testReturnsCommand() {
		Command aCommand = mock(Command.class);
		ControlSequence sequence = new ControlSequence();

		sequence.addCommand(aCommand);

		assertThat(sequence.getCommands(), contains(aCommand));
	}

	@Test
	public void testReturnsCommandsInOrder() {
		Command firstCommand = mock(Command.class);
		Command nextCommand = mock(Command.class);
		ControlSequence sequence = new ControlSequence();

		sequence.addCommand(firstCommand);
		sequence.addCommand(nextCommand);

		assertThat(sequence.getCommands(), contains(firstCommand, nextCommand));
	}
}
