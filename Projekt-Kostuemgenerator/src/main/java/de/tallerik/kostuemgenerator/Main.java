package de.tallerik.kostuemgenerator;

import de.tallerik.kostuemgenerator.gui.GUI;

import javax.swing.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/*
    @Project Kostümgenerator
    @Author Erik Liederbach

    Main class

    Contains Main-Method
    Sets Look&Feel
    Reads JSON from File and process it
    Starts GUI instance

    TODO: Make UI look nicer
    TODO: Randomize Answers direction
    TODO: Add images to the Frame
    TODO: Add Question-Specific image to the Frame
    TODO: Clean Up Data-Types
 */
public class Main {

    // Should an error be thrown if a Question does not have
    // exactly 4 Possible Answers
    public static final boolean FORCE_QUESTION_VALUE_COUNT = true;

    private static GUI gui;
    private static DataReader reader;

    public static void main(String[] args) throws IOException {
        // Look and Feel
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch(Exception ignored){}

        // Get JSON
        String jsonContent = Files.readString(Paths.get("src", "main", "resources", "data.json"), StandardCharsets.UTF_8);

        // Handle JSON and get Data
        reader = new DataReader(jsonContent);

        // Start GUI
        gui = new GUI(reader.getResult());
    }
    public static void restart() {
        gui.close();
        gui = new GUI(reader.getResult());
    }
}
