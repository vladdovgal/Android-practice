package com.dovhal.p0331sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    SharedPreferences sPref;
    final String SAVED_TEXT = "saved text";
    EditText etText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etText = findViewById(R.id.etText);
        findViewById(R.id.btnSave).setOnClickListener(this);
        findViewById(R.id.btnLoad).setOnClickListener(this);
        loadText();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSave:
                saveText();
                break;
            case R.id.btnLoad:
                loadText();
                break;
            default:
                break;
        }
    }

    private void saveText() {
        /* SharedPreferences object allows to work with data (read and write)
         M0DE_PRIVATE is access constant, means that after saving data will be
         available only in current application */
        sPref = getPreferences(MODE_PRIVATE);
        /* Modifications to the preferences must go through an Editor object to ensure
         the preference values remain in a consistent state and control when they
         are committed to storage */
        SharedPreferences.Editor editor = sPref.edit();
        /* SAVED_TEXT is constant(name of variable, key) */
        editor.putString(SAVED_TEXT, etText.getText().toString());
        /*   apply() or commit() to save data */
        editor.apply();
        Toast.makeText(this, "Text saved", Toast.LENGTH_SHORT).show();
    }

    private void loadText() {
        sPref = getPreferences(MODE_PRIVATE);
        /* Getting value by name of variable */
        String savedText = sPref.getString(SAVED_TEXT, "");
        etText.setText(savedText);
        Toast.makeText(this, "Text loaded", Toast.LENGTH_SHORT).show();
    }
}
