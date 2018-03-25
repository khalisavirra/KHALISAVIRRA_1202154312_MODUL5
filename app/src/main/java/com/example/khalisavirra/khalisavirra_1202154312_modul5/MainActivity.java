package com.example.khalisavirra.khalisavirra_1202154312_modul5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Deklarasi variable
    private RecyclerView recyclerView;
    private SQLiteDatabase sqLite;
    private Database dataHelper;
    private List<Todolist> todoList;
    TodolistAdapter userAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;

    public static final String TAG_ID = "id";
    public static final String TAG_NAME = "name";
    public static final String TAG_DESC = "desc";
    public static final String TAG_PRIORITY = "priority";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Mencari variable yang sudah dideklarasikan berdasarkan id
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        //Membuat database baru
        dataHelper  =  new Database(getApplicationContext());
        //Membaca data dalam database
        sqLite     = dataHelper.getReadableDatabase();
        //Membuat araylist baru
        todoList  = new ArrayList<>();

        //Melakukan inisialisasi shared preference
        SharedPreferences sharedP = this.getApplicationContext().getSharedPreferences("Preferences", 0);
        int color = sharedP.getInt("Colourground", R.color.white);

        //Membuat adapter baru
        userAdapter                 = new TodolistAdapter(this, todoList, color);
        //Mendefinisikan Linear Layout yang digunkan pada Recycler View
        mLayoutManager              = new LinearLayoutManager(this);
        //Set Linear Layout
        recyclerView.setLayoutManager(mLayoutManager);
        //Set ukuran tetap untuk recycler view sehingga ketika dilakukan perubahan tidak berubah sizenya
        recyclerView.setHasFixedSize(true);
        //Melakukan inisiasi adapter untuk recycler view
        recyclerView.setAdapter(userAdapter);

        //Menjalankan method data user
        dataUser();
        //Menjalankan method hapus data pada recycler view
        setSwipeForRecyclerView();

        //Apabila plus atau FloatingActionButton di klik
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Apabila fab di klik akan intent ke aktivitas selanjutnya yaitu AddTodolistActivity
                Intent a = new Intent(MainActivity.this,AddTodolistActivity.class);
                //Start aktivitas berdasarkan intent a yang sudah dideklarasikan
                startActivity(a);
            }
        });
    }

    //Method hapus data pada recycler view dengan swipe
    private void setSwipeForRecyclerView() {
        ItemTouchHelper touchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            //Method onMove
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            //Method onSwiped
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                final int position = viewHolder.getAdapterPosition();
                TodolistAdapter adapter = (TodolistAdapter) recyclerView.getAdapter();

                dataHelper.delete(Integer.parseInt(todoList.get(position).getId()));

                todoList.clear();
                dataUser();
                userAdapter.notifyDataSetChanged();
            }
        });
        touchHelper.attachToRecyclerView(recyclerView);
    }

    //Method data user
    private void dataUser() {
        ArrayList<HashMap<String, String>> row = dataHelper.getAllData();
        for (int i = 0; i < row.size(); i++) {
            String id = row.get(i).get(TAG_ID);
            String name = row.get(i).get(TAG_NAME);
            String desc = row.get(i).get(TAG_DESC);
            String priority = row.get(i).get(TAG_PRIORITY);

            Todolist data = new Todolist();

            //Set data berdasarkan yang sudah di get
            data.setId(id);
            data.setName(name);
            data.setDesc(desc);
            data.setPriority(priority);

            todoList.add(data);
        }
        userAdapter.notifyDataSetChanged();
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
            //Apabila di klik settings akan intent ke aktivitas selanjutnya yaitu SettingsActivity
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            //Start aktivitas berdasarkan intent yang sudah dideklarasikan
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

