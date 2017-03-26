package com.timeontask;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.graphics.Color;
import android.widget.Button;

import android.view.View;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.timeontask.db.Task;
import com.timeontask.db.TaskTableConnection;

import java.util.Date;
import java.util.List;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/**
 * Created by Bo on 3/25/2017.
 */

public class OpenPage extends AppCompatActivity {

    TaskTableConnection dbConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);
        dbConnection= new TaskTableConnection(getApplicationContext());
        Task[] tasks = {
                new Task("DoTA", "Gaming", new Date(10).getTime(), new Date(100).getTime()),
                new Task("LoL", "Gaming", new Date(10).getTime(), new Date(100).getTime()),
                new Task("Maple Story", "Gaming", new Date(10).getTime(), new Date(1000).getTime()),
                new Task("EECS 482", "Class", new Date(100).getTime(), new Date(1000).getTime()),
                new Task("EECS 485", "Class", new Date(20).getTime(), new Date(1000).getTime()),
                new Task("STATS 413", "Class", new Date(30).getTime(), new Date(1000).getTime()),
                new Task("MATH 423", "Class", new Date(40).getTime(), new Date(10000).getTime()),
                new Task("TCHNCLCM 300", "Class", new Date(60).getTime(), new Date(1000).getTime()),
                new Task("Sleep", "Misc", new Date(12).getTime(), new Date(100).getTime())
        };

        for (Task t : tasks) {
            dbConnection.addTask(t);
        }

        Button entry_button = (Button)findViewById(R.id.start_button);
        entry_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OpenPage.this, GenerateNewEntry.class);
                    startActivity(intent);
            }
        });
        GeneratePieChart("TimeOnTask");
    }

    void GeneratePieChart(String centertext){
        PieChart piechart = (PieChart) findViewById(R.id.piechart);

        //default use percent values

        piechart.setUsePercentValues(true);

        piechart.setCenterText(centertext);

        ArrayList<Integer> colors_list = new ArrayList<>();
        colors_list.add(R.color.pie_chart_blue);
        colors_list.add(R.color.pie_chart_green);
        colors_list.add(R.color.pie_chart_pink);
        colors_list.add(R.color.pie_chart_red);
        colors_list.add(R.color.pie_chart_purple);

        List<Task> taskList = dbConnection.getTasks(new Date(6), new Date());

        for (Task t : taskList) {
			System.out.println(t);
		}

        Map<String, Integer> categories = new HashMap();
        for (Task t : taskList){
            String category = t.category;
            long time_spent = t.endTime;
            //System.out.print("LULULLULLUL\n");
            //System.out.print(time_spent);
            long divide_factor = 1000;
            time_spent = time_spent/divide_factor;
            Integer time_spent_int = (int) time_spent;
            //System.out.print("TIME SPENT INT \n");
            //System.out.print(time_spent_int);
            Integer value = categories.get(category);
            System.out.print("HELLO: ");
            System.out.print(value);
            if(value != null){
                categories.put(category,value + time_spent_int);
            }
            else {
                categories.put(category, time_spent_int);
            }
        }

        ArrayList<PieEntry> entries= new ArrayList<>();


        for (String key : categories.keySet()){
            System.out.println(categories.get(key));
            entries.add(new PieEntry(categories.get(key), key));
        }
//        entries.add(new PieEntry(25, "Defense of the Ancients"));
//        entries.add(new PieEntry(25, "League of legends"));
//        entries.add(new PieEntry(30, "Electrical Engineering and Computer Science"));
//        entries.add(new PieEntry(35, "OverFlow Tester because I cant think of anything"));
//        entries.add(new PieEntry(35, "This thing is small"));

        PieDataSet dataset = new PieDataSet(entries,"");


        dataset.setColors(new int[]{R.color.pie_chart_blue,
                R.color.pie_chart_green, R.color.pie_chart_pink, R.color.pie_chart_purple,
                R.color.pie_chart_red}, getApplicationContext());

        PieData data = new PieData(dataset);

        data.setValueTextColor(Color.WHITE);
        data.setValueTextSize(30f);
        piechart.setData(data);
        piechart.setEntryLabelTextSize(20f);
        piechart.setCenterTextSize(20f);
        piechart.setHoleRadius(40);
        piechart.setTransparentCircleRadius(42);
        piechart.setEnabled(false);


        //query database
        piechart.invalidate();
    }
}
