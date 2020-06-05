package com.boot;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.boot.mapper")
public class MavenBootApplication {

    public static void main(String args[]){
        SpringApplication.run( MavenBootApplication.class , args );
    }

}
