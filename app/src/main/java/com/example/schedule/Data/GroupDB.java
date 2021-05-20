package com.example.schedule.Data;

import androidx.constraintlayout.widget.Group;
import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.schedule.Dao.GroupDao;

@Database(entities = {Group.class}, version = 1, exportSchema = false)
public abstract class GroupDB extends RoomDatabase {
    public abstract GroupDao groupDao();
}
