package txiner.top.dailytask;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import txiner.top.dailytask.util.Task;
import txiner.top.dailytask.util.TaskAdapter;

public class MainActivity extends AppCompatActivity {


    private ListView listView = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ArrayList<Map<String,Object>> tasks=getTasks();


        listView = (ListView) findViewById(R.id.task_list);
        TaskAdapter taskAdapter=new TaskAdapter(this,tasks);
        listView.setAdapter(taskAdapter);


    }

    private ArrayList<Map<String, Object>> getTasks() {
        ArrayList<Map<String, Object>> tasks=new ArrayList<>();
        for (int i=0;i<3;i++){
            Map<String, Object> task=new HashMap<>();
            task.put("name","name "+i);
            task.put("content","content "+i);
            task.put("over",false);
            tasks.add(task);
        }
        return tasks;
    }


}
