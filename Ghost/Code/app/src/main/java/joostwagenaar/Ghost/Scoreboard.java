package joostwagenaar.Ghost;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by startklaar on 20-3-2015.
 */
public class Scoreboard {



    public static void scoreboardUpdater(Context context, String winner, String loser) {
        //Update score by either iterating existing high score, or adding
        //new entry with score = 1.
        SharedPreferences prefs = context.getSharedPreferences("Scores", Context.MODE_PRIVATE);
        SharedPreferences.Editor scoreboardUpdaterEditor = prefs.edit();

        //Check for winner, or create entry.
        if(prefs.contains(winner)){
            Log.d("Joost", "Existing score updated");
            int score = (prefs.getInt(winner, 0));
            score++;
            scoreboardUpdaterEditor.putInt(winner, score);
            scoreboardUpdaterEditor.commit();
        }
        else{
            Log.d("Joost", "New score entry created");
            scoreboardUpdaterEditor.putInt(winner, 1);
            scoreboardUpdaterEditor.commit();
        }

        //Check for loser, or create entry.
        if(prefs.contains(loser)){
            Log.d("Joost", "Loser already existed");
        }
        else{
            Log.d("Joost", "Loser entry entered");
            scoreboardUpdaterEditor.putInt(loser, 0);
            scoreboardUpdaterEditor.commit();
        }


    }


}
