package in.net.codestar.androlibrary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    private static final String JOKE_NOT_FOUND = "Joke not found... LOL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        TextView jokeTextView = (TextView) findViewById(R.id.joke_text_view);

        Intent intent = getIntent();
        if (intent.hasExtra("joke_string")) {
            jokeTextView.setText(intent.getStringExtra("joke_string"));
        }
        else {
            jokeTextView.setText(JOKE_NOT_FOUND);
        }
    }
}
