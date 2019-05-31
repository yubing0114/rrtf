package org.rrtf.lesson;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

//尝试增加一条注释
@SpringBootApplication
public class App extends SpringBootServletInitializer {
    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
    }
    @Override//为了打包springboot项目
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }
}
