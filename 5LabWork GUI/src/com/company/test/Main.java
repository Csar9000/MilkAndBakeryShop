package com.company.test;

import javax.swing.*;


public class Main {

    public static void main(String[] args)
    {
        // Создание и настройка окна
        JFrame frame = new JFrame("Магазин");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                (new InterFace()).createPanelUI(frame.getContentPane());


                // Открытие окна
                frame.setSize(1000,500);
                frame.setVisible(true);
            }
        });
    }
}

