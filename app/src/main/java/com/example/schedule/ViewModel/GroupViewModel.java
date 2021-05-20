package com.example.schedule.ViewModel;

import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.schedule.Model.Group;
import com.example.schedule.Model.GroupResponse;
import com.example.schedule.Repository.Repository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Function;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class GroupViewModel extends ViewModel {
    private static final String TAG = "GroupViewModel";

    private Repository repository;
    private MutableLiveData<ArrayList<Group>> groupList = new MutableLiveData<>();
    private LiveData<List<Group>> favoriteGroupList = null;

    @ViewModelInject
    public GroupViewModel(Repository repository) {
        this.repository = repository;
        favoriteGroupList = repository.getFavoriteGroup();
    }

    public MutableLiveData<ArrayList<Group>> getGroupList() {
        return groupList;
    }

    public void getGroups(){
        repository.getGroups()
                .subscribeOn(Schedulers.io())
                .map(new Function<GroupResponse, ArrayList<Group>>() {
                    @Override
                    public ArrayList<Group> apply(GroupResponse groupResponse) throws Throwable {
                        ArrayList<Group> list = groupResponse.getResults();
                        for (Group group : list) {
                            String name = group.getName();
                            group.setName(name);
                        }
                        Log.e(TAG, "apply: " + list.get(2).getName());
                        return list;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> groupList.setValue(result),
                        error-> Log.e(TAG, "getPokemons: " + error.getMessage() ));
    }

    public void insertGroup(Group group) {
        repository.insertGroup(group);
    }

    public void deleteGroup(String groupName) {
        repository.deleteGroup(groupName);
    }

    public LiveData<List<Group>> getFavoriteGroupList() {
        return favoriteGroupList;
    }

    public void getFavoriteGroup() {
        favoriteGroupList = repository.getFavoriteGroup();
    }
}
