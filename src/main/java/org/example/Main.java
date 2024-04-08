package org.example;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame jFrame=new JFrame("Jeu du Taquin");
        jFrame.setSize(1200,500);
        jFrame.setLocationRelativeTo(null);
        jFrame.add(new Layout().getPanel());
        jFrame.setVisible(true);
    }
}