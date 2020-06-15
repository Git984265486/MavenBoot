package com.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan("com.boot.mapper")
@ServletComponentScan
public class MavenBootApplication extends SpringBootServletInitializer {

    public static void main(String args[]){
        SpringApplication.run( MavenBootApplication.class , args );
    }

    /**【打包war时初始化配置】**/
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
        return application.sources(MavenBootApplication.class);
    }

}
