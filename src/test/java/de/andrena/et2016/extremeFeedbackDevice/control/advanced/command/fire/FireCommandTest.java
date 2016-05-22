package de.andrena.et2016.extremeFeedbackDevice.control.advanced.command.fire;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import de.andrena.et2016.extremeFeedbackDevice.control.advanced.command.fire.FireCommand;

public class FireCommandTest {
	@Test
	public void testForMillisProperty() {
		FireCommand command = new FireCommand();

		command.setNumberOfShots(12);

		assertThat(command.getNumberOfShots(), is(12));
	}

	@Test
	public void testForMillisConstructor() {
		FireCommand command = new FireCommand(13);

		assertThat(command.getNumberOfShots(), is(13));
	}
}
