package com.antondon.githubreposearch.data.source.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.antondon.githubreposearch.data.Repository;

/**
 * Created by anton on 11/29/17.
 */

@Database(version = 1, entities = Repository.class)
public abstract class RepositoriesDatabase extends RoomDatabase {

    private static RepositoriesDatabase INSTANCE;

    public static RepositoriesDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), RepositoriesDatabase.class,
                    "Repositories.db").build();
        }
        return INSTANCE;
    }

    public abstract RepositoriesDao repoDao();

}
