package carbook;

import java.io.UnsupportedEncodingException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

import carbook.vnpay.VnPayConfig;

@SpringBootApplication
@EnableScheduling

public class RuncarbookApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(RuncarbookApplication.class, args);
	
	
		/*
		 * try { System.out.print(VnPayConfig.createStringQuery()); } catch
		 * (UnsupportedEncodingException e) { // TODO Auto-generated catch block
		 * System.out.print("hihihi"); }
		 */

	}
}