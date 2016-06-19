package de.andrena.et2016.extremeFeedbackDevice.control.advanced.command;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class TimeBasedMoveCommandTest {
	@Test
	public void testForMillisProperty() {
		TimeBasedMoveCommand command = new TimeBasedMoveCommand() {
		};

		command.setTurnForMillis(12L);

		assertThat(command.getTurnForMillis(), is(12L));
	}

	@Test
	public void testForMillisByConstructor() {
		TimeBasedMoveCommand command = new TimeBasedMoveCommand(13L) {
		};

		assertThat(command.getTurnForMillis(), is(13L));
	}
}
