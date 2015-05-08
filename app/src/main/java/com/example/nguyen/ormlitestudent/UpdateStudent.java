package com.example.nguyen.ormlitestudent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

/**
 * Created by nguyen on 08/05/2015.
 */
public class UpdateStudent extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_student);
        Intent intent = getIntent();
        final int student_id = intent.getIntExtra(ShowAllStudents.STUDENT_ID,0);

        final StudentDatabaseHelper db = new StudentDatabaseHelper(this);
        final Context context = getApplicationContext();
        Dao<Student,Integer> studentDao = null;

        try {
            studentDao = db.getDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Student update_student = null;
        try {
            update_student = studentDao.queryForId(student_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        TextView txtview_studentId = (TextView)findViewById(R.id.txtview_studentid);
        txtview_studentId.setText(String.valueOf(update_student.getID()));

        EditText edittext_fname = (EditText)findViewById(R.id.editText_update_fName);
        EditText edittext_lname = (EditText)findViewById(R.id.editText_update_lName);
        EditText edittext_address = (EditText)findViewById(R.id.editText_update_address);

        edittext_fname.setText(update_student.getFirstName());
        edittext_lname.setText(update_student.getLastName());
        edittext_address.setText(update_student.getAddress());

        Button btn_delete = (Button) findViewById(R.id.btn_delete);
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Dao<Student,Integer> delete_student = db.getDao();
                    delete_student.deleteById(student_id);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                Intent all_student_intent = new Intent(context, ShowAllStudents.class);
                startActivity(all_student_intent);

            }
        });
    }
}
