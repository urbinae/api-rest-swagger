package com.interfell.apidoc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.StringVendorExtension;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by eimar on 21/09/18.
 */
@SpringBootApplication
@Configuration
@EnableSwagger2
public class ApidocApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ApidocApplication.class, args);
	}

	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.interfell.apidoc.services"))
				.paths(PathSelectors.any()).build().enable(true).apiInfo(apiInfo());
	}

	public ApiInfo apiInfo() {
		StringVendorExtension vendorExtension = new StringVendorExtension("", "");
		Collection<VendorExtension> vendorExtensions = new ArrayList<>();
		vendorExtensions.add(vendorExtension);

		Contact contactInfo = new Contact("Eimar Urbina", "www.github.com/urbinae", "eimar.urbina@gmail.com");

		return new ApiInfo("API-REST-Swagger2-JaxRS",
				"Prueba Interfell Java:"
						+ "web app using jaxrs with swagger, and SpringBoot.",
				"1.0", "For Test only", contactInfo, "Apache 2.0", "www.apache.org", vendorExtensions);
	}

}
