package by.it.grechishnikov.matLab.controller;


public class ReaderFromConsoleImplIReadable extends Reader{

    @Override
    public String readValue() {
        System.out.println("Введите уравнение или end для завершения программы: ");
        return readStringFromConsole();
    }
}