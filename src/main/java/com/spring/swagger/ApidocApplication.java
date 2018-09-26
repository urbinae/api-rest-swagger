package com.spring.swagger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableSwagger2
public class ApidocApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApidocApplication.class, args);
	}
	
	/*public Docket api() {
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
	}*/
}
