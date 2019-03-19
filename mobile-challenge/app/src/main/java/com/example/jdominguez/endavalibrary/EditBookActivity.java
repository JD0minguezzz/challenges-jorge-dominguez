package com.example.jdominguez.endavalibrary;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditBookActivity extends AppCompatActivity {

    private BookViewModel mBookViewModel;
    private EditText updateBookName;
    private EditText updateBookAuthor;
    private EditText updateBookIsbn;
    private EditText updateBookLanguage;
    private EditText updateBookPublisher;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);

        Toolbar mToolbar = findViewById(R.id.backButtonToolbar);
        mToolbar.setTitle(getIntent().getStringExtra("NAME"));
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_left_solid);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); //Finishes current activity
            }
        });

        updateBookName = findViewById(R.id.updateBookName);
        updateBookAuthor = findViewById(R.id.updateBookAuthor);
        updateBookIsbn = findViewById(R.id.updateBookIsbn);
        updateBookLanguage = findViewById(R.id.updateBookLanguage);
        updateBookPublisher = findViewById(R.id.updateBookPublisher);

        updateBookName.setHint(getIntent().getStringExtra("NAME"));
        updateBookAuthor.setHint(getIntent().getStringExtra("AUTHOR"));
        updateBookIsbn.setHint(getIntent().getStringExtra("ISBN"));
        updateBookLanguage.setHint(getIntent().getStringExtra("LANGUAGE"));
        updateBookPublisher.setHint(getIntent().getStringExtra("PUBLISHER"));

        mBookViewModel = ViewModelProviders.of(EditBookActivity.this).get(BookViewModel.class);

        final Button updateButton = findViewById(R.id.update_button);
        updateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mBookViewModel.updateBook(
                        getTextOrHint(updateBookName),
                        getTextOrHint(updateBookAuthor),
                        getTextOrHint(updateBookIsbn),
                        getTextOrHint(updateBookLanguage),
                        getTextOrHint(updateBookPublisher),
                        String.valueOf(getIntent().getIntExtra("ID", 0))
                );
                Toast.makeText(
                        getApplicationContext(), getIntent().getStringExtra("NAME") + " updated successfully",
                        Toast.LENGTH_LONG).show();
                setResult(RESULT_OK, null);
                finish();
            }
        });
    }

    private static String getTextOrHint(EditText editText) {
        return(TextUtils.isEmpty(editText.getText()) ? editText.getHint() : editText.getText()).toString();
    }
}
