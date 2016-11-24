package com.example.blksupm.im.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.blksupm.im.R;
import com.example.blksupm.im.model.User;
import com.example.blksupm.im.mvpinterface.IMainUIView;

import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements IMainUIView{

    @Bind(R.id.user_id)
    TextView userId;

    @Bind(R.id.user_name)
    TextView userName;

    @Bind(R.id.user_token)
    TextView userToken;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        mainPresenter = new MainPresenter(this);

        userRegister(String.valueOf(new Random().nextInt(10)),"haha");
    }

    private void userRegister(String userId,String name) {
        mainPresenter.userRegister(userId,name);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showUserInfo(User user) {
        userId.setText(user.getUserId());
        userName.setText(user.getName());
        userToken.setText(user.getToken());
    }
}
