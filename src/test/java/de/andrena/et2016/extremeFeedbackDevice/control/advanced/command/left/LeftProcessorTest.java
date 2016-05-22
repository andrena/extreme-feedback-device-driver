package de.andrena.et2016.extremeFeedbackDevice.control.advanced.command.left;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;

import de.andrena.et2016.extremeFeedbackDevice.control.advanced.targetter.TimeBasedTargetter;

public class LeftProcessorTest {
	@Test
	public void testLeft() {
		TimeBasedTargetter launcher = mock(TimeBasedTargetter.class);
		LeftProcessor processor = new LeftProcessor(launcher);

		processor.process(new LeftCommand(42L));

		verify(launcher).left(42L);
	}
}
