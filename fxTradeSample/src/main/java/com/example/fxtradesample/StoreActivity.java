package com.example.fxtradesample;

import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SimpleCursorAdapter;

import java.math.BigDecimal;

public class StoreActivity extends ListActivity {

    private SQLiteDatabase db;
    private Cursor cursor;

    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private InstrumentDao instrumentDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_main);

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "instrument-db", null);
        db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
        instrumentDao = daoSession.getInstrumentDao();

        String textColumn = InstrumentDao.Properties.DisplayName.columnName;
        cursor = db.query(instrumentDao.getTablename(), instrumentDao.getAllColumns(), null, null, null, null, null);
        String[] from = { textColumn, InstrumentDao.Properties.Pip.columnName };
        int[] to = { android.R.id.text1, android.R.id.text2 };

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, cursor, from,
                to);
        setListAdapter(adapter);
        addInstruments();
    }

    private void addInstruments() {
        Instrument EURUSD = new Instrument("EUR_USD", "EUR/USD", BigDecimal.valueOf(0.0001), BigDecimal.valueOf(0.00001), 650);
        updateDB(EURUSD);
        Instrument AUDCAD = new Instrument("AUD_CAD", "AUD/CAD", BigDecimal.valueOf(0.0002), BigDecimal.valueOf(0.00005), 250);
        updateDB(AUDCAD);
        cursor.requery();
    }

    private void updateDB(Instrument instrument) {
        if (instrumentDao.load(instrument.instrument()) != null) {
            instrumentDao.update(instrument);
        }
        else {
            instrumentDao.insert(instrument);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.store, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
