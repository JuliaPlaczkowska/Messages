package com.example.lista11test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;

public class MainActivity extends AppCompatActivity {

    public static final String FOLDERNAME = "MessagesHistory";
    public static final String TAG = "nie udalo sie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onSendMessage(View view) {
        EditText editText = (EditText) findViewById(R.id.editMessage);
        String message = editText.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, message);
        startActivity(intent);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            LocalDate today = LocalDate.now();
            String FILENAME = (today.toString()+".txt");

            Context context = getApplicationContext();
            String folder = context.getFilesDir().getAbsolutePath() + File.separator + FOLDERNAME;
            File subFolder = new File(folder);

            if (!subFolder.exists()) subFolder.mkdirs();

            try (FileOutputStream os = new FileOutputStream(new File(subFolder, FILENAME),true)) {
                os.write((message+"\n").getBytes());

            } catch (FileNotFoundException e) {
                Log.e(TAG, e.toString());
            } catch (IOException e) {
                Log.e(TAG, e.toString());
            }

        }


    }

    public void onHistory(View view){
        Intent intent2 = new Intent(this, HistoryActivity.class);
        startActivity(intent2);

    }
}