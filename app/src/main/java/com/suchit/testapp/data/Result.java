package com.suchit.testapp.data;

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
    private int visualCorrect = 0;
    private int visualFull = 0;
    private int textCorrect = 0;
    private int textFull = 0;

    private float schoolSciencePercentage = 0;
    private float schoolMathPercentage = 0;
    private float schoolEnglishPercentage = 0;

    public void evaluateTest(Test test, int seconds){
        name = test.getName();
        grade = (test.getGrade()+3)+"th";
        schoolSciencePercentage = test.getSchoolScienceMarks();
        schoolEnglishPercentage = test.getSchoolEnglishMarks();
        schoolMathPercentage = test.getSchoolMathMarks();
        timeLeftSeconds = seconds;
        for (Question question: test.getQuestions()){
            if (question.hasImage()){
                visualFull++;
            }
            else {
                textFull++;
            }
            switch (question.getSubject()) {
                case "math":
                    mathFullMarks++;
                    if (question.answeredCorrectly()) {
                        mathMarks++;
                        if (question.hasImage()){
                            visualCorrect++;
                        }
                        else {
                            textCorrect++;
                        }
                    }
                    break;
                case "science":
                    scienceFullMarks++;
                    if (question.answeredCorrectly()) {
                        scienceMarks++;
                        if (question.hasImage()){
                            visualCorrect++;
                        }
                        else {
                            textCorrect++;
                        }
                    }
                    break;
                case "english":
                    englishFullMarks++;
                    if (question.answeredCorrectly()) {
                        englishMarks++;
                        if (question.hasImage()){
                            visualCorrect++;
                        }
                        else {
                            textCorrect++;
                        }
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
    public float getTotalPercentage(){
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
    public float getTimePercentage(){
        return ((float) timeLeftSeconds) / ((float) fullTimeSeconds) * 100;
    }
    public float getVisualCorrectPercentage() {
        return ((float) visualCorrect) / ((float) visualFull) * 100;
    }
    public float getTextCorrectPercentage() {
        return ((float) textCorrect) / ((float) textFull) * 100;
    }
    public float getSchoolEnglishPercentage() {
        return schoolEnglishPercentage;
    }
    public float getSchoolMathPercentage() {
        return schoolMathPercentage;
    }
    public float getSchoolSciencePercentage() {
        return schoolSciencePercentage;
    }
    public float getSchoolFullPercentage() {
        return (schoolEnglishPercentage + schoolMathPercentage + schoolSciencePercentage) / 3;
    }
    public int getFullMarks() {
        return fullMarks;
    }
    public int getEnglishFullMarks() {
        return englishFullMarks;
    }
    public int getMathFullMarks() {
        return mathFullMarks;
    }
    public int getScienceFullMarks() {
        return scienceFullMarks;
    }
    public int getFullTimeSeconds() {
        return fullTimeSeconds;
    }
    public int getTimeLeftSeconds() {
        return timeLeftSeconds;
    }
}
