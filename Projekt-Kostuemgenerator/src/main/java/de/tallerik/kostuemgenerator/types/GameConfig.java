package de.tallerik.kostuemgenerator.types;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/*
    @Project Kostümgenerator
    @Author Erik Liederbach

    Object which stores the configuration defined in data.json
 */
public class GameConfig {
    private List<Question> questions;
    private List<Answer> answers;
    private String name;
    private String icon;

    public GameConfig() {
        name = "";
        questions = new ArrayList<Question>();
        answers = new ArrayList<Answer>();
    }

    public GameConfig(String name) {
        this.name = name;
        questions = new ArrayList<Question>();
        answers = new ArrayList<Answer>();
    }

    public GameConfig(String name, List<Question> questions, List<Answer> answers) {
        this.name = name;
        this.questions = questions;
        this.answers = answers;
    }

    public void addQuestion(Question q) {
        questions.add(q);
    }

    public void addAnswer(Answer a) {
        answers.add(a);
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public String getName() {
        return name;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }

    // Returns image As ImageIcon. Might be Null
    public ImageIcon getImageIcon() {
        return new ImageIcon(icon);
    }

    public Answer getSpecificAnswer(int points) {
        for (Answer a : answers) {
            if(a.isInRange(points)) {
                return a;
            }
        }
        return null;
    }
}
