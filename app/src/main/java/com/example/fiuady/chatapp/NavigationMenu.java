package com.example.fiuady.chatapp;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomOpenHelper;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;

import com.facebook.stetho.Stetho;

import java.util.ArrayList;
import java.util.List;

public class NavigationMenu extends AppCompatActivity {

    private ChatDatabase db;
    private TextView mTextMessage;
    private RecyclerView rvUsers;
    private UsersAdapter rvUsersAdapter;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_chats:
                    //mTextMessage.setText("Chats");
                    return true;
                case R.id.navigation_groups:
                    //mTextMessage.setText("Groups");
                    return true;
                case R.id.navigation_contacts:
                    rvUsers.setAdapter(rvUsersAdapter);
                    return true;
            }
            return false;
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_menu);

        Stetho.initializeWithDefaults(this);

        rvUsers = findViewById(R.id.recycler_view_container);

        List<User> rv_users_data = new ArrayList<>();
        int total_users = db.usersDao().getMaxId() + 1;
        for (int i = 0; i < total_users; i++){
            rv_users_data.add(new User(
                    i,
                    db.usersDao().getFirstNameById(i),
                    db.usersDao().getLastNameById(i),
                    db.usersDao().getPhoneNumberById(i)));
        }
        rvUsersAdapter = new UsersAdapter((rv_users_data));

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);



    }


}
