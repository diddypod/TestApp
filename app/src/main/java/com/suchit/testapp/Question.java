package com.suchit.testapp;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;

public class Question {

    public class Option {
        String optionText;
        boolean isCorrect;

        Option(String text, boolean correct) {
            optionText = text;
            isCorrect = correct;
        }

        public ArrayList<String> toArrayList(){
            ArrayList values = new ArrayList<String>();
            values.add(optionText);
            if (isCorrect){
                values.add("true");
            }
            else {
                values.add("false");
            }
            return values;
        }
    }

    private String subject;
    private String questionText;

    private boolean hasImage = false;
    private String imagePath = null;

    private ArrayList<Option> options = new ArrayList<>();

    public Question(JsonObject jsonObject){
        subject = jsonObject.get("subject").getAsString();
        questionText = jsonObject.get("text").getAsString();
        hasImage = jsonObject.get("image").getAsBoolean();
        if (hasImage) {
            imagePath = jsonObject.get("imagePath").getAsString();
        }
        JsonArray answersJsonArray = jsonObject.get("answers").getAsJsonArray();
        for (JsonElement element : answersJsonArray) {
            JsonObject answerJsonObject = element.getAsJsonObject();
            String text = answerJsonObject.get("text").getAsString();
            boolean correct = answerJsonObject.get("correct").getAsBoolean();
            options.add(new Option(text, correct));
        }
    }

    public String getQuestionText() {
        return questionText;
    }
    public boolean hasImage() {
        return hasImage;
    }
    public String getImagePath() {
        return imagePath;
    }
    public ArrayList<Option> getOptions() {
        return options;
    }
    public ArrayList<String> toArrayList(){
        ArrayList<String> values = new ArrayList<>();
        values.add(questionText);
        if (hasImage){
            values.add("true");
            values.add(imagePath);
        }
        else {
            values.add("false");
            values.add(null);
        }
        for (int i=0; i<4; i++){
            values.addAll(options.get(i).toArrayList());
        }
        return values;
    }
}