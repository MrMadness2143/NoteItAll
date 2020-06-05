package com.example.noteitall;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import java.util.Calendar;

public class AddNote extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Toolbar toolbar;
    EditText noteTitle,noteDetails;
    CheckBox noteFavourite;
    SeekBar noteImportance;
    String importanceValue;
    Calendar c;
    Spinner noteType;
    String todaysDate;
    String currentTime;
    String txtType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);//uses layout
        toolbar = findViewById(R.id.toolbar);   //assigns toolbar variable
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);   //uses toolbar
        getSupportActionBar().setTitle("New Note"); //changes title
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        c = Calendar.getInstance(); //sets up calendar instance
        //dedicates calendar variables to readable strings
        todaysDate = c.get(Calendar.YEAR)+"/"+(c.get(Calendar.MONTH)+1)+"/"+c.get(Calendar.DAY_OF_MONTH);
        currentTime = pad(c.get(Calendar.HOUR))+":"+pad(c.get(Calendar.MINUTE));

        noteTitle = findViewById(R.id.noteTitle);   //assigns layout components contents to variables
        noteDetails = findViewById(R.id.noteDetails);
        noteFavourite = findViewById(R.id.noteFavourite);
        noteImportance = findViewById(R.id.noteImportance);
        noteType = findViewById(R.id.noteType);

        //sets up adapter for retrieving data
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        noteType.setAdapter(adapter);
        noteType.setOnItemSelectedListener(this);   //listens for change in seek bar
        noteImportance.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                importanceValue = Integer.toString(progress); //assigns seek bar progress to variable
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        noteTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() != 0){
                    getSupportActionBar().setTitle(s);
                    //change title
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //get year month and date

    }

    private String pad(int i){
        if(i<10)
            return "0"+i;
        return String.valueOf(i);
        //pads data in if value is too low
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_menu,menu);//shows save and delete buttons
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.delete){
        }
        if(item.getItemId() == R.id.save){  //saves Note using a constructor
            Note note = new Note(noteTitle.getText().toString(),importanceValue,txtType,noteDetails.getText().toString(),Boolean.toString(noteFavourite.isChecked()),todaysDate,currentTime);
            noteDatabase db = new noteDatabase(this);
            db.addNote(note);
            onBackPressed();
        }return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        txtType = parent.getItemAtPosition(position).toString();    //assigns type value
        Toast.makeText(parent.getContext(), txtType, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
