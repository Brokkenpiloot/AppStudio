package joostwagenaar.laughder;

/**
 * Created by startklaar on 6-1-2016.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.parse.Parse;
import com.parse.ParseObject;


public class MatchActivity extends AppCompatActivity {

    Intent logOut;
    Intent viewProfile;
    Intent editProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Joost", "Main onCreate started");
        setContentView(R.layout.activity_match);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_match, menu);
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

        if (id == R.id.log_out) {
            logOut = new Intent (this, MainActivity.class);
            startActivity(logOut);
            finish();
        }

        if (id == R.id.view_profile) {
            viewProfile = new Intent (this, BrowseActivity.class);
            viewProfile.putExtra("Joost", "username");
            startActivity(viewProfile);
            finish();
        }

        if (id == R.id.edit_profile) {
            editProfile = new Intent (this, EditProfileActivity.class);
            startActivity(editProfile);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
