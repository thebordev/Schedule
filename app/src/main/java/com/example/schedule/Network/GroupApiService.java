package com.example.schedule.Network;

import com.example.schedule.Model.GroupResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface GroupApiService {

    @GET("groups")
    Observable<GroupResponse> getGroups();
}
