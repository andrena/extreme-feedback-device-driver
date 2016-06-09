package de.andrena.et2016.extremeFeedbackDevice.control.advanced;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.andrena.et2016.extremeFeedbackDevice.control.advanced.adapter.ManualToAdvancedMissileLauncherAdapterConfig;
import de.andrena.et2016.extremeFeedbackDevice.control.advanced.command.CommandConfig;
import de.andrena.et2016.extremeFeedbackDevice.control.advanced.command.processor.CommandProcessors;
import de.andrena.et2016.extremeFeedbackDevice.control.advanced.sequence.ControlSequenceProcessor;
import de.andrena.et2016.extremeFeedbackDevice.control.advanced.sequence.ControlSequences;
import de.andrena.et2016.extremeFeedbackDevice.control.advanced.sequence.ControlSequencesProcessor;
import de.andrena.et2016.extremeFeedbackDevice.driver.FeedbackDeviceFactory;
import de.andrena.et2016.extremeFeedbackDevice.driver.RegistryBasedFeedbackDeviceFactory;
import de.andrena.et2016.extremeFeedbackDevice.driver.dummy.DummyRegisteringConfig;
import de.andrena.et2016.extremeFeedbackDevice.driver.thunder.ThunderRegisteringConfig;

public class CommandSequenceExecutor {
	private ControlSequencesProcessor controlSequencesProcessor;

	public CommandSequenceExecutor(File controlSequencesDefinitionFile) {
		FeedbackDeviceFactory registry = createFactory();
		AdvancedControlMissileLauncher advancedControl = getLauncher(registry, AdvancedControlMissileLauncher.class);

		CommandProcessors commandProcessors = new CommandConfig().createCommandProcessorsUsing(advancedControl);
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerSubtypes(commandProcessors.getCommandClassesArray());

		ControlSequenceProcessor sequenceProcessor = new ControlSequenceProcessor(commandProcessors);
		ControlSequences sequences = createControlSequences(objectMapper, controlSequencesDefinitionFile);
		controlSequencesProcessor = new ControlSequencesProcessor(sequenceProcessor, sequences);
	}

	public void runControlSequence(String controlSequenceName) {
		controlSequencesProcessor.processSequence(controlSequenceName);
	}

	private ControlSequences createControlSequences(ObjectMapper objectMapper, File controlSequencesDefinitionFile) {
		try {
			return objectMapper.readValue(controlSequencesDefinitionFile, ControlSequences.class);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private <T> T getLauncher(FeedbackDeviceFactory registry, Class<T> supportedInterface) {
		Optional<T> launcherOptional = registry.findFeedbackDeviceSupportingControlInterface(supportedInterface);
		if (!launcherOptional.isPresent()) {
			throw new RuntimeException("Missile launcher not found.");
		}
		return launcherOptional.get();
	}

	private FeedbackDeviceFactory createFactory() {
		RegistryBasedFeedbackDeviceFactory registryBasedFactory = new RegistryBasedFeedbackDeviceFactory();
		new ThunderRegisteringConfig(registryBasedFactory);
		new DummyRegisteringConfig(registryBasedFactory);
		new ManualToAdvancedMissileLauncherAdapterConfig(registryBasedFactory, registryBasedFactory);
		return registryBasedFactory;
	}
}