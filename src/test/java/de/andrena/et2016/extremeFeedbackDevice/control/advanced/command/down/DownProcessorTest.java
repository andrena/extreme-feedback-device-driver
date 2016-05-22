package de.andrena.et2016.extremeFeedbackDevice.control.advanced.command.down;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;

import de.andrena.et2016.extremeFeedbackDevice.control.advanced.targetter.TimeBasedTargetter;

public class DownProcessorTest {
	@Test
	public void testDown() {
		TimeBasedTargetter launcher = mock(TimeBasedTargetter.class);
		DownProcessor processor = new DownProcessor(launcher);

		processor.process(new DownCommand(42L));

		verify(launcher).down(42L);
	}
}
