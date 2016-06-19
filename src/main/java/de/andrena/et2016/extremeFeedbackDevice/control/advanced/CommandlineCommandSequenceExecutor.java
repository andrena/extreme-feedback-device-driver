package de.andrena.et2016.extremeFeedbackDevice.control.advanced;

import java.io.File;

public class CommandlineCommandSequenceExecutor {
	public static void main(String[] args) throws Exception {
		File controlSequencesDefinitionFile = new File(args[0]);
		String controlSequenceName = args[1];
		new CommandSequenceExecutor(controlSequencesDefinitionFile).runControlSequence(controlSequenceName);
	}
}