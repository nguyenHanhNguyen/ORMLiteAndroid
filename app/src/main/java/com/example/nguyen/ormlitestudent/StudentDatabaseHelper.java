package com.example.nguyen.ormlitestudent;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class StudentDatabaseHelper extends OrmLiteSqliteOpenHelper{

    private static final String DB_NAME = "Student";
    private static final int DB_VERSION = 1;
    private Dao studentDao = null;

    public StudentDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION, R.raw.ormlite_config);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource,Student.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, Student.class, false);
            onCreate(database,connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Dao <Student,Integer> getDao() throws SQLException {
        if(studentDao == null){
            studentDao = getDao(Student.class);
        }
        return studentDao;
    }
}
