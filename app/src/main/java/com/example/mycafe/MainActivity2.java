package com.example.mycafe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    ListView lvDSFile;
    ArrayList<String> filesname = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        lvDSFile = (ListView) findViewById(R.id.lvDanhSach);
        filesname= getIntent().getStringArrayListExtra("file");
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_single_choice,filesname);
        lvDSFile.setAdapter(arrayAdapter);
        lvDSFile.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = filesname.get(position).toString();
                String noidung = "";
                StringBuffer buffer=new StringBuffer();
                String line=null;
                int c;
                try{
                    FileInputStream fin=openFileInput(name);
                    BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(fin));
                    while ((line=bufferedReader.readLine())!=null){
                        buffer.append(line).append("\n");
                    }
                    noidung = buffer.toString();
                    fin.close();

                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity2.this);
                    alertDialog.setTitle("Tên file: " + name);
                    alertDialog.setMessage("Nội dung: " + noidung);
                    alertDialog.show();
                }
                catch (Exception e){
                    Toast.makeText(MainActivity2.this, "Không lể mở file", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}