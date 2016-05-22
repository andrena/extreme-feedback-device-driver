package de.andrena.et2016.extremeFeedbackDevice.control.advanced.command.up;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;

import de.andrena.et2016.extremeFeedbackDevice.control.advanced.targetter.TimeBasedTargetter;

public class UpProcessorTest {
	@Test
	public void testUp() {
		TimeBasedTargetter launcher = mock(TimeBasedTargetter.class);
		UpProcessor processor = new UpProcessor(launcher);

		processor.process(new UpCommand(42L));

		verify(launcher).up(42L);
	}
}
