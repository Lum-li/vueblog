package com.markerhub.vueblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//为什么启动类的位置必须是com.marKerhub下面
@SpringBootApplication
public class VueblogApplication {

    public static void main(String[] args) {
        SpringApplication.run(VueblogApplication.class, args);
    }

}
