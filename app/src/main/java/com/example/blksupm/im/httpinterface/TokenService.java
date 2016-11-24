package com.example.blksupm.im.httpinterface;

import com.example.blksupm.im.model.User;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by blksupm on 2016/11/23.
 */
public interface TokenService {
    @FormUrlEncoded
    @POST("getToken.json")
    Observable<User> getUserToken(@Field("userId") String userId, @Field("name") String username, @Field("portraitUri") String portraitUri);
}
