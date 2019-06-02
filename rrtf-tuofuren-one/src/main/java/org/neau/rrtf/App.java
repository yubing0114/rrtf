package org.neau.rrtf;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EntityScan("org.neau.rrtf.Entity")
@EnableEurekaClient//表明当前是一个Eureka客户端
@RestController
public class App 
{
    public static void main( String[] args )
    {
    	
    	
       SpringApplication.run(App.class, args);
     
    }
}
