package de.tallerik.kostuemgenerator.gui;

import de.tallerik.kostuemgenerator.Main;
import de.tallerik.kostuemgenerator.types.Answer;
import de.tallerik.kostuemgenerator.types.GameConfig;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/*
    @Project Kostümgenerator
    @Author Erik Liederbach

    Main GUI-Class

    Initialize JFrame
    Setup Game with Informations from GameConfig
    Runs Game and handle Points.
 */
public class GUI {
    // Frame Components init
    public JFrame frame;

    private JPanel panel;
    private JLabel question;
    private JButton weiterButton;
    private JButton schliesenButton;
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;
    private JRadioButton radioButton3;
    private JRadioButton radioButton4;
    private JPanel imgPanel;
    private JLabel title;

    private int currentQuestion = 0;
    private int owned_points = 0;

    // Temp storage for points for each possible RadioButton
    private int pointsfor1, pointsfor2, pointsfor3, pointsfor4;

    private GameConfig config;


    public GUI(GameConfig config) {
        this.config = config;
        // Setup JFrame
        frame = new JFrame(config.getName());
        frame.setSize(600, 450);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            frame.setIconImage(ImageIO.read(new File(config.getIcon())));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Show Frame
        // Now here because of a weird bug where the components got null
        frame.add(panel);
        frame.setVisible(true);

        // Setup Components
        updateComponents();

        // Next Question Button
        weiterButton.addActionListener(e -> {
            int points = getPointsforSelected();
            if(points == -1) {
                weiterButton.setText("Bitte beantworte die Frage");
                return;
            }
            owned_points += points;
            currentQuestion++;
            if(currentQuestion < config.getQuestions().size()) {
                updateComponents();
            } else {
                System.out.println("Finish: Points: " + owned_points);
                String text = "Kein Ergebniss deiner Punktzahl";
                String img = null;
                for(Answer a : config.getAnswers()) {
                    if(a.isInRange(owned_points)) {
                        text = a.getText();
                        if(a.isImageValid()) {
                            img  = a.getImg();
                        }
                        break;
                    }

                }
                frame.remove(panel);

                Answer a = config.getSpecificAnswer(owned_points);

                if(a != null) {
                    text = a.getText();
                    img = a.getImg();
                }
                if(img.equals("")) {
                    img = null;
                }

                GameEnd endp = new GameEnd(
                    "Ergebnisse der Fragen",
                    "Ergebnis",
                    text,
                    img,
                    //null,
                    () -> { // Restart Button
//                        //frame.removeAll();
//                        for (Component c : frame.getComponents()) {
//                            frame.remove(c);
//                        }
//                        currentQuestion = 0;
//                        owned_points = 0;
//                        updateComponents();
//                        frame.add(panel);
//                        frame.setMinimumSize(new Dimension(600, 450));
//                        frame.pack(); // Causes sizing issues
//                        frame.repaint();
//                        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                        Main.restart();
                    },
                    () -> { // Exit Button
                        frame.setVisible(false);
                        System.exit(0);
                    }
                );
                frame.add(endp.getPanel());

                frame.setMinimumSize(new Dimension(600, 450));
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                frame.pack(); // Causes sizing issues
                frame.repaint();
            }
        });

        // Button to close the GUI and stop the process
        schliesenButton.addActionListener(e -> {
            frame.setVisible(false);
            System.exit(0);
        });



    }

    /*
        Returns Points selected RadioButton. Returns -1 if no RadioButton is Selected
     */
    private int getPointsforSelected() {
        if(radioButton1.isSelected())
            return pointsfor1;
        if(radioButton2.isSelected())
            return pointsfor2;
        if(radioButton3.isSelected())
            return pointsfor3;
        if(radioButton4.isSelected())
            return pointsfor4;
        return -1;
    }

    /*
        Update all Components for next Question
     */
    private void updateComponents() {
        // Game Title
        title.setText(config.getName() + " (Frage: " + (currentQuestion + 1) + ": " + owned_points + "Pt)");
        // Question
        question.setText(config.getQuestions().get(currentQuestion).getText());
        // Reset Button Label
        weiterButton.setText("Weiter");
        //TODO: Set image

        // Deselect all Buttons
        radioButton1.setSelected(false);
        radioButton2.setSelected(false);
        radioButton3.setSelected(false);
        radioButton4.setSelected(false);

        // Set Labels and points for Radio Buttons
        int size = config.getQuestions().get(currentQuestion).getAnswers().size();
        if(size >= 1) {
            radioButton1.setText((String) config.getQuestions().get(currentQuestion).getAnswers().keySet().toArray()[0]);
            pointsfor1 = config.getQuestions().get(currentQuestion).getAnswers().get(radioButton1.getText());
        } else {
            pointsfor1 = 0;
            radioButton1.setVisible(false);
        }

        if(size >= 2) {
            radioButton2.setText((String) config.getQuestions().get(currentQuestion).getAnswers().keySet().toArray()[1]);
            pointsfor2 = config.getQuestions().get(currentQuestion).getAnswers().get(radioButton2.getText());
        } else {
            pointsfor2 = 0;
            radioButton2.setVisible(false);
        }

        if(size >= 3) {
            radioButton3.setText((String) config.getQuestions().get(currentQuestion).getAnswers().keySet().toArray()[2]);
            pointsfor3 = config.getQuestions().get(currentQuestion).getAnswers().get(radioButton3.getText());
        } else {
            pointsfor3 = 0;
            radioButton3.setVisible(false);
        }

        if(size >= 4) {
            radioButton4.setText((String) config.getQuestions().get(currentQuestion).getAnswers().keySet().toArray()[3]);
            pointsfor4 = config.getQuestions().get(currentQuestion).getAnswers().get(radioButton4.getText());
        } else {
            pointsfor4 = 0;
            radioButton4.setVisible(false);
        }
    }
    public void close() {
        frame.setVisible(false);
    }
}
