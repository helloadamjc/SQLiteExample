package com.example.adamc.sqliteexample;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDB;
    EditText editName, editSurname, editMarks, editId;
    Button btnAddData;
    Button btnViewAll;
    Button btnviewUpdate;
    Button btnDelete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDB = new DatabaseHelper(this);
        editName = (EditText)findViewById(R.id.edit_Name);
        editSurname = (EditText)findViewById(R.id.edit_Surname);
        editMarks = (EditText)findViewById(R.id.edit_Marks);
        editId = (EditText)findViewById(R.id.editText_id);
        btnAddData = (Button)findViewById(R.id.button_add);
        btnViewAll = (Button)findViewById(R.id.button_view_all);
        btnviewUpdate = (Button)findViewById(R.id.button_update);
        btnDelete = (Button)findViewById(R.id.button_delete);
        addData();
        viewAll();
        updateData();
        deletedata();
    }

    public void deletedata(){
        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = myDB.deleteData(editId.getText().toString());
                        if(deletedRows > 0){
                            Toast.makeText(MainActivity.this, "Data Deleted", Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Data Not Deleted", Toast.LENGTH_LONG).show();
                        }
                    }
                }

        );
    }

    public void addData(){
        btnAddData.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        boolean isInserted =  myDB.insertData(editName.getText().toString(), editSurname.getText().toString(), editMarks.getText().toString());
                        if(isInserted = true){
                            Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Data Not Inserted", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }

    public void viewAll(){
        btnViewAll.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Cursor res = myDB.getAllData();
                        if(res.getCount() == 0){
                            showMessage("Error", "No data found");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while(res.moveToNext()){
                            buffer.append("Id :"+ res.getString(0) +"\n");
                            buffer.append("Name :"+ res.getString(1) +"\n");
                            buffer.append("Surname :"+ res.getString(2) +"\n");
                            buffer.append("Marks :"+ res.getString(3) +"\n\n");
                        }
                        showMessage("Data", buffer.toString());

                    }
                }
        );
    }



    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();

    }

    public void updateData(){
        btnviewUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate = myDB.updateData(editId.getText().toString(), editName.getText().toString(), editSurname.getText().toString(), editMarks.getText().toString());

                        if(isUpdate == true){
                            Toast.makeText(MainActivity.this, "Data Updated", Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Data Not Updated", Toast.LENGTH_LONG).show();
                        }
                    }
                }


        );
    }
}
