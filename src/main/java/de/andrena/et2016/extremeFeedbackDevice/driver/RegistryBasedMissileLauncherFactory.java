package de.andrena.et2016.extremeFeedbackDevice.driver;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RegistryBasedMissileLauncherFactory implements MissileLauncherFactory, MissileLauncherFactoryRegistry {

	private List<MissileLauncherFactory> factories = new ArrayList<>();

	@Override
	public Optional<MissileLauncher> findMissileLauncher() {
		for (MissileLauncherFactory factory : factories) {
			Optional<MissileLauncher> result = factory.findMissileLauncher();
			if (result.isPresent())
				return result;
		}
		return Optional.empty();
	}

	@Override
	public void addFactory(MissileLauncherFactory factory) {
		this.factories.add(factory);
	}
}
