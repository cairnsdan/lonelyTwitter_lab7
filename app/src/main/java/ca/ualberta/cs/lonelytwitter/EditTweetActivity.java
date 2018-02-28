package ca.ualberta.cs.lonelytwitter;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;

public class EditTweetActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_tweet);

        Intent intent = getIntent();
        String message = intent.getStringExtra("message");

        TextView displayField = (TextView) findViewById(R.id.textView);

        displayField.setText(message);
    }

}
