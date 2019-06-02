package org.rrtf.flcb;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RestController;

@EnableEurekaClient//表明当前是一个Eureka客户端
@RestController
@SpringBootApplication
public class App extends SpringBootServletInitializer {
	public static void main( String[] args ) {
    	SpringApplication.run(App.class, args);
	}
    @Override//为了打包springboot项目
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
    	return builder.sources(this.getClass());
	}
}
