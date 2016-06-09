package de.andrena.et2016.extremeFeedbackDevice.jenkins.culprit;

import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CulpritsTest {
	@Test
	public void testName() throws Exception {
		String data = "{\"culprits\":[{\"fullName\":\"fabian.knittel\"}]}";

		ObjectMapper objectMapper = new ObjectMapper();
		Culprits culprits = objectMapper.readValue(data, Culprits.class);

		assertThat(culprits.getFullNames(), contains("fabian.knittel"));
	}
}
