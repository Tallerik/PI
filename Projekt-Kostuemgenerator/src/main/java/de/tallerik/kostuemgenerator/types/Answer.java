package de.tallerik.kostuemgenerator.types;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
/*
    @Project Kostümgenerator
    @Author Erik Liederbach

    Specific Object to store one Answer
    Also includes function to test if points are in specific
    range for this answer
 */
public class Answer {
    private int min;
    private int max;
    private String text;
    private String img;

    public Answer() {}

    public Answer(int min, int max, String text, String img) {
        this.min = min;
        this.max = max;
        this.text = text;
        this.img = img;
    }

    // Setter
    public void setText(String text) {
        this.text = text;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void setMin(int min) {
        this.min = min;
    }

    // Getter
    public int getMax() {
        return max;
    }

    public int getMin() {
        return min;
    }

    public String getImg() {
        return img;
    }

    public String getText() {
        return text;
    }

    // Special
    public boolean isInRange(int number) {
        return number >= min && number <= max;
    }

    public boolean isImageValid() {
        File f = new File(img);
        if(f.exists() && f.isFile()) {
            return true;
        }
        try {
            URL url = new URL(img);
            return true;
        } catch (MalformedURLException ignored) {}

        return false;
    }
}
