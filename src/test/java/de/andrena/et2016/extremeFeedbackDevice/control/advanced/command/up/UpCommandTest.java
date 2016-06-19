package de.andrena.et2016.extremeFeedbackDevice.control.advanced.command.up;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class UpCommandTest {
	@Test
	public void testConstructorPassesMillis() {
		UpCommand command = new UpCommand(12L);

		assertThat(command.getTurnForMillis(), is(12L));
	}
}
