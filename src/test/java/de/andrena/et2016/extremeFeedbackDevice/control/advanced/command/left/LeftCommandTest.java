package de.andrena.et2016.extremeFeedbackDevice.control.advanced.command.left;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import de.andrena.et2016.extremeFeedbackDevice.control.advanced.command.left.LeftCommand;

public class LeftCommandTest {
	@Test
	public void testConstructorPassesMillis() {
		LeftCommand command = new LeftCommand(12L);

		assertThat(command.getTurnForMillis(), is(12L));
	}
}
