package com.app.registerandlogin;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText mEtUserName, mEtPass;
    TextView mTvClickToRegister;
    Button mBtnLogin;
    Database mMyDB;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mMyDB = new Database(this);
        init();
    }

    private void init() {

        mEtUserName = (EditText) findViewById(R.id.idEtLloginId);
        mEtPass = (EditText) findViewById(R.id.idEtLloginPass);
        mTvClickToRegister = (TextView) findViewById(R.id.idTvLregisterLink);
        mBtnLogin = (Button) findViewById(R.id.idBtnLlogin);

        loginMethod();
        registerMethoddd();
    }


    public void loginMethod() {



        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (isValid()) {
                    String userName, pass;
                    userName = mEtUserName.getText().toString();
                    pass = mEtPass.getText().toString();
            mMyDB.open();
                    Cursor res = mMyDB.getlogin(userName, pass);
                    if (res.getCount() == 0) {
                        Toast.makeText(Login.this, "Kindly Register First", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {

                        Toast.makeText(Login.this, "Login Successfully", Toast.LENGTH_SHORT).show();

                    }
                }
                else {

                    Toast.makeText(Login.this, "Kindly Enter UserName Password", Toast.LENGTH_SHORT).show();
                }


            }


        });

    }

    private boolean isValid() {

        if(mEtUserName.getText().toString().equals(""))
        {
            return false;
        }
        else if ( mEtPass.getText().toString().equals("")) {
            return false;
        }
        return true;
    }


    public void registerMethoddd() {

        mTvClickToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);

            }
        });

    }

    @Override
    protected void onDestroy() {
        mMyDB.close();
        super.onDestroy();
    }
}
