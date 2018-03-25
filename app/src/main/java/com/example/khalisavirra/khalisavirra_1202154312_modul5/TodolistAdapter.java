package com.example.khalisavirra.khalisavirra_1202154312_modul5;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by virra PC on 3/24/2018.
 */

public class TodolistAdapter extends RecyclerView.Adapter<TodolistAdapter.TodoViewHolder> {

    //Deklarasi variable
    private List<Todolist> todoList;
    private Context context;
    int id;

    //Konstruktor dari class TodolistAdapter
    public TodolistAdapter(Context con, List<Todolist> listTodo, int id){
        this.todoList = listTodo;
        this.context = con;
        this.id = id;
    }

    @Override
    public TodolistAdapter.TodoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_todo_list, parent, false);
        return new TodoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TodolistAdapter.TodoViewHolder holder, int position) {
        // Mendapatkan elemen dari dataset
        // Mengganti isi tampilan dengan elemen itu
        Todolist todo = todoList.get(position);
        holder.name.setText(todo.getName());
        holder.desc.setText(todo.getDesc());
        holder.priority.setText(todo.getPriority());
        holder.cd.setCardBackgroundColor(context.getResources().getColor(this.id));

        //Memberikan tindakan ketika salah satu item view diklik
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Apabila di klik akan intent ke aktivitas selanjutnya yaitu MainActivity
                Intent i = new Intent(view.getContext().getApplicationContext(), MainActivity.class);
                //Get context terlebih dahulu lalu start aktivitas berdasarkan intent i yang sudah dideklarasikan
                view.getContext().startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }

    public static class TodoViewHolder extends RecyclerView.ViewHolder {
        public TextView name, desc,priority;
        public CardView cd;

        public TodoViewHolder(View itemView) {
            super(itemView);
            //Mencari variable yang sudah dideklarasikan berdasarkan id
            name  = itemView.findViewById(R.id.name);
            desc   = itemView.findViewById(R.id.desc);
            priority   = itemView.findViewById(R.id.priority);
            cd = itemView.findViewById(R.id.card_view);
        }
    }
}
