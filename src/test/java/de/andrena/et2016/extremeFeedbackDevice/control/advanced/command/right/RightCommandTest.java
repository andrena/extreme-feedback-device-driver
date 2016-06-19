package de.andrena.et2016.extremeFeedbackDevice.control.advanced.command.right;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class RightCommandTest {
	@Test
	public void testConstructorPassesMillis() {
		RightCommand command = new RightCommand(12L);

		assertThat(command.getTurnForMillis(), is(12L));
	}
}
