package com.quizeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.quizeapp.custom.CustomActivity;
/**
 * This class provide Question list according your topic and provide answer as option of every question
 */

public class Question extends CustomActivity {
    /**
     * The TextViews for answer of question
     */
    TextView ans1, ans2, ans3, ans4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question);
        setUi();

    }

    /**
     * This method initialize all ui element and set click listener on UI elements
     */
    public void setUi() {
        ans1 = (TextView) findViewById(R.id.ans1);
        ans2 = (TextView) findViewById(R.id.ans2);
        ans3 = (TextView) findViewById(R.id.ans3);
        ans4 = (TextView) findViewById(R.id.ans4);

        setTouchNClick(R.id.ans1);
        setTouchNClick(R.id.ans2);
        setTouchNClick(R.id.ans3);
        setTouchNClick(R.id.ans4);
    }

    @Override
    public void onClick(View v) {
        if (v == ans1) {
            finish();
            startActivity(new Intent(Question.this, WinOrLoose.class));
        } else {
            finish();
            startActivity(new Intent(Question.this, Logos.class));
        }
        super.onClick(v);

    }
}
