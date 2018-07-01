package com.suchit.testapp.data;

import android.util.Log;

public class Result {
    
    public static Result currentResult;

    private String name;
    private String grade;

    private int scienceMarks = 0;
    private int mathMarks = 0;
    private int englishMarks = 0;
    private int scienceFullMarks = 0;
    private int mathFullMarks = 0;
    private int englishFullMarks = 0;
    private int fullMarks = 0;
    private int timeLeftSeconds = 0;
    private int fullTimeSeconds = 1200;

    public void evaluateTest(Test test, int seconds){
        name = test.getName();
        grade = (test.getGrade()+3)+"th";
        timeLeftSeconds = seconds;
        for (Question question: test.getQuestions()){
            switch (question.getSubject()) {
                case "math":
                    mathFullMarks++;
                    if (question.answeredCorrectly()) {
                        mathMarks++;
                    }
                    break;
                case "science":
                    scienceFullMarks++;
                    if (question.answeredCorrectly()) {
                        scienceMarks++;
                    }
                    break;
                case "english":
                    englishFullMarks++;
                    if (question.answeredCorrectly()) {
                        englishMarks++;
                    }
                    break;
            }
            fullMarks++;
        }
    }
    public int getEnglishMarks() {
        return englishMarks;
    }
    public int getMathMarks() {
        return mathMarks;
    }
    public int getScienceMarks() {
        return scienceMarks;
    }
    public int getTotalMarks() {
        return scienceMarks + mathMarks + englishMarks;
    }
    public void setFullMarks(Test test){
        for (Question question: test.getQuestions()){
            fullMarks++;
        }
    }
    public float getTotalPercentage(){
        Log.d("SC",scienceMarks+"");
        Log.d("MA",mathMarks+"");
        Log.d("EN",englishMarks+"");
        return ((float) (scienceMarks + mathMarks + englishMarks)) / ((float) fullMarks) * 100;
    }
    public float getSciencePercentage(){
        return ((float) (scienceMarks)) / ((float) scienceFullMarks) * 100;
    }
    public float getMathPercentage(){
        return ((float) (mathMarks)) / ((float) mathFullMarks) * 100;
    }
    public float getEnglishPercentage(){
        return ((float) (englishMarks)) / ((float) englishFullMarks) * 100;
    }

    public String getName() {
        return name;
    }
    public String getGrade() {
        return grade;
    }
    public int getTimeLeft() {
        return timeLeftSeconds;
    }
    public int getFullTime() {
        return fullTimeSeconds;
    }
    public float getTimePercentage(){
        return ((float) timeLeftSeconds) / ((float) fullTimeSeconds) * 100;
    }
}
