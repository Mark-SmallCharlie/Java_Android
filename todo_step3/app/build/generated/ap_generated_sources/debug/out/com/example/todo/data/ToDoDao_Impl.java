package com.example.todo.data;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class ToDoDao_Impl implements ToDoDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ToDoItem> __insertionAdapterOfToDoItem;

  private final EntityDeletionOrUpdateAdapter<ToDoItem> __deletionAdapterOfToDoItem;

  public ToDoDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfToDoItem = new EntityInsertionAdapter<ToDoItem>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `ToDoItem` (`id`,`deadline`,`description`,`location`,`person`,`level`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ToDoItem value) {
        stmt.bindLong(1, value.id);
        if (value.deadline == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.deadline);
        }
        if (value.description == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.description);
        }
        if (value.location == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.location);
        }
        if (value.person == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.person);
        }
        if (value.level == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.level);
        }
      }
    };
    this.__deletionAdapterOfToDoItem = new EntityDeletionOrUpdateAdapter<ToDoItem>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `ToDoItem` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ToDoItem value) {
        stmt.bindLong(1, value.id);
      }
    };
  }

  @Override
  public void add(final ToDoItem item) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfToDoItem.insert(item);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final ToDoItem item) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfToDoItem.handle(item);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<ToDoItem> getAll() {
    final String _sql = "select * from todoitem";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfDeadline = CursorUtil.getColumnIndexOrThrow(_cursor, "deadline");
      final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
      final int _cursorIndexOfLocation = CursorUtil.getColumnIndexOrThrow(_cursor, "location");
      final int _cursorIndexOfPerson = CursorUtil.getColumnIndexOrThrow(_cursor, "person");
      final int _cursorIndexOfLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "level");
      final List<ToDoItem> _result = new ArrayList<ToDoItem>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final ToDoItem _item;
        _item = new ToDoItem();
        _item.id = _cursor.getInt(_cursorIndexOfId);
        if (_cursor.isNull(_cursorIndexOfDeadline)) {
          _item.deadline = null;
        } else {
          _item.deadline = _cursor.getString(_cursorIndexOfDeadline);
        }
        if (_cursor.isNull(_cursorIndexOfDescription)) {
          _item.description = null;
        } else {
          _item.description = _cursor.getString(_cursorIndexOfDescription);
        }
        if (_cursor.isNull(_cursorIndexOfLocation)) {
          _item.location = null;
        } else {
          _item.location = _cursor.getString(_cursorIndexOfLocation);
        }
        if (_cursor.isNull(_cursorIndexOfPerson)) {
          _item.person = null;
        } else {
          _item.person = _cursor.getString(_cursorIndexOfPerson);
        }
        if (_cursor.isNull(_cursorIndexOfLevel)) {
          _item.level = null;
        } else {
          _item.level = _cursor.getString(_cursorIndexOfLevel);
        }
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
