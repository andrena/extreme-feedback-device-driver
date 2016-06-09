package de.andrena.et2016.extremeFeedbackDevice.jenkins.notification;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Notification {
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Build {
		private String status;
		private String fullUrl;

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		@JsonProperty("full_url")
		public String getFullUrl() {
			return fullUrl;
		}

		public void setFullUrl(String full_url) {
			this.fullUrl = full_url;
		}
	}

	private Build build;

	public Build getBuild() {
		return build;
	}

	public void setBuild(Build build) {
		this.build = build;
	}
}
