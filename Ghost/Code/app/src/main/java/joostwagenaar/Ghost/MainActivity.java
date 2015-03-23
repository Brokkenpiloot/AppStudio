package joostwagenaar.Ghost;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    Dictionary dictionary;
    String input;
    String word = "";
    EditText gameEditText;
    TextView gameSubmittedText;
    long isGameOver;
    TextView player1TextView;
    TextView player2TextView;
    int turnIterator= 0;
    Intent victory;
    String currentLanguage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Joost", "onCreate started");
        setContentView(R.layout.activity_main);
        gameEditText = (EditText) findViewById(R.id.gameEditText);
        gameSubmittedText = (TextView) findViewById(R.id.gameSubmittedText);
        player1TextView = (TextView) findViewById(R.id.player1TextView);
        player2TextView = (TextView) findViewById(R.id.player2TextView);


        //Check if the app was called by onReplayButtonClicked,
        //in which case the names of the previous game must be preserved.
        Intent replay = getIntent();
        if (replay.hasExtra("Victor")) {
            String previousVictor = replay.getStringExtra("Victor");
            String previousLoser = replay.getStringExtra("Loser");
            player1TextView.setText(previousVictor);
            player2TextView.setText(previousLoser);
            gameSubmittedText.setText("");
            player1TextView.setTypeface(null, Typeface.BOLD);

        }
        //In this case this activity was either launched,
        //or was called by onChangeNamesButtonClicked.

        //check if intent asks for language change.
        if (replay.hasExtra("Language")) {
            Log.d("Joost", "Intent extra 'Language' found.");
            String intentLanguage = replay.getStringExtra("Language");
            Log.d("Joost", "Intent Language: " + intentLanguage);
            if (intentLanguage.equals("english")) {
                dictionary = new Dictionary(getApplicationContext(), "english");
                currentLanguage ="english";
            }
            else if (intentLanguage.equals("dutch")) {
                dictionary = new Dictionary(getApplicationContext(), "dutch");
                currentLanguage = "dutch";
            }

        }
        else {
            Log.d("Joost", "Intent extra 'Language' not found.");
            dictionary = new Dictionary(getApplicationContext(), "english");
            currentLanguage = "english";
        }
        Toast.makeText(getApplicationContext(), "Current language selected: " + currentLanguage,
                Toast.LENGTH_SHORT).show();
    }


    public void onSubmitButtonClicked(View view) {
        Log.d("Joost", "Submit button clicked");
        input = gameEditText.getText().toString();
        if (input.length() != 1 || !input.matches("[a-zA-Z]")) {

            Log.d("Joost", "User did not fill in one letter");
            Toast.makeText(getApplicationContext(), "Please fill in one letter",
                    Toast.LENGTH_SHORT).show();
        }

        //Correct game input found, so game can continue.
        else {
            turnIterator++;
            gameEditText.setText("");
            input = input.toLowerCase();
            word = gameSubmittedText.getText().toString().concat(input);
            gameSubmittedText.setText(word);

            //Different rules apply when 0<word.length()<4, so we must check for that
            if (word.length() == 1 || word.length() == 2 || word.length() == 3) {
                Log.d("Joost", "Word = 1-3 characters");
                dictionary.filter(word);
                isGameOver = dictionary.counter(word);
                if (isGameOver == 0) {
                    Log.d("Joost", "word123: isGameOver == 0");

                    //Game is over, last user input loses. turnIterator helps us ascertain
                    //whose turn is was lastly.
                    if(turnIterator%2 == 0) {
                        Log.d("Joost", player1TextView.getText().toString() + " has won!");
                        victory = new Intent(this, VictoryActivity.class);
                        victory.putExtra("Victor", player1TextView.getText().toString());
                        victory.putExtra("Loser", player2TextView.getText().toString());
                        Scoreboard.scoreboardUpdater(this, player1TextView.getText().toString(),
                                player2TextView.getText().toString());
                        startActivity(victory);
                        finish();
                    }

                    else {
                        Log.d("Joost", player2TextView.getText().toString() + " has won!");
                        victory = new Intent(this, VictoryActivity.class);
                        victory.putExtra("Victor", player2TextView.getText().toString());
                        victory.putExtra("Loser", player1TextView.getText().toString());
                        startActivity(victory);
                        Scoreboard.scoreboardUpdater(this, player2TextView.getText().toString(),
                                player1TextView.getText().toString());
                        finish();
                    }
                }
                if (isGameOver == 1) {
                    if (dictionary.wordCompleted(word))
                        //Game is over, but no loser. Therefore this should not initiate
                        //cake screen, but should allow for a quick restart game.
                        Log.d("Joost", "word123: isGameOver == 1 and word completed");
                    else
                        //Game is not over yet.
                        Log.d("Joost", "word123: isGameOver == 1 but word not completed");
                }
            }
            if (word.length() > 3) {
                Log.d("Joost", "Word > 3 characters");
                dictionary.filter(word);
                isGameOver = dictionary.counter(word);
                if (isGameOver == 0) {
                    Log.d("Joost", "word>3: isGameOver == 0");
                    //Game is over, last user input loses.
                    if(turnIterator%2 == 0) {
                        Log.d("Joost", player1TextView.getText().toString() + " has won!");
                        victory = new Intent(this, VictoryActivity.class);
                        victory.putExtra("Victor", player1TextView.getText().toString());
                        victory.putExtra("Loser", player2TextView.getText().toString());
                        startActivity(victory);
                        Scoreboard.scoreboardUpdater(this, player1TextView.getText().toString(),
                                player2TextView.getText().toString());
                        finish();
                    }
                    else {
                        Log.d("Joost", player2TextView.getText().toString() + " has won!");
                        victory = new Intent(this, VictoryActivity.class);
                        victory.putExtra("Victor", player2TextView.getText().toString());
                        victory.putExtra("Loser", player1TextView.getText().toString());
                        startActivity(victory);
                        Scoreboard.scoreboardUpdater(this, player2TextView.getText().toString(),
                                player1TextView.getText().toString());
                        finish();
                    }
                }
                else if (isGameOver == 1) {
                    if (dictionary.wordCompleted(word)) {
                        //Game is over, last user input loses.
                        Log.d("Joost", "word>3: isGameOver == 1 and word completed");
                        if (turnIterator % 2 == 0) {
                            Log.d("Joost", player1TextView.getText().toString() + " has won!");
                            victory = new Intent(this, VictoryActivity.class);
                            victory.putExtra("Victor", player1TextView.getText().toString());
                            victory.putExtra("Loser", player2TextView.getText().toString());
                            startActivity(victory);
                            Scoreboard.scoreboardUpdater(this, player1TextView.getText().toString(),
                                    player2TextView.getText().toString());
                            finish();
                        }
                        else {
                            Log.d("Joost", player2TextView.getText().toString() + " has won!");
                            victory = new Intent(this, VictoryActivity.class);
                            victory.putExtra("Victor", player2TextView.getText().toString());
                            victory.putExtra("Loser", player1TextView.getText().toString());
                            startActivity(victory);
                            Scoreboard.scoreboardUpdater(this, player2TextView.getText().toString(),
                                    player1TextView.getText().toString());
                            finish();
                        }
                    }
                    else {
                        //Game is not over yet.
                        Log.d("Joost", "word>3: isGameOver == 1 but word not completed");
                    }
                }
                else {
                    Log.d("Joost", "word>3: More than 1 option left");
                }
            }

            // Makes sure the player's name whose turn it is is bold.
            if (turnIterator % 2 == 0){
                player1TextView.setTypeface(null, Typeface.BOLD);
                player2TextView.setTypeface(null, Typeface.NORMAL);
            }
            else {
                player1TextView.setTypeface(null, Typeface.NORMAL);
                player2TextView.setTypeface(null, Typeface.BOLD);
            }
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("Player 1", player1TextView.getText().toString());
        outState.putString("Player 2", player2TextView.getText().toString());
        outState.putString("Word", gameSubmittedText.getText().toString());
        outState.putInt("Turn", turnIterator);
        outState.putSerializable("Dictionary", dictionary);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        player1TextView.setText(savedInstanceState.getString("Player 1"));
        player2TextView.setText(savedInstanceState.getString("Player 2"));
        gameSubmittedText.setText(savedInstanceState.getString("Word"));
        turnIterator = savedInstanceState.getInt("Turn");
        if (turnIterator % 2 == 0){
            player1TextView.setTypeface(null, Typeface.BOLD);
            player2TextView.setTypeface(null, Typeface.NORMAL);
        }
        else {
            player1TextView.setTypeface(null, Typeface.NORMAL);
            player2TextView.setTypeface(null, Typeface.BOLD);
        }
        //dictionary = savedInstanceState.getSerializable("Dictionary");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            replay.putExtra("Victor", player1TextView.getText().toString());
            replay.putExtra("Loser", player2TextView.getText().toString());
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
