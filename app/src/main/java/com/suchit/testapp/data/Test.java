package com.suchit.testapp.data;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class Test {
    public static Test currentTest;

    private String name;
    private String email;
    private String phone;

    private int grade;
    private int numberAnswered = 0;

    private float schoolMathMarks;
    private float schoolScienceMarks;
    private float schoolEnglishMarks;

    private ArrayList<Question> questions = new ArrayList<>();

    public void setLoginDetails(String name, String email, String phone, long grade) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.grade = (int) grade;
    }

    public void setMarks(float mathMarks, float scienceMarks, float englishMarks) {
        schoolMathMarks = mathMarks;
        schoolScienceMarks = scienceMarks;
        schoolEnglishMarks = englishMarks;
    }

    public int getGrade() {
        return grade;
    }

    public String getName() {
        return name;
    }

    public void setQuestions(Context context) {
        JsonArray jsonArray = getQuestionsFromJson(context).getAsJsonArray("questions");
        for (JsonElement jsonElement : jsonArray) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            questions.add(new Question(jsonObject));
        }
    }
    @Nullable
    private JsonObject getQuestionsFromJson(Context context) {
        try (InputStream is = context.getAssets().open("questions.json")) {
            JsonParser parser = new JsonParser();
            return parser.parse(new InputStreamReader(is)).getAsJsonObject();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
        }
        return null;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public boolean allDone(){
        for (Question question: questions){
            if (!question.isAnswered()){
                return false;
            }
        }
        return true;
    }

    public float getSchoolEnglishMarks() {
        return schoolEnglishMarks;
    }
    public float getSchoolMathMarks() {
        return schoolMathMarks;
    }
    public float getSchoolScienceMarks() {
        return schoolScienceMarks;
    }
}
