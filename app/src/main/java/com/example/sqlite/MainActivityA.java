package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivityA extends AppCompatActivity {
    TextView datalist;
    EditText studentid;
    EditText studentname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        datalist = (TextView) findViewById(R.id.txtData);
        studentid = (EditText) findViewById(R.id.studentid);
        studentname = (EditText) findViewById(R.id.studentname);
    }
    public void loadStudents(View view) {
        //khởi tạo đối tượng xử lý dữ liệu
        DataHandler dbHandler = new DataHandler(this, null, null, 1);
        //hiển thị dữ liệu
        datalist.setText(dbHandler.loadDataHandler());
        //xóa sạch các PlainText
        studentid.setText("");
        studentname.setText("");
    }
    public void deleteStudent(View view) {
        DataHandler dbHandler = new DataHandler(this, null, null, 1);
        boolean result = dbHandler.deleteDataHandler(Integer.parseInt(
                studentid.getText().toString()));
        if (result) {
            studentid.setText("");
            studentname.setText("");
            datalist.setText("Student Deleted");
        } else
            studentid.setText("No Match Found");
    }
    public void updateStudent(View view) {
        DataHandler dbHandler = new DataHandler(this, null,null, 1);
        boolean result = dbHandler.updateDataHandler(Integer.parseInt(
                studentid.getText().toString()), studentname.getText().toString());
        if (result) {
            studentid.setText("");
            studentname.setText("");
            datalist.setText("Student Updated");
        } else
            studentid.setText("No Match Found");
    }
    public void findFirstStudent(View view) {
        DataHandler dbHandler = new DataHandler(this, null, null, 1);
        Student student =
                dbHandler.findFisrtDataHandler
                        (studentname.getText().toString());
        if (student != null) {
            datalist.setText(String.valueOf(student.getStudentID())
                    + " " + student.getStudentName()
                    + System.getProperty("line.separator"));
            studentid.setText("");
            studentname.setText("");
        } else {
            datalist.setText("No Match Found");
            studentid.setText("");
            studentname.setText("");
        }
    }
    public void findAllStudent(View view) {
        DataHandler dbHandler = new DataHandler(this, null, null, 1);
        List<Student> lst =
                dbHandler.findAllDataHandler
                        (studentname.getText().toString());
        String studentsList = "";
        if (!lst.isEmpty()) {
            for(Student st:lst)
            {
                studentsList += String.valueOf(st.getStudentID())
                        + " " + st.getStudentName()
                        + System.getProperty("line.separator");
                studentid.setText("");
                studentname.setText("");
            }
            datalist.setText(studentsList);
        } else {
            datalist.setText("No Match Found");
            studentid.setText("");
            studentname.setText("");
        }
    }
}
