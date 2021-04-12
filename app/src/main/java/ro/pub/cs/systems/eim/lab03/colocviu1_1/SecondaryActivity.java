package ro.pub.cs.systems.eim.lab03.colocviu1_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondaryActivity extends AppCompatActivity {

    private TextView points;
    private Button okButton, cancelButton;

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.register:
                    setResult(RESULT_OK, null);
                    break;
                case R.id.cancel:
                    setResult(RESULT_CANCELED, null);
                    break;
            }
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_secondary);

        points = (TextView)findViewById(R.id.points);
        Intent intent = getIntent();
        if (intent != null && intent.getExtras().containsKey("count")) {
            int numberOfClicks = intent.getIntExtra("points", -1);
            points.setText(String.valueOf(numberOfClicks));
        }

        okButton = (Button)findViewById(R.id.register);
        okButton.setOnClickListener(buttonClickListener);
        cancelButton = (Button)findViewById(R.id.cancel);
        cancelButton.setOnClickListener(buttonClickListener);
    }
}