package com.example.roommvvm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class AddNoteActivity extends AppCompatActivity {
    public static final String EXTRA_TITLE = "com.example.roommvvm.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION = "com.example.roommvvm.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY = "com.example.roommvvm.EXTRA_PRIORITY";

    private EditText mTitle;
    private EditText mDescription;
    private NumberPicker mPriority;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        mTitle=findViewById(R.id.text_title);
        mDescription=findViewById(R.id.text_description);
        mPriority=findViewById(R.id.number_picker_priority);

        mPriority.setMinValue(1);
        mPriority.setMaxValue(10);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add Note");
    }
    private void saveNote(){
        String title= mTitle.getText().toString();
        String Description=mDescription.getText().toString();
        int priority= mPriority.getValue();

        if(title.trim().isEmpty() || Description.trim().isEmpty())
        {
            Toast.makeText(this,"Please add all details",Toast.LENGTH_SHORT).show();
        }
        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE,title);
        data.putExtra(EXTRA_DESCRIPTION,Description);
        data.putExtra(EXTRA_PRIORITY,priority);
        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.save_note: saveNote();
                    return true;
            default: return super.onOptionsItemSelected(item);
        }

    }
}
