package com.example.seproject;

import com.example.seproject.view.MainFrame;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SeProjectApplication {

    public static void main(String[] args) {
        new MainFrame();
        SpringApplication.run(SeProjectApplication.class, args);
    }

}
