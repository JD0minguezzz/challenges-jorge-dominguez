package com.example.jdominguez.endavalibrary;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class BookListActivity extends AppCompatActivity implements OnItemClickListener{

    private BookViewModel mBookViewModel;
    private List<Book> books;
    public static final int NEW_BOOK_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_book);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final BookListAdapter adapter = new BookListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setClickListener(this);

        Toolbar mToolbar = findViewById(R.id.backButtonToolbar);
        mToolbar.setTitle(getString(R.string.app_name));
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_left_solid);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); // Finishes current activity
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BookListActivity.this, NewBookActivity.class);
                startActivityForResult(intent, NEW_BOOK_ACTIVITY_REQUEST_CODE);
            }
        });

        mBookViewModel = ViewModelProviders.of(this).get(BookViewModel.class);

        mBookViewModel.getAllBooks().observe(this, new Observer<List<Book>>() {
            @Override
            public void onChanged(@Nullable final List<Book> getbooks) {
                // Update the cached copy of the books in the adapter.
                adapter.setBooks(getbooks);
                books = getbooks;
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_BOOK_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            Book book = new Book();
            book.setId((int) Math.floor(Math.random() * 1000));
            book.setName(bundle.getString("NAME"));
            book.setAuthor(bundle.getString("AUTHOR"));
            book.setIsbn(bundle.getInt("ISBN"));
            book.setLanguage(bundle.getString("LANGUAGE"));
            book.setPublisher(bundle.getString("PUBLISHER"));
            mBookViewModel.insert(book);
            Toast.makeText(
                    getApplicationContext(), bundle.getString("NAME") + " added successfully",
                    Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(
                    getApplicationContext(), R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }

    public void onClick(View view, int position) {
        Intent intent = new Intent(this, PopActivity.class);
        Bundle bundle = new Bundle();
        Book book = books.get(position);
        bundle.putString("NAME", book.getName());
        bundle.putString("AUTHOR", book.getAuthor());
        bundle.putInt("ISBN", book.getIsbn());
        bundle.putString("LANGUAGE", book.getLanguage());
        bundle.putString("PUBLISHER", book.getPublisher());
        bundle.putInt("ID", book.getId());
        intent.putExtras(bundle);
        startActivity(intent);
    }
}