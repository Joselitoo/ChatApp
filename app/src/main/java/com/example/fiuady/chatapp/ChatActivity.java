package com.example.fiuady.chatapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ChatActivity extends AppCompatActivity {

    public final static String EXTRA_USER_NAME = "EXTRA_USER_NAME";

    private EditText toSendText;
    private Button sendButton;
    private TextView tvContactName;
    private TextView receivedText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        toSendText = findViewById(R.id.to_send_text_contact_chat);
        sendButton = findViewById(R.id.to_send_button_contact_chat);
        tvContactName = findViewById(R.id.tv_contact_name);

        Intent intent = getIntent();
        String received_user_name = intent.getStringExtra(ChatActivity.EXTRA_USER_NAME);
        tvContactName.setText(received_user_name);




    }
}
