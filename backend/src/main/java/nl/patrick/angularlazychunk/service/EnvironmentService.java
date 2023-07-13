package nl.patrick.angularlazychunk.service;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class EnvironmentService {

	private final Environment environment;

	public EnvironmentService(Environment environment) {
		this.environment = environment;
	}

	/**
	 * @return if this is development to make sure the non-minified scripts and
	 * css files are rendered.
	 */
	public boolean isDevelopment() {
		return Arrays.stream(environment.getActiveProfiles())
				.anyMatch(profile -> profile.contains("dev"));
	}
}
