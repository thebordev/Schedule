package com.example.schedule.Di;


import android.app.Application;

import androidx.room.Room;

import com.example.schedule.Dao.GroupDao;
import com.example.schedule.Data.GroupDB;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class)
public class DataBaseModule {

    @Provides
    @Singleton
    public static GroupDB provideGroupDB(Application application) {
        return Room.databaseBuilder(application, GroupDB.class, "Favorite Database")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }

    @Provides
    @Singleton
    public static GroupDao provideGroupDao(GroupDB groupDB) {
        return groupDB.groupDao();
    }
}
