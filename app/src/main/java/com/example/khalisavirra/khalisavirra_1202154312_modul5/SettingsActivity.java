package com.example.khalisavirra.khalisavirra_1202154312_modul5;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {

    //Deklarasi variabel
    TextView shapecolor;
    int colorid;
    AlertDialog.Builder alert;
    SharedPreferences.Editor sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setTitle("Settings");

        //Membuat alert dialog bernama alert
        alert = new AlertDialog.Builder(this);

        //Melakukan inisiasi shared preference
        SharedPreferences sharedP = getApplicationContext().getSharedPreferences("Preferences", 0);
        sp = sharedP.edit();
        colorid = sharedP.getInt("Colourground", R.color.white);

        //Mengakses text view pada layout
        shapecolor = findViewById(R.id.shapecolor);

        //Set shape color dengan color id yang dipilih
        shapecolor.setText(getShapeColor(colorid));
    }

    //Apabila tombol back diklik
    @Override
    public void onBackPressed() {
        //Apabila tombol back di klik akan intent ke aktivitas selanjutnya yaitu MainActivity
        Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
        //Start aktivitas berdasarkan intentyang sudah dideklarasikan
        startActivity(intent);
        //Menutup aktivitas setelah intent di jalankan
        finish();
    }

    //Method yang dijalankan ketika pilihan pada menu dipilih
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            //Menjalankan method onbackpressed
            this.onBackPressed();
        }
        return true;
    }

    //Mendapatkan string warna yang akan digunakan untuk mengubah shape color
    public String getShapeColor(int i){
        if (i==R.color.red){
            return "Red";
        }else if (i==R.color.green){
            return "Green";
        }else if (i==R.color.blue){
            return "Blue";
        }else if (i==R.color.yellow){
            return "Yellow";
        }else if (i==R.color.blue_grey){
            return "Blue Grey";
        }else{
            return "Default";
        }
    }

    //Mendapatkan id dari warna yang akan digunakan
    public int getColorid(int i){
        if (i==R.color.red){
            return R.id.red;
        }else if (i==R.color.green){
            return R.id.green;
        }else if (i==R.color.blue){
            return R.id.blue;
        }else if (i==R.color.yellow){
            return R.id.yellow;
        }else if (i==R.color.blue_grey){
            return R.id.blue_grey;
        }else{
            return R.id.white;
        }
    }

    //Method choose color yang digunakan untuk memilih warna
    public void choosecolor(View view) {
        //Set title dari alert dialog
        alert.setTitle("Shape Color");
        //Memmbuat view baru
        View view1 = getLayoutInflater().inflate(R.layout.settings_color, null);
        //Set atau menampilkan view yang telah dibuat
        alert.setView(view1);

        //Mengakses radio group pada layout
        final RadioGroup radG = view1.findViewById(R.id.radiocolor);
        radG.check(getColorid(colorid));

        //Apabila button OK pada alert dialog di klik
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Mendapatkan id radio button yang di pilih
                int a = radG.getCheckedRadioButtonId();
                switch (a){
                    case R.id.white:
                        colorid = R.color.white;
                        break;
                    case R.id.red:
                        colorid = R.color.red;
                        break;
                    case R.id.green:
                        colorid = R.color.green;
                        break;
                    case R.id.blue:
                        colorid = R.color.blue;
                        break;
                    case R.id.yellow:
                        colorid = R.color.yellow;
                        break;
                    case R.id.blue_grey:
                        colorid = R.color.blue_grey;
                        break;
                }
                //Set shape color menjadi color id yang dipilih
                shapecolor.setText(getShapeColor(colorid));
                //Menempatkan shared preferences
                sp.putInt("Colourground", colorid);
                //Melakukan commit shared preference
                sp.commit();
            }
        });

        //Apabila button Cancel pada alert dialog di klik
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        //Membuat dan menampilkan alert dialog
        alert.create().show();
    }
}
