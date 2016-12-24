package com.app.registerandlogin;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    EditText mEtFname,mEtLname,mEtUserName,mEtPass,mEtMobNo;
    Database mMyDB;
    Button mBtnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mMyDB = new Database(this);
        init();
    }

    private void init() {

        mEtFname=(EditText)findViewById(R.id.idEtR_Fname);
        mEtLname=(EditText)findViewById(R.id.idEtR_Lname) ;
        mEtUserName=(EditText)findViewById(R.id.idEtR_UserName);
        mEtPass=(EditText)findViewById(R.id.idEtR_Password);
        mEtMobNo=(EditText)findViewById(R.id.idEtR_MobNo);
        mBtnRegister=(Button)findViewById(R.id.idBtnRregister);
        registerMethod();


    }


    public void registerMethod()
    {

        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isValid()) {

                    String fName, lName, userName, pass, mobNo;
                    fName = mEtFname.getText().toString();
                    lName = mEtLname.getText().toString();
                    userName = mEtUserName.getText().toString();
                    pass = mEtPass.getText().toString();
                    mobNo = mEtMobNo.getText().toString();
                    boolean isInserted;
                    isInserted = mMyDB.insertDatabase(fName, lName, userName, pass, mobNo);

                    if (isInserted == true) {

                        Toast.makeText(Register.this, "SuccessFull Inserted", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Register.this, "Not Inserted Kindly Register it Again", Toast.LENGTH_SHORT).show();
                    }

                    Intent intent = new Intent(Register.this, Login.class);
                    startActivity(intent);

                } else {

                    Toast.makeText(Register.this, "Please Enter All Detail", Toast.LENGTH_SHORT).show();
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
        else if ( mEtMobNo.getText().toString().equals("")) {
            return false;
        }
        else if ( mEtFname.getText().toString().equals("")) {
            return false;
        }
        else if ( mEtLname.getText().toString().equals("")) {
            return false;
        }

        return true;
    }


}
