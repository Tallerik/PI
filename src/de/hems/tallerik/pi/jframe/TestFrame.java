package de.hems.tallerik.pi.jframe;

import javax.swing.*;

public class TestFrame {
    private JPanel panel;
    private JLabel lab;

    private JFrame frame;
    public TestFrame() throws InterruptedException {
        frame = new JFrame();
        panel = new JPanel();
        lab = new JLabel("Hey");
        panel.add(lab);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();

        Thread.sleep(1000);
        lab.setText("Geändert");
    }

    public static void main(String[] args) {
        try {
            new TestFrame();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
