package org.barbaris.generator.controllers;

import java.io.*;

public class FileWriter {
    public static boolean writeFile(String el, String filePath) {
        try(FileOutputStream fos = new FileOutputStream(filePath, true)) {
            byte[] buffer = el.getBytes();
            fos.write(buffer, 0, buffer.length);
            return true;
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
}
