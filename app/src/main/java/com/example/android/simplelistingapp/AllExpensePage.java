package com.example.android.simplelistingapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

public class AllExpensePage extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_expense_page);

        MyDB myDB = new MyDB(this);
        SQLiteDatabase sqLiteDatabase = myDB.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(Entry.TABLE_NAME,new String[]{Entry.COLUMN_NAME,Entry.COLUMN_AMOUNT,Entry.COLUMN_TIME},Entry.COLUMN_NAME + "=?",new String[]{},null,null,null,null);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //recyclerView.setAdapter(new EntryAdapter(this,));

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AllExpensePage.this,AddExpense.class);
                startActivity(i);
            }
        });

    }
}
