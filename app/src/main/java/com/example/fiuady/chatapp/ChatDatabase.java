package com.example.fiuady.chatapp;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

@Database(entities = {UsersTable.class, MessagesTable.class}, version = 1)

public abstract class ChatDatabase extends RoomDatabase {

    public abstract UsersDao usersDao();
    public abstract MessagesDao messagesDao();

    private static ChatDatabase dbChat;

    public static ChatDatabase getDatabase(final Context context) {
        if (dbChat == null) {
                    dbChat = Room.databaseBuilder(context.getApplicationContext(),
                            ChatDatabase.class, "chatapp").allowMainThreadQueries().addCallback(new RoomDatabase.Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);

                            db.execSQL("INSERT INTO users(id, first_name, last_name, phone_number, password) VALUES (0, 'Sara', 'Torres', '9991234567', '1234')");
                            db.execSQL("INSERT INTO users(id, first_name, last_name, phone_number, password) VALUES (1, 'Gerardo', 'Canul', '9992764598', '1234')");
                            db.execSQL("INSERT INTO users(id, first_name, last_name, phone_number, password) VALUES (2, 'Jorge', 'Albores', '9992468649', '1234')");
                            db.execSQL("INSERT INTO users(id, first_name, last_name, phone_number, password) VALUES (3, 'Eduardo', 'Chan', '9991895311', '1234')");
                            db.execSQL("INSERT INTO users(id, first_name, last_name, phone_number, password) VALUES (4, 'Marisol', 'Uno', '9991362718', '1234')");
                            db.execSQL("INSERT INTO users(id, first_name, last_name, phone_number, password) VALUES (5, 'Marisol', 'Dos', '9993598437', '1234')");

                            db.execSQL("INSERT INTO messages(id, message, sender_id, receiver_id) VALUES (0, 'Jose a Sara', 3, 0)");
                            db.execSQL("INSERT INTO messages(id, message, sender_id, receiver_id) VALUES (1, 'Marisol 1 a Jose', 4, 3)");
                            db.execSQL("INSERT INTO messages(id, message, sender_id, receiver_id) VALUES (2, 'Jose a Ger', 3, 1)");
                            db.execSQL("INSERT INTO messages(id, message, sender_id, receiver_id) VALUES (3, 'Albores a Jose', 2, 3)");
                            db.execSQL("INSERT INTO messages(id, message, sender_id, receiver_id) VALUES (4, 'Jose a Dos', 3, 5)");
                        }
                    }).build();

            }
        return dbChat;
        }
}

