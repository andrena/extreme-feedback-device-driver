package de.andrena.et2016.extremeFeedbackDevice.control.advanced.command.fire;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;

import de.andrena.et2016.extremeFeedbackDevice.control.advanced.launcher.MultiLauncher;

public class FireProcessorTest {
	@Test
	public void testFire() {
		MultiLauncher launcher = mock(MultiLauncher.class);
		FireProcessor processor = new FireProcessor(launcher);

		processor.process(new FireCommand(42));

		verify(launcher).fire(42);
	}
}
