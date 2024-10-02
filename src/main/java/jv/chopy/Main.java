package jv.chopy;

import jv.chopy.view.MainView;

public class Main {
    public static void main(String[] args) {
        try {
            new MainView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}