package com.timeontask;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

import com.timeontask.db.Task;
import com.timeontask.db.TaskTableConnection;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Bo on 3/25/2017.
 */

public class GenerateNewEntry extends AppCompatActivity {
    TaskTableConnection dbConnection;
    String selected_category;
    String selected_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_entry);
        dbConnection= new TaskTableConnection(getApplicationContext());
        Spinner categories_spinner = (Spinner) findViewById(R.id.categories_spinner);
        final Spinner names_spinner = (Spinner) findViewById(R.id.names_spinner);
        final EditText categories_edit_text = (EditText) findViewById(R.id.categories_edit_text);
        final EditText names_edit_text = (EditText) findViewById(R.id.names_edt_text);

        Button entry_button = (Button)findViewById(R.id.submit_button);
        entry_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GenerateNewEntry.this, GenerateNewEntry.class);
                startActivity(intent);
            }
        });

        List<Task> taskList = dbConnection.getTasks(new Date(6), new Date());
        Map<String, Integer> categories = new HashMap();
        Map<String, Integer> names = new HashMap();

        for (Task t : taskList){
            categories.put(t.category,1);
            names.put(t.name,1);
        }
        categories.put("New entry", 1);
        names.put("New entry", 1);
        Set<String> categories_set = categories.keySet();
        Set<String> names_set = names.keySet();

        String[] categories_array = categories_set.toArray(new String[categories.size()]);
        String[] names_array = names_set.toArray(new String[names_set.size()]);



        final ArrayAdapter<String> categories_adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, categories_array);
        ArrayAdapter<String> names_adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, names_array);

        categories_spinner.setAdapter(categories_adapter);
        names_spinner.setAdapter(names_adapter);

        categories_spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                selected_category =  (String) parent.getItemAtPosition(position);

                if(selected_name.equals("new entry")) {
                    categories_edit_text.setVisibility(View.VISIBLE);
                    names_spinner.setVisibility(View.GONE);
                    names_edit_text.setVisibility(View.GONE);
                    new_entry_alert();
                } else {
                    categories_edit_text.setVisibility(View.GONE);
                    names_spinner.setVisibility(View.VISIBLE);
                    // TODO: update names_array
                    categories_adapter.notifyDataSetChanged();
                    // TODO: set visiblity of names_edit_text
                }

                Log.v("item", (String) parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        names_spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                selected_name =  (String) parent.getItemAtPosition(position);
                if(selected_name.equals("new entry")) {
                    names_edit_text.setVisibility(View.VISIBLE));
                    new_entry_alert();
                }
                Log.v("item", (String) parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
    }
    void new_entry_alert(){
        // 1. Instantiate an AlertDialog.Builder with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());

        String dialog_message = "Create New Entry";
        String dialog_title = "Create New Entry";
        // 2. Chain together various setter methods to set the dialog characteristics
        builder.setMessage(dialog_message)
                .setTitle(dialog_title);

        // 3. Get the AlertDialog from create()
        builder.setPositiveButton("Create", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });
        AlertDialog dialog = builder.create();
    }


}
