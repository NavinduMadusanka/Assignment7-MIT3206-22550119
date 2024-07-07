package com.assignment7_mit3206_22550119;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import java.util.ArrayList;

public class SearchBooks extends AppCompatActivity {

    private DBManager dbmanager;
    DatabaseHelper db;

    ListView userlist;

    ArrayList<String> listItem;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_books);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Search Books");

        db = new DatabaseHelper(this);
        listItem = new ArrayList<>();

        dbmanager = new DBManager(this);

        userlist = findViewById(R.id.users_list);
        viewData();
        userlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String text = userlist.getItemAtPosition(position).toString();
                Intent intent = new Intent(SearchBooks.this, SearchBooks.class);
                startActivity(intent);
                Toast.makeText(SearchBooks.this, ""+text, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void viewData() {
        Cursor cursor = db.viewData();

        if(cursor.getCount() == 0){
            Toast.makeText(this, "Not Data to Show", Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                listItem.add(
                        "Book ID: "+ cursor.getString(0)+'\n' +
                        "Book Name: "+ cursor.getString(1)+'\n' +
                        "Author: "+cursor.getString(3)+'\n'+
                        "Publisher: "+cursor.getString(2)+'\n'+
                        "Branch: "+cursor.getString(4));
            }
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listItem);
            userlist.setAdapter(adapter);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate (R.menu.menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView =(SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<String> userslist = new ArrayList<>();
                for(String user: listItem){
                    if(user.toLowerCase().contains(newText.toLowerCase())){
                        userslist.add(user);
                    }
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(SearchBooks.this,
                        android.R.layout.simple_expandable_list_item_1,userslist);
                userlist.setAdapter(adapter);
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    public void MemberMenu(){
        Intent intent = new Intent(this, MemberMenu.class);
        startActivity(intent);
    }

    public void MainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
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

    public void ManageBooks(){
        Intent intent = new Intent(this, ManageBooks.class);
        startActivity(intent);
    }

    public void ManagePublisher(){
        Intent intent = new Intent(this, ManagePublisher.class);
        startActivity(intent);
    }

    public void ManageBranch(){
        Intent intent = new Intent(this, ManageBranch.class);
        startActivity(intent);
    }

    public void Lending(){
        Intent intent = new Intent(this, Lending.class);
        startActivity(intent);
    }

    public void AdminMenu(){
        Intent intent = new Intent(this, AdminMenu.class);
        startActivity(intent);
    }
}
