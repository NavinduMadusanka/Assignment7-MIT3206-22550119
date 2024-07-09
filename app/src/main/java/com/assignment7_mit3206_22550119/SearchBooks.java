package com.assignment7_mit3206_22550119;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class SearchBooks extends AppCompatActivity {

    private ListView listView;
    private EditText searchEditText;
    private Button backButton;
    private Button searchButton;
    private Button refreshButton;
    private DBManager dbManager;
    private List<String> bookList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_books);

        listView = findViewById(R.id.listView);
        searchEditText = findViewById(R.id.searchEditText);
        backButton = findViewById(R.id.backButton);
        searchButton = findViewById(R.id.searchButton);
        refreshButton = findViewById(R.id.refreshButton);

        dbManager = new DBManager(this);
        dbManager.open();

        bookList = new ArrayList<>();
        Cursor cursor = dbManager.Select("SELECT * FROM Book");
        if (cursor.moveToFirst()) {
            do {
                bookList.add("Book ID : " + cursor.getString(0) +'\n'+ "Book Name : " + cursor.getString(1) +'\n' + "Author : " + cursor.getString(2) +'\n' + "Publisher : " + cursor.getString(3) +'\n' + "Branch : " + cursor.getString(4));
            } while (cursor.moveToNext());
        }
        cursor.close();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, bookList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String bookInfo = bookList.get(position);
                Toast.makeText(SearchBooks.this, "You had Selected this Book: " + bookInfo, Toast.LENGTH_SHORT).show();
            }
        });

        searchEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, android.view.KeyEvent event) {
                if ((event.getAction() == android.view.KeyEvent.ACTION_DOWN) &&
                        (keyCode == android.view.KeyEvent.KEYCODE_ENTER)) {
                    searchBooks();
                    return true;
                }
                return false;
            }
        });

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchEditText.clearComposingText();
                searchBooks();
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchEditText.getText();
                searchBooks();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MemberMenu();
            }
        });
    }

    private void searchBooks() {
        String searchQuery = searchEditText.getText().toString();
        bookList.clear();
        Cursor cursor = dbManager.Select("SELECT * FROM Book WHERE BookID LIKE '%" + searchQuery + "%' OR BookName LIKE '%" + searchQuery + "%' OR BookAuthor LIKE '%" + searchQuery + "%' OR BookPublisher LIKE '%" + searchQuery + "%'");
        if(cursor.getCount() == 0){
            Toast.makeText(SearchBooks.this, "Book Not Found", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                bookList.add("Book ID : " + cursor.getString(0) +'\n'+ "Book Name : " + cursor.getString(1) +'\n'+ "Author : " + cursor.getString(2) +'\n'+ "Publisher : " + cursor.getString(3) +'\n'+ "Branch : " + cursor.getString(4));
            }
        }
        cursor.close();
        ArrayAdapter<String> adapter = (ArrayAdapter<String>) listView.getAdapter();
        adapter.notifyDataSetChanged();
    }

    public void MemberMenu() {
        Intent intent = new Intent(this, MemberMenu.class);
        startActivity(intent);
    }

    public void DatabaseHelper() {
        Intent intent = new Intent(this, DatabaseHelper.class);
        startActivity(intent);
    }

    public void DBManager() {
        Intent intent = new Intent(this, DBManager.class);
        startActivity(intent);
    }

    public void Lending(){
        Intent intent = new Intent(this, Lending.class);
        startActivity(intent);
    }

    public void MainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}