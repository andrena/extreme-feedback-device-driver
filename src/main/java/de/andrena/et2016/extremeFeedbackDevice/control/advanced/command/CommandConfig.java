package de.andrena.et2016.extremeFeedbackDevice.control.advanced.command;

import de.andrena.et2016.extremeFeedbackDevice.control.advanced.AdvancedControlMissileLauncher;
import de.andrena.et2016.extremeFeedbackDevice.control.advanced.command.down.DownCommand;
import de.andrena.et2016.extremeFeedbackDevice.control.advanced.command.down.DownProcessor;
import de.andrena.et2016.extremeFeedbackDevice.control.advanced.command.fire.FireCommand;
import de.andrena.et2016.extremeFeedbackDevice.control.advanced.command.fire.FireProcessor;
import de.andrena.et2016.extremeFeedbackDevice.control.advanced.command.left.LeftCommand;
import de.andrena.et2016.extremeFeedbackDevice.control.advanced.command.left.LeftProcessor;
import de.andrena.et2016.extremeFeedbackDevice.control.advanced.command.processor.CommandProcessorRegistry;
import de.andrena.et2016.extremeFeedbackDevice.control.advanced.command.processor.CommandProcessors;
import de.andrena.et2016.extremeFeedbackDevice.control.advanced.command.right.RightCommand;
import de.andrena.et2016.extremeFeedbackDevice.control.advanced.command.right.RightProcessor;
import de.andrena.et2016.extremeFeedbackDevice.control.advanced.command.up.UpCommand;
import de.andrena.et2016.extremeFeedbackDevice.control.advanced.command.up.UpProcessor;
import de.andrena.et2016.extremeFeedbackDevice.control.advanced.command.zero.ZeroLowerLeftCommand;
import de.andrena.et2016.extremeFeedbackDevice.control.advanced.command.zero.ZeroLowerLeftProcessor;

public class CommandConfig {
	public CommandProcessors createCommandProcessorsUsing(AdvancedControlMissileLauncher launcher) {
		CommandProcessors processors = new CommandProcessors();
		registerCommandProcessors(processors, launcher);
		return processors;
	}

	private void registerCommandProcessors(CommandProcessorRegistry processors, AdvancedControlMissileLauncher launcher) {
		processors.register(ZeroLowerLeftCommand.class, new ZeroLowerLeftProcessor(launcher));
		processors.register(UpCommand.class, new UpProcessor(launcher));
		processors.register(DownCommand.class, new DownProcessor(launcher));
		processors.register(LeftCommand.class, new LeftProcessor(launcher));
		processors.register(RightCommand.class, new RightProcessor(launcher));
		processors.register(FireCommand.class, new FireProcessor(launcher));
	}
}
