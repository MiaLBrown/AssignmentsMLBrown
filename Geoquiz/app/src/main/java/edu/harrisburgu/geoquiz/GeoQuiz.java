package edu.harrisburgu.geoquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import android.widget.TextView;
import android.content.Intent;
import android.view.View;
import android.util.Log;
import androidx.annotation.Nullable;



public class GeoQuiz extends AppCompatActivity {

    public Button mTrueButton;
    public Button mFalseButton;
    public Button mNextButton;
    public Button mCheatButton;
    public TextView mQuestionTextView;

    public TextView mScoreTextView;
    public TextView mUsesTextView;
    public boolean mAnswerIsTrue;

    public static final String TAG = "GeoQuiz";

    private static final String KEY_INDEX = "index";
    private static final String KEY_SCORE = "score";

    private static final String KEY_USES = "uses";
    public static final String EXTRA_MESSAGE = "edu.harrisburgu.geoquiz";
    private static final String EXTRA_ANSWER_IS_TRUE = "edu.harrisburgu.android.geoquiz.answer_is_true";
    private static final int REQUEST_CODE_CHEAT = 0;
    public SharedPreferences prefs;
    public SharedPreferences.Editor editor;
    public int totalCount =0;
    private int score =0;




    private final Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_australia, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true),
            new Question(R.string.question_sahara, false),
            new Question(R.string.question_tuvalu,true),
            new Question(R.string.question_afghanistan, true),
            new Question(R.string.question_france, false),
            new Question(R.string.question_australia2, true),
            new Question(R.string.question_congo, true),

    };
    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

        mQuestionTextView = findViewById(R.id.question_text_view);
        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){ checkAnswer(true);}
        });

        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){ checkAnswer(false);}
        });

        mCheatButton = findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start cheat
                Intent i = CheatActivity.newIntent(GeoQuiz.this,
                        mQuestionBank[mCurrentIndex].isAnswerTrue());
                String message = "Hello From MainActivity";
                i.putExtra(EXTRA_MESSAGE, message);
                startActivityForResult(i, REQUEST_CODE_CHEAT);


            }
        });

        mNextButton = findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                int question = mQuestionBank[mCurrentIndex].getTextResId();
                mQuestionTextView.setText(question);
                // if question goes back to first question increment uses
                if (mCurrentIndex ==0){
                    totalCount++;
                    mUsesTextView.setText(getString(R.string.uses_output) + " " + totalCount);
                }
            }

        });
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        mScoreTextView = (TextView) findViewById(R.id.score_text_view);
        mUsesTextView = (TextView) findViewById(R.id.uses_text_view);

        prefs = getPreferences(Context.MODE_PRIVATE);
        editor = prefs.edit();

        if (savedInstanceState == null){
            totalCount = prefs.getInt(KEY_USES, 0);
            totalCount++;
            editor.putInt(KEY_USES, totalCount);
            editor.commit();
        }
        else
        {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
            score = savedInstanceState.getInt(KEY_SCORE,0);
            totalCount = savedInstanceState.getInt(KEY_USES, 0);
        }
        mUsesTextView.setText(getString(R.string.uses_output) + " " + totalCount);
        mScoreTextView.setText(getString(R.string.score_output) + " " + score);
        Log.d(TAG, "Total count: " + totalCount);
        Log.d(TAG, "Score: " + score);
        updateQuestion();
    }
    public void startCheatActivity(boolean answerIsTrue) {
        answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
        Intent cheatIntent = CheatActivity.newIntent(GeoQuiz.this, answerIsTrue);
        startActivity(cheatIntent);
    }

    private void updateQuestion() {
        int questionResId = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(getResources().getString(questionResId));

    }
    private void updateScore() {
        mScoreTextView.setText(getString(R.string.score_output) + " " + score);

    }

    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
        int messageResId;

        if (userPressedTrue == answerIsTrue) {
            messageResId = R.string.correct_toast;
            score++;
            updateScore();// Increment the score for correct answers
        } else {
            messageResId = R.string.incorrect_toast;
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHEAT) {
            Log.d(TAG, Integer.toString(requestCode));
            if (null != data && data.getStringExtra("result") != null) {
                int messageResId = R.string.judgement_toast;
                Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
            }
        }
    }
            @Override
            public void onStart(){
                super.onStart();
                Log.d(TAG, "onStart() called");

            }

            @Override
            public void onPause() {
                super.onPause();
                Log.d(TAG, "onPause() called");
            }

            @Override
            public void onResume() {
                super.onResume();
                Log.d(TAG, "onResume() called");
            }

            @Override
            public void onStop() {
                super.onStop();
                Log.d(TAG, "onStop() called");
            }

            @Override
            public void onDestroy() {
                super.onDestroy();
                Log.d(TAG, "onDestroy() called");
            }
        }


