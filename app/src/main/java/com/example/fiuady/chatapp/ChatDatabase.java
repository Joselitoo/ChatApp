package com.example.fiuady.chatapp;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

@Database(entities = {UsersTable.class}, version = 1)
public abstract class ChatDatabase extends RoomDatabase {

    private ChatDatabase db2;

    public abstract UsersDao usersDao();
    
    public ChatDatabase db(Context context) {
        db2 = Room.databaseBuilder(context.getApplicationContext(),
                ChatDatabase.class, "chatapp").allowMainThreadQueries().addCallback(new RoomDatabase.Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);

                db.execSQL("INSERT INTO users(id, first_name, last_name, phone_number, password) VALUES (0, 'Sara', 'Torres', 9991234567, '1234')");
                db.execSQL("INSERT INTO users(id, first_name, last_name, phone_number, password) VALUES (1, 'Gerardo', 'Canul', 9992764598, '1234')");
                db.execSQL("INSERT INTO users(id, first_name, last_name, phone_number, password) VALUES (2, 'Jorge', 'Albores', 9992468649, '1234')");
                db.execSQL("INSERT INTO users(id, first_name, last_name, phone_number, password) VALUES (3, 'Eduardo', 'Chan', 9991895311, '1234')");
                db.execSQL("INSERT INTO users(id, first_name, last_name, phone_number, password) VALUES (4, 'Marisol', 'Uno', 9991362718, '1234')");
                db.execSQL("INSERT INTO users(id, first_name, last_name, phone_number, password) VALUES (5, 'Marisol', 'Dos', 9993598437, '1234')");
            }
        }).build();
        return db2;
    }

}
