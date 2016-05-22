package de.andrena.et2016.extremeFeedbackDevice.control.advanced.command.right;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;

import de.andrena.et2016.extremeFeedbackDevice.control.advanced.targetter.TimeBasedTargetter;

public class RightProcessorTest {
	@Test
	public void testRight() {
		TimeBasedTargetter launcher = mock(TimeBasedTargetter.class);
		RightProcessor processor = new RightProcessor(launcher);

		processor.process(new RightCommand(42L));

		verify(launcher).right(42L);
	}
}
