package jv.chopy.view;

import jv.chopy.crud.utils.PropertiesLoader;

import java.util.Scanner;

public class ExportSelector {
    public static void showExportSelectorDialog() {
        Scanner scanner = new Scanner(System.in);
        int option;

        System.out.println("============ EXPORT SELECTOR ============");
        System.out.println("""
                Choose your export method:
                1. Binary Object
                2. Binary Secuential
                3. Binary Random
                4. Text File
                5. JSON File
                6. XML File""");

        do {
            try {
                option = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid option, please try again");
                option = 0;
            }
            if(option < 1 || option > 6) System.out.println("Invalid option, please try again");
        } while (option < 1 || option > 6);

        setExportMethod(option);
    }

    private static void setExportMethod(int option) {
        switch (option) {
            case 1 -> {
                PropertiesLoader.setProperty("app.export", "binaryObject");
                System.out.println("Binary Object selected");
            }
            case 2 -> {
                PropertiesLoader.setProperty("app.export", "binarySecuential");
                System.out.println("Binary Secuential selected");
            }
            case 3 -> {
                PropertiesLoader.setProperty("app.export", "binaryRandom");
                System.out.println("Binary Random selected");
            }
            case 4 -> {
                PropertiesLoader.setProperty("app.export", "textFile");
                System.out.println("Text File selected");
            }
            case 5 -> {
                PropertiesLoader.setProperty("app.export", "jsonFile");
                System.out.println("JSON File selected");
            }
            case 6 -> {
                PropertiesLoader.setProperty("app.export", "xmlFile");
                System.out.println("XML File selected");
            }
        }
    }
}
