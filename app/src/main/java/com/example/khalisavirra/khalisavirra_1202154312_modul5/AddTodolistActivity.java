package com.example.khalisavirra.khalisavirra_1202154312_modul5;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by virra PC on 3/24/2018.
 */

public class AddTodolistActivity extends AppCompatActivity {

    //Deklarasi Variabel
    private EditText mName, mDesc,mPriority;
    private Button btn_tambah;
    //Database
    Database sqlite = new Database(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_do_list);

        //Mencari variable yang sudah dideklarasikan berdasarkan id
        mName = (EditText) findViewById(R.id.edit_name);
        mDesc = (EditText) findViewById(R.id.edit_desc);
        mPriority = (EditText) findViewById(R.id.edit_priority);
        btn_tambah = (Button) findViewById(R.id.button_tambah);

        //Apabila button tambah todolist di klik
        btn_tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });
    }

    //Method save yang digunakan untuk button tambah todolist
    private void save() {
        if (String.valueOf(mName.getText()).equals(null) || String.valueOf(mDesc.getText()).equals("") ||
                String.valueOf(mPriority.getText()).equals(null) || String.valueOf(mPriority.getText()).equals("")) {
            //Pesan yang muncul bila 3 edit text belum diisi
            Toast.makeText(getApplicationContext(),
                    "Please input name or address ...", Toast.LENGTH_SHORT).show();
        } else {
            sqlite.insert(mName.getText().toString().trim(), mDesc.getText().toString().trim(),mPriority.getText().toString().trim());
            blank();
            //Apabila sudah diisi akan intent ke aktivitas selanjutnya yaitu MainActivity
            Intent a = new Intent(AddTodolistActivity.this,MainActivity.class);
            //Start aktivitas berdasarkan intent a yang sudah dideklarasikan
            startActivity(a);
        }
    }

    private void blank() {
        mName.requestFocus();
        //Set 3 edit text menjadi kosong(null)
        mName.setText(null);
        mDesc.setText(null);
        mPriority.setText(null);
    }
}
