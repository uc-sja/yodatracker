package com.test.locationupdates.model.room;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@SuppressWarnings({"unchecked", "deprecation"})
public final class UserLocationDao_Impl implements UserLocationDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<UserLocation> __insertionAdapterOfUserLocation;

  private final MyLocationTypeConverters __myLocationTypeConverters = new MyLocationTypeConverters();

  public UserLocationDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfUserLocation = new EntityInsertionAdapter<UserLocation>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `user_location` (`id`,`latitude`,`longitude`,`date`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, UserLocation value) {
        stmt.bindLong(1, value.getId());
        if (value.getLatitude() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getLatitude());
        }
        if (value.getLongitude() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getLongitude());
        }
        final Long _tmp;
        _tmp = __myLocationTypeConverters.fromDate(value.getDate());
        if (_tmp == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindLong(4, _tmp);
        }
      }
    };
  }

  @Override
  public Object addLocation(final UserLocation location,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfUserLocation.insert(location);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public LiveData<List<UserLocation>> getLocations() {
    final String _sql = "SELECT * FROM  user_location ORDER BY date DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"user_location"}, false, new Callable<List<UserLocation>>() {
      @Override
      public List<UserLocation> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfLatitude = CursorUtil.getColumnIndexOrThrow(_cursor, "latitude");
          final int _cursorIndexOfLongitude = CursorUtil.getColumnIndexOrThrow(_cursor, "longitude");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final List<UserLocation> _result = new ArrayList<UserLocation>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final UserLocation _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpLatitude;
            if (_cursor.isNull(_cursorIndexOfLatitude)) {
              _tmpLatitude = null;
            } else {
              _tmpLatitude = _cursor.getString(_cursorIndexOfLatitude);
            }
            final String _tmpLongitude;
            if (_cursor.isNull(_cursorIndexOfLongitude)) {
              _tmpLongitude = null;
            } else {
              _tmpLongitude = _cursor.getString(_cursorIndexOfLongitude);
            }
            final Date _tmpDate;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfDate)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfDate);
            }
            _tmpDate = __myLocationTypeConverters.toDate(_tmp);
            _item = new UserLocation(_tmpId,_tmpLatitude,_tmpLongitude,_tmpDate);
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
    });
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
