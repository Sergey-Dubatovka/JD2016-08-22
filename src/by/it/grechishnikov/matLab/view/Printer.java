package by.it.grechishnikov.matLab.view;

import by.it.grechishnikov.matLab.controller.Runner;
import by.it.grechishnikov.matLab.model.*;

import java.io.*;
import java.util.Map;

/**
 * Created by Evgeniy on 27.09.16.
 */
public abstract class Printer implements IPrintable {
    public void serializeMap(String path) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path))) {
            for(Map.Entry<String, Var> pair : Runner.storage.entrySet()) {
                out.writeObject(pair.getValue());
            }
            System.out.println("Данные записаны");
        } catch (IOException e) {
            System.out.println("Ошибка");
            Runner.logger.log("Ошибка");
        }
    }

    public void printLine(String message) {
        System.out.println(message);
    }
}
