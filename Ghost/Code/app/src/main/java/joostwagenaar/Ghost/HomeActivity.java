package joostwagenaar.Ghost;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends ActionBarActivity {

    String player1;
    String player2;
    EditText player1EditText;
    EditText player2EditText;
    TextView languageIndicatorTextView;
    Intent startGame;
    String replayLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        player1EditText = (EditText) findViewById(R.id.player1EditText);
        player2EditText = (EditText) findViewById(R.id.player2EditText);
        languageIndicatorTextView = (TextView) findViewById(R.id.languageIndicator);

        //Create intent for starting the game.
        startGame = new Intent(this, MainActivity.class);

        //Check if intent was passed, and if it asks for language change.
        Intent replay = getIntent();
        if (replay.hasExtra("Language")) {
            Log.d("Joost", "Home: Intent extra 'Language' found.");
            replayLanguage = replay.getStringExtra("Language");
            Log.d("Joost", "Home:  Language: " + replayLanguage);
            if (replayLanguage.equals("english")) {
                startGame.putExtra("Language", "english");
                languageIndicatorTextView.setText(languageIndicatorTextView.getText().toString().
                    concat(" " + replayLanguage));
            }
            else if (replayLanguage.equals("dutch")) {
                startGame.putExtra("Language", "dutch");
                languageIndicatorTextView.setText(languageIndicatorTextView.getText().toString().
                    concat(" " + replayLanguage));
            }

        }
        else {
            Log.d("Joost", "Home: Intent extra 'Language' not found.");
            startGame.putExtra("Language", "english");
            languageIndicatorTextView.setText(languageIndicatorTextView.getText().toString()
                + " english");
            replayLanguage = "english";
        }

    }

    public void onStartGameButtonClicked(View view) {
        player1 = player1EditText.getText().toString();
        player2 = player2EditText.getText().toString();
        if(player1.equals("") || player2.equals("")){
            Toast.makeText(getApplicationContext(), "Please insert player names.",
                    Toast.LENGTH_SHORT).show();
        }
        else{
            startGame.putExtra("Victor", player1);
            startGame.putExtra("Loser", player2);
            startActivity(startGame);
            finish();
        }
    }

    public void onRulesButtonClicked(View view) {
        Uri rules = Uri.parse("http://en.wikipedia.org/wiki/Ghost_(game)");
        Intent openRulesPage = new Intent(Intent.ACTION_VIEW, rules);
        startActivity(openRulesPage);
    }

    public void onChangeLanguageButtonClicked(View view) {
        Intent changeLanguage;
        if (replayLanguage.equals("english")) {
            changeLanguage = new Intent(this, HomeActivity.class);
            changeLanguage.putExtra("Language", "dutch");
        }
        else {
            changeLanguage = new Intent(this, HomeActivity.class);
            changeLanguage.putExtra("Language", "english");
        }
        startActivity(changeLanguage);
        finish();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("Language", replayLanguage);
        outState.putString("Player 1", player1EditText.getText().toString());
        outState.putString("Player 2", player2EditText.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        replayLanguage = savedInstanceState.getString("Language");
        player1EditText.setText(savedInstanceState.getString("Player 1"));
        player1EditText.setText(savedInstanceState.getString("Player 1"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.scoreboardMenuItem) {
            Intent scoreboard = new Intent (this, ScoreboardActivity.class);
            startActivity(scoreboard);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }


}
