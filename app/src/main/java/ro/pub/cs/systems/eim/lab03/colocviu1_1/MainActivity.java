package ro.pub.cs.systems.eim.lab03.colocviu1_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button north, east, west, south;
    private Button navigateToSecondaryActivityButton;
    private TextView textview, points;
    private IntentFilter intentFilter = new IntentFilter();
    int noPoints = 0;

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            String text = textview.getText().toString();
            if (text != "") {
                text = text + ", ";
            }

            switch(view.getId()) {
                case R.id.north:
                    text = text + getResources().getString(R.string.north);
                    textview.setText(text);
                    noPoints++;
                    break;
                case R.id.west:
                    text = text + getResources().getString(R.string.west);
                    textview.setText(text);
                    noPoints++;
                    break;
                case R.id.east:
                    text = text + getResources().getString(R.string.east);
                    textview.setText(text);
                    noPoints++;
                    break;
                case R.id.south:
                    text = text + getResources().getString(R.string.south);
                    textview.setText(text);
                    noPoints++;
                    break;
                case R.id.navigate_to_secondary_activity_button:
                    Intent intent = new Intent(getApplicationContext(), SecondaryActivity.class);
                    intent.putExtra("count", noPoints);
                    startActivityForResult(intent, 1);
                    break;
            }
            points.setText(String.valueOf(noPoints));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        north = (Button)findViewById(R.id.north);
        north.setOnClickListener(buttonClickListener);

        east = (Button)findViewById(R.id.east);
        east.setOnClickListener(buttonClickListener);

        west = (Button)findViewById(R.id.west);
        west.setOnClickListener(buttonClickListener);

        south = (Button)findViewById(R.id.south);
        south.setOnClickListener(buttonClickListener);

        textview = (TextView) findViewById(R.id.text);
        points = (TextView) findViewById(R.id.points);

        navigateToSecondaryActivityButton = (Button)findViewById(R.id.navigate_to_secondary_activity_button);
        navigateToSecondaryActivityButton.setOnClickListener(buttonClickListener);

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("count")) {
                points.setText(savedInstanceState.getString("count"));
                noPoints = Integer.parseInt(savedInstanceState.getString("count"));
            } else {
                points.setText(String.valueOf(0));
            }
        } else {
            points.setText(String.valueOf(0));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("count", String.valueOf(noPoints));
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState.containsKey("count")) {
            points.setText(savedInstanceState.getString("count"));
            noPoints = Integer.parseInt(savedInstanceState.getString("count"));
        } else {
            points.setText(String.valueOf(0));
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == 1) {
            Toast.makeText(this, "The activity returned with result " + resultCode, Toast.LENGTH_LONG).show();
        }
    }
}