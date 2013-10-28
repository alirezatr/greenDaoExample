package com.example.fxtradesample;

import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SimpleCursorAdapter;

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

        String textColumn = InstrumentDao.Properties.Instrument.columnName;
        cursor = db.query(instrumentDao.getTablename(), instrumentDao.getAllColumns(), null, null, null, null, null);
        String[] from = { textColumn };
        int[] to = { android.R.id.text1 };

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, cursor, from,
                to);
        setListAdapter(adapter);
        addInstrument();
    }

    private void addInstrument() {
        Instrument instrument = new Instrument("EUR/USD", "EUR_USD", null, null, 0);
        //Instrument instrument2 = new Instrument("AUD/CAD", "AUD_CAD", null, null, 0);
        instrumentDao.insert(instrument);
        //instrumentDao.insert(instrument2);
        cursor.requery();
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
