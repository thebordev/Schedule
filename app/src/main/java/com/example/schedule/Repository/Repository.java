package com.example.schedule.Repository;

import androidx.lifecycle.LiveData;

import com.example.schedule.Dao.GroupDao;
import com.example.schedule.Model.Group;
import com.example.schedule.Model.GroupResponse;
import com.example.schedule.Network.GroupApiService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class Repository {

    private GroupDao groupDao;
    private GroupApiService apiService;

    @Inject
    public Repository(GroupDao groupDao, GroupApiService apiService) {
        this.groupDao = groupDao;
        this.apiService = apiService;
    }

    public Observable<GroupResponse> getGroups() {
        return apiService.getGroups();
    }

    public void insertGroup(Group group) {
        groupDao.insertGroup(group);
    }

    public void deleteGroup(String groupName) {
        groupDao.deleteGroup(groupName);
    }

    public void deleteAll() {
        groupDao.deleteAll();
    }

    public LiveData<List<Group>> getFavoriteGroup() {
        return groupDao.getFavoriteGroup();
    }
}
