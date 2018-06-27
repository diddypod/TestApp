package com.suchit.testapp.data;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;

public class Question {

    public class Option {
        String optionText;
        boolean isCorrect;
        boolean chosen = false;

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

        public void setChosen() {
            this.chosen = true;
        }
        public boolean isCorrect() {
            return isCorrect;
        }
    }

    private String subject;
    private String questionText;
    private String imagePath = null;

    private boolean hasImage;
    private boolean answered = false;

    private ArrayList<Option> options = new ArrayList<>();

    public Question(JsonObject jsonObject){
        subject = jsonObject.get("subject").getAsString();
        questionText = jsonObject.get("text").getAsString();
        hasImage = jsonObject.get("image").getAsBoolean();
        if (hasImage) {
            imagePath = jsonObject.get("imageName").getAsString();
        }
        JsonArray answersJsonArray = jsonObject.get("answers").getAsJsonArray();
        for (JsonElement element : answersJsonArray) {
            JsonObject answerJsonObject = element.getAsJsonObject();
            String text = answerJsonObject.get("text").getAsString();
            boolean correct = answerJsonObject.get("correct").getAsBoolean();
            options.add(new Option(text, correct));
        }
    }

    public ArrayList<String> toArrayList(){
        ArrayList<String> values = new ArrayList<>();
        values.add("");
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

    public ArrayList<Option> getOptions() {
        return options;
    }

    public void resetOptions(){
        for (Option i: options){
            i.chosen = false;
        }
    }
}