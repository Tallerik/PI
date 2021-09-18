package de.tallerik.kostuemgenerator.types;

import de.tallerik.kostuemgenerator.Main;

import java.util.*;

/*
    @Project Kostümgenerator
    @Author Erik Liederbach

    Specific Object to store one Question
 */
public class Question {
    String text;

    // String: Name
    // Integer: Gaining Points for this Answer
    Map<String, Integer> possibleAnswers;

    public Question(){
        possibleAnswers = new HashMap<String, Integer>();
        text = "";
    }

    public Question(String text) {
        possibleAnswers = new HashMap<String, Integer>();
        this.text = text;
    }

    public void setText(String text) {
        this.text = text;
    }

    // Add Answer to the list and check the size of possibleAnswers
    public boolean addPossibleAnswer(String name, int points) {
        if(possibleAnswers.size() <= 3 || !Main.FORCE_QUESTION_VALUE_COUNT) {
            possibleAnswers.put(name, points);
            return true;
        } else {
            return false;
        }
    }

    public Map<String, Integer> getAnswers() {
        return possibleAnswers;
    }

    /*
        Returns all Answers in a randomized order
        __Note__ Unchecked Cast in first line! Might not work
     */
    public Map<String, Integer> getRandomizedAnswers() {
        List<String> coll = (ArrayList<String>) possibleAnswers.keySet();
        Collections.shuffle(coll);
        Map<String, Integer> shuffled = new HashMap<String, Integer>();
        for(String key: coll) {
            shuffled.put(key, possibleAnswers.get(key));
        }
        return shuffled;
    }

    public int getAnswerCount() {
        return possibleAnswers.size();
    }

    public String getText() {
        return text;
    }
}
