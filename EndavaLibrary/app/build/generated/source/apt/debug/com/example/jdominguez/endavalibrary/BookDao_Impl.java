package com.example.jdominguez.endavalibrary;

import android.arch.lifecycle.ComputableLiveData;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.InvalidationTracker.Observer;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.arch.persistence.room.SharedSQLiteStatement;
import android.database.Cursor;
import android.support.annotation.NonNull;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SuppressWarnings("unchecked")
public class BookDao_Impl implements BookDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfBook;

  private final SharedSQLiteStatement __preparedStmtOfDeleteBook;

  private final SharedSQLiteStatement __preparedStmtOfUpdateBook;

  public BookDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfBook = new EntityInsertionAdapter<Book>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `book_table`(`id`,`name`,`author`,`isbn`,`language`,`publisher`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Book value) {
        stmt.bindLong(1, value.getId());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        if (value.getAuthor() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getAuthor());
        }
        stmt.bindLong(4, value.getIsbn());
        if (value.getLanguage() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getLanguage());
        }
        if (value.getPublisher() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getPublisher());
        }
      }
    };
    this.__preparedStmtOfDeleteBook = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM book_table WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateBook = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE book_table SET name = ?,author = ?,isbn = ?,language = ?,publisher = ? WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public void insert(Book book) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfBook.insert(book);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteBook(int id) {
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteBook.acquire();
    __db.beginTransaction();
    try {
      int _argIndex = 1;
      _stmt.bindLong(_argIndex, id);
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteBook.release(_stmt);
    }
  }

  @Override
  public void updateBook(String name, String author, int isbn, String language, String publisher,
      int id) {
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateBook.acquire();
    __db.beginTransaction();
    try {
      int _argIndex = 1;
      if (name == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, name);
      }
      _argIndex = 2;
      if (author == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, author);
      }
      _argIndex = 3;
      _stmt.bindLong(_argIndex, isbn);
      _argIndex = 4;
      if (language == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, language);
      }
      _argIndex = 5;
      if (publisher == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, publisher);
      }
      _argIndex = 6;
      _stmt.bindLong(_argIndex, id);
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfUpdateBook.release(_stmt);
    }
  }

  @Override
  public LiveData<List<Book>> retrieveBook() {
    final String _sql = "SELECT * FROM book_table";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return new ComputableLiveData<List<Book>>() {
      private Observer _observer;

      @Override
      protected List<Book> compute() {
        if (_observer == null) {
          _observer = new Observer("book_table") {
            @Override
            public void onInvalidated(@NonNull Set<String> tables) {
              invalidate();
            }
          };
          __db.getInvalidationTracker().addWeakObserver(_observer);
        }
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
          final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("name");
          final int _cursorIndexOfAuthor = _cursor.getColumnIndexOrThrow("author");
          final int _cursorIndexOfIsbn = _cursor.getColumnIndexOrThrow("isbn");
          final int _cursorIndexOfLanguage = _cursor.getColumnIndexOrThrow("language");
          final int _cursorIndexOfPublisher = _cursor.getColumnIndexOrThrow("publisher");
          final List<Book> _result = new ArrayList<Book>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Book _item;
            _item = new Book();
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            _item.setId(_tmpId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            _item.setName(_tmpName);
            final String _tmpAuthor;
            _tmpAuthor = _cursor.getString(_cursorIndexOfAuthor);
            _item.setAuthor(_tmpAuthor);
            final int _tmpIsbn;
            _tmpIsbn = _cursor.getInt(_cursorIndexOfIsbn);
            _item.setIsbn(_tmpIsbn);
            final String _tmpLanguage;
            _tmpLanguage = _cursor.getString(_cursorIndexOfLanguage);
            _item.setLanguage(_tmpLanguage);
            final String _tmpPublisher;
            _tmpPublisher = _cursor.getString(_cursorIndexOfPublisher);
            _item.setPublisher(_tmpPublisher);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    }.getLiveData();
  }
}
