package de.andrena.et2016.extremeFeedbackDevice.control.advanced.command.processor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.andrena.et2016.extremeFeedbackDevice.control.advanced.command.Command;

public class CommandProcessors implements CommandProcessorRegistry {

	private Map<Class<?>, CommandProcessor<?>> cache = new HashMap<>();

	@SuppressWarnings("unchecked")
	public void processCommand(Command command) {
		Class<? extends Command> commandClass = command.getClass();
		@SuppressWarnings("rawtypes")
		CommandProcessor processor = cache.get(commandClass);
		if (processor == null) {
			throw new UnknownCommandException("Unknown command " + command);
		}
		processor.process(command);
	}

	@Override
	public <T extends Command> void register(Class<T> commandClass, CommandProcessor<T> processor) {
		cache.put(commandClass, processor);
	}

	public Class<?>[] getCommandClassesArray() {
		List<Class<?>> commandClassesList = new ArrayList<>(cache.keySet());
		Class<?>[] commandClassesArray = new Class<?>[commandClassesList.size()];
		return commandClassesList.toArray(commandClassesArray);
	}
}
