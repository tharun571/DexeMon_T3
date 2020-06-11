package com.example.dexemon_t3.fav;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


@Database(entities = favo.class, exportSchema = false,version = 1)
public abstract class favDatabase extends RoomDatabase {

    private static favDatabase instance;

    public abstract favDao favdao();

    public static synchronized favDatabase getInstance(Context context){

        if(instance==null){

            instance= Room.databaseBuilder(context.getApplicationContext(),
                    favDatabase.class,"fav_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .allowMainThreadQueries()
                    .build();
        }

        return instance;

    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private favDao noteDao;
        private PopulateDbAsyncTask(favDatabase db) {
            noteDao = db.favdao();
        }
        @Override
        protected Void doInBackground(Void... voids) {

            return null;
        }
    }


}
