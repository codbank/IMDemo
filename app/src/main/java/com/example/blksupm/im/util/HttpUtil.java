package com.example.blksupm.im.util;

import com.example.blksupm.im.httpinterface.TokenService;
import com.example.blksupm.im.model.User;

import java.io.IOException;
import java.util.Random;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by blksupm on 2016/11/23.
 */
public class HttpUtil {

    private static final String RY_APP_KEY = "x4vkb1qpxlfqk";
    private static final String RY_APP_SECRET = "wc5U8rnXHZo";

    //进行网络请求
    public static void getToken(String userId,String username,Subscriber<User> subscriber){
        String baseUrl = "https://api.cn.ronghub.com/user/";

        // Log信息
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        // Define the interceptor, add authentication headers
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Random r = new Random();
                        String Nonce = String.valueOf(r.nextInt(10000) + 10000);
                        String Timestamp = String.valueOf(System.currentTimeMillis() / 1000);
                        Request request = chain.request();
                        Request.Builder builder1 = request.newBuilder();
                        Request build = builder1.addHeader("App-Key", RY_APP_KEY)
                                .addHeader("Nonce", Nonce)
                                .addHeader("Timestamp", Timestamp)
                                .addHeader("Signature",
                                        MD5.encryptToSHA(RY_APP_SECRET + Nonce + Timestamp))
                                .build();
                        return chain.proceed(build);
                    }
                })
                .addInterceptor(loggingInterceptor)
                .retryOnConnectionFailure(true)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        TokenService tokenService = retrofit.create(TokenService.class);
        tokenService.getUserToken(userId,username,"http%3A%2F%2Fabc.com%2Fmyportrait.jpg")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
