package View;

import Controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NomenclatureView extends JFrame {


    NomenclatureView(String s){
            super (s);
            setSize(1000,150);
            setVisible(true);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setLayout(new BorderLayout());
            JButton b = new JButton("OK");
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Controller.nomen = null;
                    dispose();
                }
            });

            add(b,BorderLayout.SOUTH);

            InterFace.tableNomen = new JTable(new Controller.MyTableFromDBWNomenclature());
            add(new JScrollPane(InterFace.tableNomen), BorderLayout.CENTER);
    }
}
