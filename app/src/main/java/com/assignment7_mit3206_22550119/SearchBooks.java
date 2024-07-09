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
                Toast.makeText(SearchBooks.this, "You Selected: " + bookInfo, Toast.LENGTH_SHORT).show();
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
            public void onClick(View v) { searchBooks(); }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        Cursor cursor = dbManager.Select("SELECT * FROM Book WHERE BookName LIKE '%" + searchQuery + "%' OR BookAuthor LIKE '%" + searchQuery + "%'");
        if (cursor.moveToFirst()) {
            do {
                bookList.add(cursor.getString(1) + " - " + cursor.getString(2));
            } while (cursor.moveToNext());
        } else {
            Toast.makeText(SearchBooks.this, "Book not Found", Toast.LENGTH_SHORT).show();
        }
        cursor.close();
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, bookList));
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