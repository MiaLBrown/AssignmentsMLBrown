package edu.harrisburgu.geoquiz;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.util.Log;
import android.app.Activity;


public class CheatActivity extends AppCompatActivity {
    private static final String TAG = "CheatActivity";
    private boolean mAnswerIsTrue;
    private TextView mAnswerTextView;
    public Button mShowAnswer;
    private static final String EXTRA_ANSWER_IS_TRUE =
            "edu.harrisburgu.android.geoquiz.answer_is_true";

    public static Intent newIntent (Context packageContext,boolean answerIsTrue){
        Intent i = new Intent(packageContext, CheatActivity.class);
        i.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
        mAnswerTextView = (TextView) findViewById(R.id.answer_text_view);
        mShowAnswer = (Button) findViewById(R.id.show_answer_button);
        mShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAnswerIsTrue) {
                    mAnswerTextView.setText(R.string.true_button);
                } else {
                    mAnswerTextView.setText(R.string.false_button);
                }
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result","Hello from Cheat Activity");
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });
        Intent i = getIntent();
        String message = getIntent().getStringExtra(GeoQuiz.EXTRA_MESSAGE);
        Log.d(TAG, message);

    }
}


