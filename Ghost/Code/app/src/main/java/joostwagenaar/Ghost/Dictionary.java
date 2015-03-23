package joostwagenaar.Ghost;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by startklaar on 2-3-2015.
 */
public class Dictionary implements Serializable{

    HashSet wordList = new HashSet();
    String wordToCheck;
    long counter;


    Dictionary(Context context, String language) {
        InputStream inputStream;

        //Check if a language preference was specified. If not, use default.
        if(language.equals("english")) {
            inputStream = context.getResources().openRawResource(R.raw.english);
            Log.d("Joost", "English selected");
        }
        else if(language.equals("dutch")) {
            inputStream = context.getResources().openRawResource(R.raw.dutch);
            Log.d("Joost", "Dutch selected");
        }
        else{
            inputStream = context.getResources().openRawResource(R.raw.english);
            Log.d("Joost", "Unsupported language, default (English) selected");
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        Log.d("Joost", "Attempting to construct the dictionary");
        try {
            String line = bufferedReader.readLine();
            while (line != null){
                wordList.add(line);
                line = bufferedReader.readLine();
            }

        } catch (IOException e){
            wordList.clear();
            Log.d("Joost", "Construction failed");
        }
    Log.d("Joost", "Construction complete");
    }

    void filter(String word) {
        Log.d("Joost", "Filter: Entered");
        Iterator iterator = wordList.iterator();
        while(iterator.hasNext()){
            wordToCheck = iterator.next().toString();
            if(!wordToCheck.startsWith(word)) {
                iterator.remove();
            }
        }
        Log.d("Joost", "Filter: Completed");
    }

    long counter(String word) {
        counter = 0;
        Log.d("Joost", "Counter: Entered");
        Iterator iterator = wordList.iterator();
        while(iterator.hasNext()) {
            counter++;
            iterator.next();
        }
        Log.d("Joost", "Counter: Completed with count: " + counter);
        return counter;
    }

    boolean wordCompleted(String word) {
        Log.d("Joost", "wordCompleted: Entered");
        return wordList.contains(word);

    }
}
