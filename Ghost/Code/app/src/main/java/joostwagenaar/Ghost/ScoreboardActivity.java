package joostwagenaar.Ghost;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.renderscript.Int2;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Map;
import java.util.Set;

import joostwagenaar.Ghost.R;

public class ScoreboardActivity extends ActionBarActivity {

    Intent victory;
    String victor;
    String loser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);
        victory = getIntent();
        if(victory.getStringExtra("Victor") != null) {
            victor = victory.getStringExtra("Victor");
            loser = victory.getStringExtra("Loser");
        }

        TextView highScoreTextView = (TextView) findViewById(R.id.highScores);
        SharedPreferences highScores = getSharedPreferences("Scores", MODE_PRIVATE);
        Map<String, ?> allHighScores = highScores.getAll();
        int numberOfHighsScores = allHighScores.size();
        Log.d("Joost", "Number of high scores found: " + numberOfHighsScores);
        int highScoreIterator = 0;

        //only lines I don't understand as of yet.
        //Set<Map.Entry<String, ?>> set = allHighScores.entrySet();
        for(Map.Entry<String, ?> highScore : allHighScores.entrySet()) {

            //get a value, and insert it into a newline in the text view.
            //high scores are not yet sorted.
            Log.d("Joost", "Scoreboard loop entered");
            highScoreTextView.setText(highScoreTextView.getText().toString() +
                    "\n" + highScore.getKey() + ": " + highScore.getValue());
            highScoreIterator++;
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scoreboard, menu);
        return true;
    }


    public void onReplayButtonClicked(View view) {
        Intent replay = new Intent(this, MainActivity.class);
        replay.putExtra("Victor", victor);
        replay.putExtra("Loser", loser);
        startActivity(replay);
        finish();
    }


    public void onChangeNamesButtonClicked(View view) {
        Intent changeNames = new Intent(this, HomeActivity.class);
        startActivity(changeNames);
        finish();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("Victor", victor);
        outState.putString("Loser", loser);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        victor = savedInstanceState.getString("Victor");
        loser = savedInstanceState.getString("Loser");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.replayMenuItem) {
            Intent replay = new Intent(this, MainActivity.class);
            replay.putExtra("Victor", victor);
            replay.putExtra("Loser", loser);
            startActivity(replay);
            finish();

        }

        if (id == R.id.changeNamesMenuItem) {
            Intent changeNames = new Intent(this, HomeActivity.class);
            startActivity(changeNames);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


}
