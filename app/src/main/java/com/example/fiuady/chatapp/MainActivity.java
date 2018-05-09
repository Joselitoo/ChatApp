package com.example.fiuady.chatapp;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.stetho.Stetho;

import java.util.List;

class User {
    private int id;
    private String firstName;
    private String lastName;
    private int phoneNumber;

    public int getId(){return id; }
    public String getFirstName(){ return firstName;}
    public String getLastName(){ return lastName;}
    public int getPhoneNumber(){ return phoneNumber;}


    public User(int id, String firstName, String lastName, int phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }
}

class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {

    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvFirstName;
        private TextView tvLastName;
        private TextView tvPhoneNumber;
        private User user;

        public ViewHolder(View itemView) {
            super(itemView);

            tvFirstName = itemView.findViewById(R.id.last_name_text);
            tvLastName = itemView.findViewById(R.id.first_name_text);
            tvPhoneNumber = itemView.findViewById(R.id.phone_number_text);
        }

        public void bind(User users){
            this.user = users;
            tvFirstName.setText(users.getFirstName());
            tvLastName.setText(users.getLastName());
            tvPhoneNumber.setText(users.getPhoneNumber());
        }
    }

    private List<User> users;

    public UsersAdapter(List<User> users) {
        super();
        this.users = users;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rvusers_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(users.get(position));

    }

    @Override
    public int getItemCount() {
        return users.size();
    }


}

public class MainActivity extends AppCompatActivity {

    private ChatDatabase db;
    private Button login_btn;
    private EditText user_name;
    private EditText user_pass;
    private int destinyPort;
    private int originPort;
    private int destinyId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Stetho.initializeWithDefaults(this);

//        ChatDatabase db = Room.databaseBuilder(getApplicationContext(),
//                ChatDatabase.class, "chatapp").allowMainThreadQueries().addCallback(new RoomDatabase.Callback() {
//            @Override
//            public void onCreate(@NonNull SupportSQLiteDatabase db) {
//                super.onCreate(db);
//
//                db.execSQL("INSERT INTO users(id, first_name, last_name, phone_number, password) VALUES (0, 'Sara', 'Torres', 9991234567, '1234')");
//                db.execSQL("INSERT INTO users(id, first_name, last_name, phone_number, password) VALUES (1, 'Gerardo', 'Canul', 9992764598, '1234')");
//                db.execSQL("INSERT INTO users(id, first_name, last_name, phone_number, password) VALUES (2, 'Jorge', 'Albores', 9992468649, '1234')");
//                db.execSQL("INSERT INTO users(id, first_name, last_name, phone_number, password) VALUES (3, 'Eduardo', 'Chan', 9991895311, '1234')");
//                db.execSQL("INSERT INTO users(id, first_name, last_name, phone_number, password) VALUES (4, 'Marisol', 'Uno', 9991362718, '1234')");
//                db.execSQL("INSERT INTO users(id, first_name, last_name, phone_number, password) VALUES (5, 'Marisol', 'Dos', 9993598437, '1234')");
//            }
//        }).build();

        UsersTable uno = db.usersDao().getUserByLastName("Chan");
        uno.setFirstName("Jose");
        db.usersDao().UpdateUser(uno);

        login_btn = findViewById(R.id.login_btn);
        user_name = findViewById(R.id.name_txt);
        user_pass = findViewById(R.id.pass_txt);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NavigationMenu.class);
                //intent.putExtra(ChatActivity.EXTRA_USER_NAME, user_name.getText().toString());
                startActivity(intent);
            }
        });


    }
}
