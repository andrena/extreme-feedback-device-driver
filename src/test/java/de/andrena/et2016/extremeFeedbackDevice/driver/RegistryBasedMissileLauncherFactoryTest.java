package de.andrena.et2016.extremeFeedbackDevice.driver;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;

public class RegistryBasedMissileLauncherFactoryTest {
	private RegistryBasedMissileLauncherFactory registry = new RegistryBasedMissileLauncherFactory();

	@Test
	public void testNoRegisteredFactoriesFindsNoMissileLauncher() {
		Optional<MissileLauncher> launcher = registry.findMissileLauncher();

		assertThat(launcher.isPresent(), is(false));
	}

	@Test
	public void testSingleRegisteredFactoryIsCalledOnFindMissileLauncher() {
		MissileLauncherFactory factory = mock(MissileLauncherFactory.class);
		when(factory.findMissileLauncher()).thenReturn(Optional.empty());
		registry.addFactory(factory);

		Optional<MissileLauncher> launcher = registry.findMissileLauncher();

		assertThat(launcher.isPresent(), is(false));
	}

	@Test
	public void testSingleRegisteredFactoryCallResultIsPassedOn() {
		MissileLauncher missileLauncher = mock(MissileLauncher.class);
		MissileLauncherFactory factory = mock(MissileLauncherFactory.class);
		when(factory.findMissileLauncher()).thenReturn(Optional.of(missileLauncher));
		registry.addFactory(factory);

		Optional<MissileLauncher> launcher = registry.findMissileLauncher();

		assertThat(launcher.isPresent(), is(true));
		assertThat(launcher.get(), is(missileLauncher));
	}

	@Test
	public void testForMultipleFactoriesTheFirstSuccessfulFactoryReturnsInstance() {
		MissileLauncherFactory firstFactory = mock(MissileLauncherFactory.class);
		when(firstFactory.findMissileLauncher()).thenReturn(Optional.empty());
		registry.addFactory(firstFactory);

		MissileLauncherFactory secondFactory = mock(MissileLauncherFactory.class);
		MissileLauncher missileLauncher = mock(MissileLauncher.class);
		when(firstFactory.findMissileLauncher()).thenReturn(Optional.of(missileLauncher));
		registry.addFactory(secondFactory);

		Optional<MissileLauncher> launcher = registry.findMissileLauncher();

		assertThat(launcher.isPresent(), is(true));
		assertThat(launcher.get(), is(missileLauncher));
	}

	@Test
	public void testForMultipleFactoriesFollowingFactoriesAreNotCalled() {
		MissileLauncherFactory firstFactory = mock(MissileLauncherFactory.class);
		MissileLauncher missileLauncher = mock(MissileLauncher.class);
		when(firstFactory.findMissileLauncher()).thenReturn(Optional.of(missileLauncher));
		registry.addFactory(firstFactory);

		MissileLauncherFactory secondFactory = mock(MissileLauncherFactory.class);
		when(secondFactory.findMissileLauncher()).thenReturn(Optional.empty());
		registry.addFactory(secondFactory);

		Optional<MissileLauncher> launcher = registry.findMissileLauncher();

		assertThat(launcher.isPresent(), is(true));
		assertThat(launcher.get(), is(missileLauncher));
	}
}
