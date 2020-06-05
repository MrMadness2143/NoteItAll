package com.example.noteitall;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    Adapter adapter;
    List<Note> notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);   //sets up toolbar variable
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));  //makes toolbar text white
        setSupportActionBar(toolbar);   //implements toolbar
        noteDatabase db = new noteDatabase(this);   //creates new database connection
        notes = db.getNotes();  //gets list of values from database using method from noteDatabase.java

        recyclerView = findViewById(R.id.listOfNotes);      //sets values then lays out the recycler variables.
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(this,notes);  //adapts data from notes to fit in recycler view
        recyclerView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_menu,menu);//shows addmenu in the toolbar
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.add){
            Intent i = new Intent(this,AddNote.class);  //sets AddNote.java as location
            startActivity(i);
            //tells you the button reacted
            Toast.makeText(this, "Add btn is Clicked", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
