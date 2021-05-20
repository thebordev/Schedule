package com.example.schedule.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.schedule.Model.Group;

import java.util.List;

@Dao
public interface GroupDao {

    @Insert
    void insertGroup(Group group);

    @Query("DELETE FROM `group` WHERE name = :groupName")
    void deleteGroup(String groupName);

    @Query("DELETE FROM `group`")
    void deleteAll();

    @Query("SELECT * FROM `group`")
    LiveData<List<Group>> getFavoriteGroup();
}
