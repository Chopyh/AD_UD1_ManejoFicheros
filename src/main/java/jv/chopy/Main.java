package jv.chopy;

import jv.chopy.crud.controller.Controller;
import jv.chopy.view.MainView;

public class Main {
    public static void main(String[] args) {
        try {
            Controller controller = new Controller();
            new MainView(controller);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}