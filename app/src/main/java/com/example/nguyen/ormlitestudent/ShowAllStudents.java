package com.example.nguyen.ormlitestudent;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.sql.SQLException;

/**
 * Created by nguyen on 04/05/2015.
 */
public class ShowAllStudents extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_all_students);
        ListView lstview_student = (ListView) findViewById(R.id.lstview_student);

        StudentListItemAdapter adapter = null;
        try {
            adapter = new StudentListItemAdapter(this);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        lstview_student.setAdapter(adapter);
    }
}
