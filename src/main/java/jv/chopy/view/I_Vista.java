package jv.chopy.view;

import jv.chopy.crud.controller.Controller;

public interface I_Vista<T> {
    T getObject();
    void setObject(T obj);
    void setController(Controller controller);
    void mostrar();

    void showMessage(String message);
    void showError(String message);
}
