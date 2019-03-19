package com.example.jdominguez.endavalibrary;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class BookRepository {

    private BookDao bookDao;
    private LiveData<List<Book>> allBooks;

    BookRepository(Application application) {
        BookDatabase db = BookDatabase.getDatabase(application);
        bookDao = db.bookDao();
        allBooks = bookDao.retrieveBook();
    }

    LiveData<List<Book>> getAllBooks() {
        return allBooks;
    }

    public void insert (Book book) {
        new insertAsyncTask(bookDao).execute(book);
    }

    public void deleteBook (String bookId) {
        new deleteAsyncTask(bookDao).execute(bookId);
    }

    public void updateBook (String name, String author, String isbn, String language, String publisher, String id) {
        new updateAsyncTask(bookDao).execute(name, author, isbn, language, publisher, id);
    }

    private static class insertAsyncTask extends AsyncTask<Book, Void, Void> {

        private BookDao asyncTaskDao;

        insertAsyncTask(BookDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Book... params) {
            asyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<String, Void, Void> {

        private BookDao asyncTaskDao;

        deleteAsyncTask(BookDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final String... params) {
            asyncTaskDao.deleteBook(Integer.parseInt(params[0]));
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<String, Void, Void> {

        private BookDao asyncTaskDao;

        updateAsyncTask(BookDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final String... params) {
            asyncTaskDao.updateBook(params[0], params[1], Integer.parseInt(params[2]), params[3], params[4], Integer.parseInt(params[5]));
            return null;
        }
    }
}
