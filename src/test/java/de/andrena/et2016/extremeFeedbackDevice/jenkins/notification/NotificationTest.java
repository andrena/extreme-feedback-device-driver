package de.andrena.et2016.extremeFeedbackDevice.jenkins.notification;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class NotificationTest {
	@Test
	public void testMapping() throws Exception {
		String data = "{\"name\":\"extreme-feedback-device-driver\",\"url\":\"job/extreme-feedback-device-driver/\",\"build\":{\"full_url\":\"http://docker-vm:8080/job/extreme-feedback-device-driver/11/\",\"number\":11,\"queue_id\":11,\"phase\":\"COMPLETED\",\"status\":\"UNSTABLE\",\"url\":\"job/extreme-feedback-device-driver/11/\",\"scm\":{\"url\":\"http://docker-vm:8081/root/extreme-feedback-device-driver.git\",\"branch\":\"origin/master\",\"commit\":\"5709288ff0d6972b2014026a68f5f0006f68b66d\"},\"artifacts\":{\"target/extreme-feedback-device-listener.jar\":{\"archive\":\"http://docker-vm:8080/job/extreme-feedback-device-driver/11/artifact/target/extreme-feedback-device-listener.jar\"},\"target/extreme-feedback-device-driver-0.0.1-SNAPSHOT.jar\":{\"archive\":\"http://docker-vm:8080/job/extreme-feedback-device-driver/11/artifact/target/extreme-feedback-device-driver-0.0.1-SNAPSHOT.jar\"}}}}";

		ObjectMapper objectMapper = new ObjectMapper();
		Notification notification = objectMapper.readValue(data, Notification.class);

		assertThat(notification.getBuild().getStatus(), is("UNSTABLE"));
		assertThat(notification.getBuild().getFullUrl(),
				is("http://docker-vm:8080/job/extreme-feedback-device-driver/11/"));
	}
}
