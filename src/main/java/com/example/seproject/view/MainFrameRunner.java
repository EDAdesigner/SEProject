package com.example.seproject.view;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import javax.swing.*;

@Component
public class MainFrameRunner implements CommandLineRunner {
    private final MainFrame mainFrame;

    public MainFrameRunner(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    @Override
    public void run(String... args) throws Exception {
        SwingUtilities.invokeLater(() -> mainFrame.setVisible(true));
    }
}