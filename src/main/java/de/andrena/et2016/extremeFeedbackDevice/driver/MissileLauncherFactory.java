package de.andrena.et2016.extremeFeedbackDevice.driver;

import java.util.Optional;

public interface MissileLauncherFactory {
	Optional<MissileLauncher> findMissileLauncher();
}
