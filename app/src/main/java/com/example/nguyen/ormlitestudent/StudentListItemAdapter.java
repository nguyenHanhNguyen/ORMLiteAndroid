package com.example.nguyen.ormlitestudent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by nguyen on 04/05/2015.
 */
public class StudentListItemAdapter extends BaseAdapter{

    StudentDatabaseHelper studentDbHelper;
    Dao<Student, Integer> studentDao;
    List<Student> all_student;
    LayoutInflater inflater;

    public StudentListItemAdapter(Context context) throws SQLException {
        studentDbHelper = new StudentDatabaseHelper(context);
        studentDao = studentDbHelper.getDao();
        all_student = studentDao.queryForAll();
        inflater = LayoutInflater.from(context);
    }

    private class ViewHolder{
        TextView fname;
        TextView lname;
        TextView address;
    }

    @Override
    public int getCount() {
        return all_student.size();
    }

    @Override
    public Object getItem(int position) {
        return all_student.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.list_item_student,null);
            holder = new ViewHolder();
            holder.fname = (TextView) convertView.findViewById(R.id.txtview_fname);
            holder.lname = (TextView) convertView.findViewById(R.id.txtview_lname);
            holder.address = (TextView) convertView.findViewById(R.id.txtview_address);
            convertView.setTag(holder);
        } else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.fname.setText(all_student.get(position).getFirstName());
        holder.lname.setText(all_student.get(position).getLastName());
        holder.address.setText(all_student.get(position).getAddress());
        return convertView;
    }
}
