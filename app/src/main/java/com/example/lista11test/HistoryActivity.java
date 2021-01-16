package com.example.lista11test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    ArrayList<Msg> messages;
    public static final String FOLDERNAME = "MessagesHistory";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        // Lookup the recyclerview in activity layout
        RecyclerView rvMessages  = (RecyclerView) findViewById(R.id.rvMsg);

        //Initialize messages
        messages = this.createHistory();
        // Create adapter passing in the sample user data
        MsgAdapter adapter = new MsgAdapter(messages);
        // Attach the adapter to the recyclerview to populate items
        rvMessages.setAdapter(adapter);
        //Set layout manager to position items
        rvMessages.setLayoutManager(new LinearLayoutManager(this));
    }


    public ArrayList<Msg> createHistory(){

        ArrayList<Msg> messages = new ArrayList<Msg>();

        Context context = getApplicationContext();
        String folder = context.getFilesDir().getAbsolutePath() + File.separator + FOLDERNAME;
        File file = new File(folder);
        String[] directories = file.list((current, name) -> name.endsWith(".txt"));


        for (int i = 0; i <directories.length ; i++) {

            try {

                FileInputStream fileInputStream = new FileInputStream(folder+File.separator+directories[i]);
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                BufferedReader br = new BufferedReader(inputStreamReader);

                String line;

                while ((line = br.readLine()) != null) {
                    messages.add(new Msg(line, directories[i].substring(0, directories[i].length()-5)));
                }
               // messages.add(new Msg("udalo sie znalezc plik",":)"));


            } catch (FileNotFoundException e) {
                Log.e("blad", e.toString());
            } catch (IOException e) {
                Log.e("blad", e.toString());
            }
        }

        return messages;
    }



}