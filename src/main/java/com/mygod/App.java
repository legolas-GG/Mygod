package com.mygod;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@EnableAutoConfiguration
@SpringBootApplication
public class App
{
    static Logger Log = Logger.getLogger(App.class);

    public static void main( String[] args ) {
        Log.info("开始服务");
        SpringApplication.run(App.class, args);
        Log.info("结束服务");
    }
}
