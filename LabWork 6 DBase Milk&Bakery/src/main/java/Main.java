

import Controller.Controller;
import View.InterFace;
import db.HibernateUtil;
import db.Nomenclature;
import db.WareHouse;
import model.BakeryProductClass;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.swing.*;


public class Main {
    public static void main(String[] args) {

        Controller.getWarehouseFromDB();

        if(Controller.l.generalList.size()!=0) {
            Controller.temp = Controller.l.generalList.get(Controller.l.generalList.size()-1).getIdAct();
        }else{ Controller.temp=0.000;}


        // Создание и настройка окна
        JFrame frame = new JFrame("Магазин");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                (new InterFace()).createPanelUI(frame.getContentPane());


                // Открытие окна
                frame.setSize(1000, 600);
                frame.setVisible(true);
            }
        });
    }
}
