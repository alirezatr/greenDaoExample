package com.example.fxtradesample;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.math.BigDecimal;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

/** 
 * DAO for table INSTRUMENT.
*/
public class InstrumentDao extends AbstractDao<Instrument, String> {

    public static final String TABLENAME = "INSTRUMENT";

    /**
     * Properties of entity Instrument.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Instrument = new Property(0, String.class, "instrument", true, "_id");
        public final static Property DisplayName = new Property(1, String.class, "displayName", false, "DISPLAY_NAME");
        public final static Property Pip = new Property(2, String.class, "pip", false, "PIP");
        public final static Property Precision = new Property(3, String.class, "precision", false, "PRECISION");
        public final static Property MaxTradeUnits = new Property(4, Integer.class, "maxTradeUnits", false, "MAX_TRADE_UNITS");
    };

    public InstrumentDao(DaoConfig config) {
        super(config);
    }
    
    public InstrumentDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'INSTRUMENT' (" + //
                "'_id' TEXT PRIMARY KEY ," + // 0: id
                "'DISPLAY_NAME' TEXT," + // 1: displayName
                "'PIP' TEXT," + // 2: pip
                "'PRECISION' TEXT," + // 3: precision
                "'MAX_TRADE_UNITS' INTEGER);"); // 4: maxTradeUnits
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'INSTRUMENT'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Instrument entity) {
        stmt.clearBindings();
 
        String id = entity.instrument();
        if (id != null) {
            stmt.bindString(1, id);
        }
 
        String displayName = entity.displayName();
        if (displayName != null) {
            stmt.bindString(2, displayName);
        }

        BigDecimal pip = entity.pip();
        if (displayName != null) {
            stmt.bindString(3, pip.toPlainString());
        }

        BigDecimal precision = entity.precision();
        if (displayName != null) {
            stmt.bindString(4, precision.toPlainString());
        }
 
        Integer maxTradeUnits = entity.maxTradeUnits();
        if (maxTradeUnits != null) {
            stmt.bindLong(5, maxTradeUnits);
        }
    }

    /** @inheritdoc */
    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Instrument readEntity(Cursor cursor, int offset) {
        Instrument entity = new Instrument( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // displayName
            cursor.isNull(offset + 2) ? null : new BigDecimal(cursor.getString(offset + 2)),
            cursor.isNull(offset + 3) ? null : new BigDecimal(cursor.getString(offset + 3)),
            cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4) // maxTradeUnits
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Instrument entity, int offset) {}
    
    /** @inheritdoc */
    @Override
    protected String updateKeyAfterInsert(Instrument entity, long rowId) {
        return null;
    }
    
    /** @inheritdoc */
    @Override
    public String getKey(Instrument entity) {
        if(entity != null) {
            return entity.instrument();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
