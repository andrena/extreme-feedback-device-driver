package de.andrena.et2016.extremeFeedbackDevice;

import java.io.File;

import de.andrena.et2016.extremeFeedbackDevice.control.advanced.CommandSequenceExecutor;

public class App {
	public static void main(String[] args) throws Exception {
		File controlSequencesDefinitionFile = new File(args[0]);
		String controlSequenceName = args[1];
		new CommandSequenceExecutor(controlSequencesDefinitionFile).runControlSequence(controlSequenceName);
	}
}