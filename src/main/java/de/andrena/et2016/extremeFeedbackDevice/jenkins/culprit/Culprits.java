package de.andrena.et2016.extremeFeedbackDevice.jenkins.culprit;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Culprits {
	public static class Culprit {
		private String fullName;

		public String getFullName() {
			return fullName;
		}

		public void setFullName(String fullName) {
			this.fullName = fullName;
		}
	}

	private List<Culprit> culprits;

	@JsonIgnore
	public Set<String> getFullNames() {
		return culprits.stream()
				.map(culprit -> culprit.getFullName())
				.collect(Collectors.toSet());
	}

	public List<Culprit> getCulprits() {
		return culprits;
	}

	public void setCulprits(List<Culprit> culprits) {
		this.culprits = culprits;
	}
}
