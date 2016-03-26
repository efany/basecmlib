package com.efan.basecmlib_sample;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.efan.basecmlib.activity.BaseFragmentActivity;
import com.efan.basecmlib.annotate.ContentView;
import com.efan.basecmlib.annotate.OnClick;
import com.efan.basecmlib.annotate.ViewInject;
import com.efan.basecmlib_sample.entity.DaoMaster;
import com.efan.basecmlib_sample.entity.DaoMaster.DevOpenHelper;
import com.efan.basecmlib_sample.entity.DaoSession;
import com.efan.basecmlib_sample.entity.Note;
import com.efan.basecmlib_sample.entity.NoteDao;

import java.text.DateFormat;
import java.util.Date;

@ContentView(id = R.layout.activity_main)
public class MainActivity extends BaseFragmentActivity {
    @ViewInject(id = R.id.main_text)
    private TextView textView;

    private SQLiteDatabase database;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private NoteDao noteDao;

    private Cursor cursor;
    @Override
    public void initView() {
        textView.setText("geweg");
        DevOpenHelper openHelper = new DevOpenHelper(this, "notes-db", null);
        database = openHelper.getWritableDatabase();
        daoMaster = new DaoMaster(database);
        daoSession = daoMaster.newSession();
        noteDao = daoSession.getNoteDao();

        String textColumn = NoteDao.Properties.Id.columnName;
        String orderBy = textColumn + " COLLATE LOCALIZED ASC";
        cursor = database.query(noteDao.getTablename(), noteDao.getAllColumns(), null, null, null, null, orderBy);

        final DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
        String comment = "Added on " + df.format(new Date());
        Note note = new Note(null, "dfgerg", comment, new Date());
        noteDao.insert(note);


        String comment2 = "Added on " + df.format(new Date());
        Note note2 = new Note(null, "dfwegvweggerg", comment2, new Date());
        noteDao.insert(note2);


        while (cursor.moveToNext()){
            Log.d("haha", String.valueOf(cursor.getInt(0)));
        }
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }

    @Override
    @OnClick(value = {R.id.main_text})
    public void onClick(View v) {
        Toast.makeText(MainActivity.this,"egewg",Toast.LENGTH_SHORT).show();
    }
}
