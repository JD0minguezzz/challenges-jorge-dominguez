package com.example.jdominguez.endavalibrary;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class BookViewModel extends AndroidViewModel {

    private BookRepository repository;

    private LiveData<List<Book>> allBooks;

    public BookViewModel (Application application) {
        super(application);
        repository = new BookRepository(application);
        allBooks = repository.getAllBooks();
    }

    LiveData<List<Book>> getAllBooks() { return allBooks; }

    public void insert(Book book) { repository.insert(book); }

    public void deleteBook(String bookId) { repository.deleteBook(bookId); }

    public void updateBook(String name, String author, String isbn, String language, String publisher, String id) {
        repository.updateBook(name, author, isbn, language, publisher, id);
    }
}
