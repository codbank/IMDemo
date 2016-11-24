package com.example.blksupm.im.main;

import com.example.blksupm.im.main.MainBiz;
import com.example.blksupm.im.model.User;
import com.example.blksupm.im.mvpinterface.IMainBiz;
import com.example.blksupm.im.mvpinterface.IMainUIView;
import com.example.blksupm.im.util.HttpUtil;

import rx.Subscriber;

/**
 * Created by blksupm on 2016/11/24.
 */
public class MainPresenter {
    private IMainBiz mainBiz;
    private IMainUIView mainUIView;

    public MainPresenter(IMainUIView mainUIView)
    {
        this.mainUIView = mainUIView;
        this.mainBiz = new MainBiz();
    }

    public void userRegister(final String userId,String name) {
        HttpUtil.getToken(userId, name, new Subscriber<User>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(User user) {
                mainUIView.showUserInfo(user);
            }
        });
    }
}
