package com.example.nguyen.ormlitestudent;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    StudentDatabaseHelper studentDbHelper;
    Dao<Student, Integer> studentDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        studentDbHelper = new StudentDatabaseHelper(this);

        final EditText editText_fname = (EditText) findViewById(R.id.editText_fName);
        final EditText editText_lname = (EditText) findViewById(R.id.editText_lName);
        final EditText editText_address = (EditText) findViewById(R.id.editText_address);
        Button btn_create = (Button) findViewById(R.id.button_create);
        Button btn_show = (Button) findViewById(R.id.button_show);
        final Context context = getApplicationContext();

        //create student
        btn_create.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    boolean created = false;
                    String fName = editText_fname.getText().toString();
                    String lName = editText_lname.getText().toString();
                    String address = editText_address.getText().toString();
                    created = createNewStudent(fName,lName,address);
                    CharSequence text = "Students successfully created";
                    int dur = Toast.LENGTH_LONG;
                    if(created) {
                        Toast toast = Toast.makeText(context,text,dur);
                        toast.show();
                        editText_fname.setText("");
                        editText_lname.setText("");
                        editText_address.setText("");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        //show all student
        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent show_intent = new Intent(context,ShowAllStudents.class);
                startActivity(show_intent);
            }
        });

    }


    public boolean createNewStudent(String fName, String lName, String address) throws SQLException {

        studentDao = studentDbHelper.getDao();
        long no_student = studentDao.countOf();
        studentDao.create(new Student(fName,lName,address));

        //debug only
        /*List<Student> all_student = null;
        try {
            all_student = studentDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Log.d("all students", all_student.toString());*/

        if(studentDao.countOf() == no_student + 1) {
            return true;
        } else {
            return false;
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
