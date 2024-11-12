package com.creditcard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import brave.sampler.Sampler;

@SpringBootApplication
public class CreditCardServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CreditCardServiceApplication.class, args);
	}

	@Bean
	public Sampler defaultSampler() {
		return ALWAYS_SAMPLE;
	}

	private static final Sampler ALWAYS_SAMPLE = new Sampler() {
		@Override
		public boolean isSampled(long traceId) {
			return true;
		}

		@Override
		public String toString() {
			return "AlwaysSample";
		}
	};

}
