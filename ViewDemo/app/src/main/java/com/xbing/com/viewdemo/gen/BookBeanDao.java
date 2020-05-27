package com.xbing.com.viewdemo.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.xbing.com.viewdemo.db.greenDao.Bean.BookBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "BOOK_BEAN".
*/
public class BookBeanDao extends AbstractDao<BookBean, Long> {

    public static final String TABLENAME = "BOOK_BEAN";

    /**
     * Properties of entity BookBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Bid = new Property(0, long.class, "bid", true, "_id");
        public final static Property BookName = new Property(1, String.class, "bookName", false, "BOOK_NAME");
        public final static Property Price = new Property(2, int.class, "price", false, "PRICE");
        public final static Property PublishDate = new Property(3, long.class, "publishDate", false, "PUBLISH_DATE");
    }


    public BookBeanDao(DaoConfig config) {
        super(config);
    }
    
    public BookBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"BOOK_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY NOT NULL ," + // 0: bid
                "\"BOOK_NAME\" TEXT," + // 1: bookName
                "\"PRICE\" INTEGER NOT NULL ," + // 2: price
                "\"PUBLISH_DATE\" INTEGER NOT NULL );"); // 3: publishDate
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"BOOK_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, BookBean entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getBid());
 
        String bookName = entity.getBookName();
        if (bookName != null) {
            stmt.bindString(2, bookName);
        }
        stmt.bindLong(3, entity.getPrice());
        stmt.bindLong(4, entity.getPublishDate());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, BookBean entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getBid());
 
        String bookName = entity.getBookName();
        if (bookName != null) {
            stmt.bindString(2, bookName);
        }
        stmt.bindLong(3, entity.getPrice());
        stmt.bindLong(4, entity.getPublishDate());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.getLong(offset + 0);
    }    

    @Override
    public BookBean readEntity(Cursor cursor, int offset) {
        BookBean entity = new BookBean( //
            cursor.getLong(offset + 0), // bid
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // bookName
            cursor.getInt(offset + 2), // price
            cursor.getLong(offset + 3) // publishDate
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, BookBean entity, int offset) {
        entity.setBid(cursor.getLong(offset + 0));
        entity.setBookName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setPrice(cursor.getInt(offset + 2));
        entity.setPublishDate(cursor.getLong(offset + 3));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(BookBean entity, long rowId) {
        entity.setBid(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(BookBean entity) {
        if(entity != null) {
            return entity.getBid();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(BookBean entity) {
        throw new UnsupportedOperationException("Unsupported for entities with a non-null key");
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}