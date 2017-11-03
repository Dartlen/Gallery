package by.test.dartlen.gallery.data.local.greendao;

import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.SqlUtils;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "Images".
*/
public class ImagesDao extends AbstractDao<Images, Long> {

    public static final String TABLENAME = "Images";

    /**
     * Properties of entity Images.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Url = new Property(1, String.class, "url", false, "URL");
        public final static Property Date = new Property(2, Integer.class, "date", false, "DATE");
        public final static Property Lat = new Property(3, Float.class, "lat", false, "LAT");
        public final static Property Lng = new Property(4, Float.class, "lng", false, "LNG");
        public final static Property UserId = new Property(5, long.class, "userId", false, "USER_ID");
    }

    private DaoSession daoSession;


    public ImagesDao(DaoConfig config) {
        super(config);
    }
    
    public ImagesDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"Images\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"URL\" TEXT NOT NULL ," + // 1: url
                "\"DATE\" INTEGER NOT NULL ," + // 2: date
                "\"LAT\" REAL NOT NULL ," + // 3: lat
                "\"LNG\" REAL NOT NULL ," + // 4: lng
                "\"USER_ID\" INTEGER NOT NULL );"); // 5: userId
        // Add Indexes
        db.execSQL("CREATE UNIQUE INDEX " + constraint + "IDX_Images_URL ON \"Images\"" +
                " (\"URL\" ASC);");
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"Images\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Images entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getUrl());
        stmt.bindLong(3, entity.getDate());
        stmt.bindDouble(4, entity.getLat());
        stmt.bindDouble(5, entity.getLng());
        stmt.bindLong(6, entity.getUserId());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Images entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getUrl());
        stmt.bindLong(3, entity.getDate());
        stmt.bindDouble(4, entity.getLat());
        stmt.bindDouble(5, entity.getLng());
        stmt.bindLong(6, entity.getUserId());
    }

    @Override
    protected final void attachEntity(Images entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Images readEntity(Cursor cursor, int offset) {
        Images entity = new Images( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getString(offset + 1), // url
            cursor.getInt(offset + 2), // date
            cursor.getFloat(offset + 3), // lat
            cursor.getFloat(offset + 4), // lng
            cursor.getLong(offset + 5) // userId
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Images entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setUrl(cursor.getString(offset + 1));
        entity.setDate(cursor.getInt(offset + 2));
        entity.setLat(cursor.getFloat(offset + 3));
        entity.setLng(cursor.getFloat(offset + 4));
        entity.setUserId(cursor.getLong(offset + 5));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Images entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Images entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Images entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getUsersDao().getAllColumns());
            builder.append(" FROM Images T");
            builder.append(" LEFT JOIN Users T0 ON T.\"USER_ID\"=T0.\"_id\"");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected Images loadCurrentDeep(Cursor cursor, boolean lock) {
        Images entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        Users user = loadCurrentOther(daoSession.getUsersDao(), cursor, offset);
         if(user != null) {
            entity.setUser(user);
        }

        return entity;    
    }

    public Images loadDeep(Long key) {
        assertSinglePk();
        if (key == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(getSelectDeep());
        builder.append("WHERE ");
        SqlUtils.appendColumnsEqValue(builder, "T", getPkColumns());
        String sql = builder.toString();
        
        String[] keyArray = new String[] { key.toString() };
        Cursor cursor = db.rawQuery(sql, keyArray);
        
        try {
            boolean available = cursor.moveToFirst();
            if (!available) {
                return null;
            } else if (!cursor.isLast()) {
                throw new IllegalStateException("Expected unique result, but count was " + cursor.getCount());
            }
            return loadCurrentDeep(cursor, true);
        } finally {
            cursor.close();
        }
    }
    
    /** Reads all available rows from the given cursor and returns a list of new ImageTO objects. */
    public List<Images> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<Images> list = new ArrayList<Images>(count);
        
        if (cursor.moveToFirst()) {
            if (identityScope != null) {
                identityScope.lock();
                identityScope.reserveRoom(count);
            }
            try {
                do {
                    list.add(loadCurrentDeep(cursor, false));
                } while (cursor.moveToNext());
            } finally {
                if (identityScope != null) {
                    identityScope.unlock();
                }
            }
        }
        return list;
    }
    
    protected List<Images> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<Images> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}
