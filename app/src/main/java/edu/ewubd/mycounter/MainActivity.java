package edu.ewubd.mycounter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textId;
    private Button btnStart, btnReset, btnPause, btnExit;
    boolean runApp = false;
    boolean pause = false;
    private int counterId = 2019160243;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textId = findViewById(R.id.tvId);
        btnStart = findViewById(R.id.btnStart);
        btnReset = findViewById(R.id.btnReset);
        btnPause = findViewById(R.id.btnPause);
        btnExit = findViewById(R.id.btnExit);

        btnExit.setOnClickListener(view -> finish());

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                runApp = true ;
                CountMyId countMyId = new CountMyId();
                countMyId.execute(runApp);
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counterId = 2019160243;
                textId.setText(String.valueOf(counterId));
                runApp = false;
            }
        });

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pause == true) {
                    pause = false;
                } else {
                    pause = true;
                }
            }
        });
    }

    class CountMyId extends AsyncTask<Boolean, Integer, Integer> {

        @Override
        protected Integer doInBackground(Boolean... booleans) {
            while (runApp) {
                if (pause == false) {
                    counterId--;
                    publishProgress(counterId);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            textId.setText(String.valueOf(counterId));
        }
    }
}