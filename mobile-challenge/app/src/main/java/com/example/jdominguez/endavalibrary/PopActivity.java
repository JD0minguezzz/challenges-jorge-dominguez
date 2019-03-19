package com.example.jdominguez.endavalibrary;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class PopActivity extends AppCompatActivity {

    private BookViewModel mBookViewModel;
    public static final int REQUEST_EXIT = 1;

    @Override
    protected void onCreate(Bundle savedInstaceState) {
        super.onCreate(savedInstaceState);
        setContentView(R.layout.activity_pop);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.75), (int)(height*.65));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;

        getWindow().setAttributes(params);

        TextView name = findViewById(R.id.name);
        TextView author = findViewById(R.id.author);
        TextView isbn = findViewById(R.id.isbn);
        TextView language = findViewById(R.id.language);
        TextView publisher = findViewById(R.id.publisher);
        ImageButton closeButton = findViewById(R.id.closeButton);
        Button deleteButton = findViewById(R.id.deleteButton);
        Button editButton = findViewById(R.id.editButton);

        name.setText(getIntent().getStringExtra("NAME"));
        author.setText(getIntent().getStringExtra("AUTHOR"));
        isbn.setText(String.valueOf(getIntent().getIntExtra("ISBN", 0)));
        language.setText(getIntent().getStringExtra("LANGUAGE"));
        publisher.setText(getIntent().getStringExtra("PUBLISHER"));


        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); //Finishes current activity
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            /*@Override
            public void onClick(View view) {
                Intent intent = new Intent(PopActivity.this, ConfirmationActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("NAME", getIntent().getStringExtra("NAME"));
                intent.putExtras(bundle);
                startActivity(intent);
            }*/
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(PopActivity.this, R.style.AppTheme_AlertDialog));

                LayoutInflater inflater = getLayoutInflater();
                View warningDialogView = inflater.inflate(R.layout.activity_confirmation, null);

                builder.setView(warningDialogView);

                Button confirmDeletionButton = warningDialogView.findViewById(R.id.confirmDeletionButton);
                Button abortDeletionButton = warningDialogView.findViewById(R.id.abortDeletionButton);
                TextView name = warningDialogView.findViewById(R.id.warningText);
                name.setText("¿Are you sure you want to delete " + getIntent().getStringExtra("NAME") + "?");

                int width = getResources().getDisplayMetrics().widthPixels;
                int height = getResources().getDisplayMetrics().heightPixels;

                final AlertDialog warningDialog = builder.create();
                warningDialog.show();
                warningDialog.getWindow().setLayout((int)(width*.8), (int)(height*.3));

                mBookViewModel = ViewModelProviders.of(PopActivity.this).get(BookViewModel.class);

                confirmDeletionButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mBookViewModel.deleteBook(String.valueOf(getIntent().getIntExtra("ID", 0)));
                        Toast.makeText(
                                getApplicationContext(), "Book deleted successfully",
                                Toast.LENGTH_LONG).show();
                        finish();
                    }
                });

                abortDeletionButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        warningDialog.cancel();
                    }
                });
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(PopActivity.this, EditBookActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("NAME", getIntent().getStringExtra("NAME"));
                bundle.putString("AUTHOR", getIntent().getStringExtra("AUTHOR"));
                bundle.putString("ISBN", String.valueOf(getIntent().getIntExtra("ISBN", 0)));
                bundle.putString("LANGUAGE", getIntent().getStringExtra("LANGUAGE"));
                bundle.putString("PUBLISHER", getIntent().getStringExtra("PUBLISHER"));
                bundle.putInt("ID", getIntent().getIntExtra("ID", 0));
                intent.putExtras(bundle);
                startActivityForResult(intent, REQUEST_EXIT);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_EXIT) {
            if (resultCode == RESULT_OK) {
                this.finish();
            }
        }
    }
}
