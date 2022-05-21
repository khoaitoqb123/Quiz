package hoangquocnam.example.quiz;

import androidx.appcompat.app.AppCompatActivity;import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

        private TextView answertv;
        private Button btntrue,btnfalse;
        private ImageButton btnprevious, btnnext;

        private int currentQuestionIndex = 0;

        private Question[] questionBank = new Question[] {
                new Question(R.string.Question1,true),
                new Question(R.string.Question2,true),
                new Question(R.string.Question3,false),
                new Question(R.string.Question4,true),
                new Question(R.string.Question5,false),
                new Question(R.string.Question6,false),
                new Question(R.string.Question7,false)
        };

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            answertv = (TextView) findViewById(R.id.answertv);
            btntrue = (Button) findViewById(R.id.true_button);
            btnfalse = (Button) findViewById(R.id.false_button);
            btnprevious = (ImageButton) findViewById(R.id.previous_button);
            btnnext = (ImageButton) findViewById(R.id.next_button);

            btntrue.setOnClickListener(this);
            btnfalse.setOnClickListener(this);
            btnprevious.setOnClickListener(this);
            btnnext.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            switch (view.getId())
            {
                case R.id.true_button:
                    checkAnswer(true);
                    break;

                case R.id.false_button:
                    checkAnswer(false);
                    break;

                case R.id.next_button:
                    currentQuestionIndex = (currentQuestionIndex + 1) % questionBank.length;
                    updateQuestion();
                    break;

                case R.id.previous_button:
                    if(currentQuestionIndex > 0) {
                        currentQuestionIndex = (currentQuestionIndex - 1) % questionBank.length;
                        updateQuestion();
                    }
                    break;
            }
        }

        private void updateQuestion() {
            Log.d("current", "onclick" + currentQuestionIndex);
            answertv.setText(questionBank[currentQuestionIndex].getAnswerResID());
        }

        private void checkAnswer(boolean userChoosenCorrect) {
            boolean answerIsTrue = questionBank[currentQuestionIndex].isAnswerTrue();

            int toastMessageId = 0;
            if(userChoosenCorrect == answerIsTrue)
                toastMessageId = R.string.correct;
            else
                toastMessageId = R.string.wrong;
            Toast.makeText(MainActivity.this, toastMessageId, Toast.LENGTH_SHORT).show();
        }
    }