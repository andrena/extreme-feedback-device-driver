package de.andrena.et2016.extremeFeedbackDevice.control.advanced.command.down;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class DownCommandTest {
	@Test
	public void testConstructorPassesMillis() {
		DownCommand command = new DownCommand(12L);

		assertThat(command.getTurnForMillis(), is(12L));
	}
}
