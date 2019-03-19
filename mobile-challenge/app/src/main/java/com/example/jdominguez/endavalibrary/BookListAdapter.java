package com.example.jdominguez.endavalibrary;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.BookViewHolder> {

    private OnItemClickListener clickListener;

    class BookViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public final TextView bookNameView;
        public final TextView bookAuthorView;
        public final TextView bookIsbnView;
        public final TextView bookLanguageView;
        public final TextView bookPublisherView;

        private BookViewHolder(View itemView) {
            super(itemView);
            bookNameView = itemView.findViewById(R.id.textNameView);
            bookAuthorView = itemView.findViewById(R.id.textAuthorView);
            bookIsbnView = itemView.findViewById(R.id.textIsbnView);
            bookLanguageView = itemView.findViewById(R.id.textLanguageView);
            bookPublisherView = itemView.findViewById(R.id.textPublisherView);
            itemView.setTag(itemView);
            itemView.setOnClickListener(this);
        }

        public void onClick(View view) {
            if (clickListener != null) {
                clickListener.onClick(view, getAdapterPosition());
            }
        }
    }

    private final LayoutInflater mInflater;
    private List<Book> books;

    BookListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    public void setClickListener(OnItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new BookViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {
        if (books != null) {
            Book current = books.get(position);
            holder.bookNameView.setText(current.getName());
            holder.bookAuthorView.setText(current.getAuthor());
            holder.bookIsbnView.setText(String.valueOf(current.getIsbn()));
            holder.bookLanguageView.setText(current.getLanguage());
            holder.bookPublisherView.setText(current.getPublisher());
        } else {
            // Covers the case of data not being ready yet.
            holder.bookNameView.setText("Cool Book");
            holder.bookAuthorView.setText("Jane Doe");
            holder.bookIsbnView.setText("0000");
            holder.bookLanguageView.setText("Klingon");
            holder.bookPublisherView.setText("Detective Comics");
        }
    }

    void setBooks(List<Book> books){
        this.books = books;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (books != null)
            return books.size();
        else return 0;
    }
}
