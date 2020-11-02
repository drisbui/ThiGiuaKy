package com.example.mycafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnLuu,btnNew,btnXem;
    EditText edMa,edNoiDung;
    ArrayList<String> filesname=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        khoitao();

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ID = edMa.getText().toString();
                String NoiDung = edNoiDung.getText().toString();

                if((!ID.isEmpty() && ID.length()>0) && (!NoiDung.isEmpty() && NoiDung.length()>0))
                {
                    if(!filesname.contains(ID))
                    {
                        filesname.add(ID);
                        // Lưu file
                        try{
                            FileOutputStream fout=openFileOutput(ID, Context.MODE_PRIVATE);
                            fout.write(NoiDung.getBytes());
                            fout.close();
                            Toast.makeText(MainActivity.this, "Lưu thành công", Toast.LENGTH_SHORT).show();
                        }
                        catch (Exception e){
                            Toast.makeText(MainActivity.this, "Lỗi lưu file", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Tên file trùng", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edMa.setText("");
                edNoiDung.setText("");
            }
        });
        btnXem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,MainActivity2.class);
                intent.putExtra("file",filesname);
                startActivity(intent);
            }
        });

    }

    private void khoitao()
    {
        btnLuu= (Button) findViewById(R.id.btnLuu);
        btnNew= (Button) findViewById(R.id.btnClear);
        btnXem= (Button) findViewById(R.id.btnXemDanhSach);
        edMa= (EditText) findViewById(R.id.edMa);
        edNoiDung= (EditText) findViewById(R.id.edNoiDung);
    }
}