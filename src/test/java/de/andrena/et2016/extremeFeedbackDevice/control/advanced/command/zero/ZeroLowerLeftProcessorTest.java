package de.andrena.et2016.extremeFeedbackDevice.control.advanced.command.zero;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;

import de.andrena.et2016.extremeFeedbackDevice.control.advanced.targetter.TimeBasedTargetter;

public class ZeroLowerLeftProcessorTest {
	@Test
	public void testZero() {
		TimeBasedTargetter launcher = mock(TimeBasedTargetter.class);
		ZeroLowerLeftProcessor processor = new ZeroLowerLeftProcessor(launcher);

		processor.process(new ZeroLowerLeftCommand());

		verify(launcher).left(6000L);
		verify(launcher).down(1500L);
	}
}
