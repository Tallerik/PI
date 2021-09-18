package de.tallerik.kostuemgenerator.gui;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class GameEnd {
    private JPanel endpanel;
    private JButton restartButton;
    private JButton closeButton;
    private JPanel imagePanel;
    private JLabel resultat;
    private JLabel title;
    private JLabel finalText;

    public GameEnd(String title, String resultat, String finalText, String image, Runnable restart, Runnable close) {

        this.title.setText(title);
        this.resultat.setText(resultat);
        this.finalText.setText("<html><body>" + finalText + "</body></html>");
        if(image != null) {
            ImageIcon img = new ImageIcon(image);
            JLabel imageLabel = new JLabel(img);
            //imageLabel.setBounds(10, 10, 400, 400);
            imageLabel.setVisible(true);
            GridLayout lay = new GridLayout();
            lay.addLayoutComponent("img", imageLabel);
            imagePanel.setLayout(lay);

        }
        restartButton.addActionListener(a -> restart.run());
        closeButton.addActionListener(a -> close.run());

    }
    public JPanel getPanel() {
        return endpanel;
    }
}
