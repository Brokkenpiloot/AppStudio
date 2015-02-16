package joostwagenaar.mrpotatohead;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    public ImageView body, arms, ears, eyebrows, eyes, glasses, hat, mouth, mustache, nose, shoes;
    public CheckBox cbarms, cbears, cbeyebrows, cbeyes, cbglasses, cbhat, cbmouth, cbmustache, cbnose, cbshoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        body = (ImageView) findViewById(R.id.image_body);
        arms = (ImageView) findViewById(R.id.image_arms);
        ears = (ImageView) findViewById(R.id.image_ears);
        eyebrows = (ImageView) findViewById(R.id.image_eyebrows);
        eyes = (ImageView) findViewById(R.id.image_eyes);
        glasses = (ImageView) findViewById(R.id.image_glasses);
        hat = (ImageView) findViewById(R.id.image_hat);
        mouth = (ImageView) findViewById(R.id.image_mouth);
        mustache = (ImageView) findViewById(R.id.image_mustache);
        nose = (ImageView) findViewById(R.id.image_nose);
        shoes = (ImageView) findViewById(R.id.image_shoes);
        cbarms = (CheckBox) findViewById(R.id.checkbox_arms);
        cbears = (CheckBox) findViewById(R.id.checkbox_ears);
        cbeyebrows = (CheckBox) findViewById(R.id.checkbox_eyebrows);
        cbeyes = (CheckBox) findViewById(R.id.checkbox_eyes);
        cbglasses = (CheckBox) findViewById(R.id.checkbox_glasses);
        cbhat = (CheckBox) findViewById(R.id.checkbox_hat);
        cbmouth = (CheckBox) findViewById(R.id.checkbox_mouth);
        cbmustache = (CheckBox) findViewById(R.id.checkbox_mustache);
        cbnose = (CheckBox) findViewById(R.id.checkbox_nose);
        cbshoes = (CheckBox) findViewById(R.id.checkbox_shoes);
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void checkbox_arms_clicked (View view){
        if (arms.isShown())
            arms.setVisibility(View.INVISIBLE);
        else
            arms.setVisibility(View.VISIBLE);
    }

    public void checkbox_ears_clicked(View view) {
        if (ears.isShown())
            ears.setVisibility(View.INVISIBLE);
        else
            ears.setVisibility(View.VISIBLE);
    }

    public void checkbox_eyebrows_clicked(View view) {
        if (eyebrows.isShown())
            eyebrows.setVisibility(View.INVISIBLE);
        else
            eyebrows.setVisibility(View.VISIBLE);
    }

    public void checkbox_eyes_clicked(View view) {
        if (eyes.isShown())
            eyes.setVisibility(View.INVISIBLE);
        else
            eyes.setVisibility(View.VISIBLE);
    }

    public void checkbox_glasses_clicked(View view) {
        if (glasses.isShown())
            glasses.setVisibility(View.INVISIBLE);
        else
            glasses.setVisibility(View.VISIBLE);
    }

    public void checkbox_hat_clicked(View view) {
        if (hat.isShown())
            hat.setVisibility(View.INVISIBLE);
        else
            hat.setVisibility(View.VISIBLE);
    }

    public void checkbox_mouth_clicked(View view) {
        if (mouth.isShown())
            mouth.setVisibility(View.INVISIBLE);
        else
            mouth.setVisibility(View.VISIBLE);
    }

    public void checkbox_mustache_clicked(View view) {
        if (mustache.isShown())
            mustache.setVisibility(View.INVISIBLE);
        else
            mustache.setVisibility(View.VISIBLE);
    }

    public void checkbox_nose_clicked(View view) {
        if (nose.isShown())
            nose.setVisibility(View.INVISIBLE);
        else
            nose.setVisibility(View.VISIBLE);
    }

    public void checkbox_shoes_clicked(View view) {
        if (shoes.isShown())
            shoes.setVisibility(View.INVISIBLE);
        else
            shoes.setVisibility(View.VISIBLE);
    }
}
