package joostwagenaar.Ghost;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Iterator;

import joostwagenaar.Ghost.R;


public class VictoryActivity extends ActionBarActivity {

    Intent victory;
    String victor;
    String loser;
    String intentLanguage;
    TextView victoryMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_victory);
        victory = getIntent();
        victor = victory.getStringExtra("Victor");
        loser = victory.getStringExtra("Loser");
        intentLanguage = victory.getStringExtra("Language");
        victoryMessage = (TextView) findViewById(R.id.victoryMessage);
        victoryMessage.setText(victoryMessage.getText().toString().replace("PLAYER", victor));
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

    public void onScoreboardButtonClicked(View view) {
        Intent scoreboard = new Intent (this, ScoreboardActivity.class);
        startActivity(scoreboard);
        finish();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("Victory message", victoryMessage.getText().toString());
        outState.putString("Victor", victor);
        outState.putString("Loser", loser);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        victoryMessage.setText(savedInstanceState.getString("Victory message"));
        victor = savedInstanceState.getString("Victor");
        loser = savedInstanceState.getString("Loser");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_victory, menu);
        return true;
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

        if (id == R.id.scoreboardMenuItem) {
            Intent scoreboard = new Intent (this, ScoreboardActivity.class);
            startActivity(scoreboard);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
