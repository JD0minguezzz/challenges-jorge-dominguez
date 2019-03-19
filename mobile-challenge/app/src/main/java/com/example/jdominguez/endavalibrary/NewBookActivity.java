package com.example.jdominguez.endavalibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewBookActivity extends AppCompatActivity {

    private EditText editBookName;
    private EditText editBookAuthor;
    private EditText editBookIsbn;
    private EditText editBookLanguage;
    private EditText editBookPublisher;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_book);

        Toolbar mToolbar = findViewById(R.id.backButtonToolbar);
        mToolbar.setTitle(getString(R.string.new_book));
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_left_solid);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); //Finishes current activity
            }
        });

        editBookName = findViewById(R.id.edit_book_name);
        editBookAuthor = findViewById(R.id.edit_book_author);
        editBookIsbn = findViewById(R.id.edit_book_isbn);
        editBookLanguage = findViewById(R.id.edit_book_language);
        editBookPublisher = findViewById(R.id.edit_book_publisher);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                Bundle bundle = new Bundle();
                if (TextUtils.isEmpty(editBookName.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                }
                if (TextUtils.isEmpty(editBookAuthor.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                }
                if (TextUtils.isEmpty(editBookIsbn.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                }
                if (TextUtils.isEmpty(editBookLanguage.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                }
                if (TextUtils.isEmpty(editBookPublisher.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String name = editBookName.getText().toString();
                    String author = editBookAuthor.getText().toString();
                    int isbn = Integer.parseInt(editBookIsbn.getText().toString());
                    String language = editBookLanguage.getText().toString();
                    String publisher = editBookPublisher.getText().toString();
                    bundle.putString("NAME", name);
                    bundle.putString("AUTHOR", author);
                    bundle.putInt("ISBN", isbn);
                    bundle.putString("LANGUAGE", language);
                    bundle.putString("PUBLISHER", publisher);
                    replyIntent.putExtras(bundle);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}
