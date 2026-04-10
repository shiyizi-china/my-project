package org.shiyizi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.servlet.context.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
@MapperScan("org.shiyizi.mapper")
@ComponentScan(basePackages = "org.shiyizi")
public class OurLittleMysteryWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(OurLittleMysteryWebApplication.class, args);
    }

}
