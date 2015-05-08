package com.example.nguyen.ormlitestudent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public class ShowAllStudents extends Activity {

    public static final String STUDENT_ID = "com.example.nguyen.ormlitestudent.STUDENT_ID";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_all_students);
        ListView lstview_student = (ListView) findViewById(R.id.lstview_student);
        final Context context = getApplicationContext();

        StudentDatabaseHelper studentDbHelper = new StudentDatabaseHelper(this);

        Dao<Student, Integer> studentDao = null;
        List<Student> all_student = null;

        try {
            studentDao = studentDbHelper.getDao();
            all_student = studentDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // initialize adapter
        final StudentListItemAdapter adapter = new StudentListItemAdapter(this,all_student);


        lstview_student.setAdapter(adapter);
        lstview_student.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent_update = new Intent(context,UpdateStudent.class);
                Student student = adapter.getStudent(position);
                int student_id = student.getID();
                intent_update.putExtra(STUDENT_ID,student_id);
                startActivity(intent_update);
            }
        });

        Button btn_create = (Button)findViewById(R.id.btn_create);
        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent create = new Intent(context, MainActivity.class);
                startActivity(create);
            }
        });
    }
}
