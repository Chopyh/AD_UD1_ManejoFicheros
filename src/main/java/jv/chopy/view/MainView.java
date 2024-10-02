package jv.chopy.view;

import jv.chopy.crud.utils.PropertiesLoader;

public class MainView {
    public MainView() {
        if (PropertiesLoader.getProperty("app.export").equals("none")){
            ExportSelector.showExportSelectorDialog();
        }

    }
}
