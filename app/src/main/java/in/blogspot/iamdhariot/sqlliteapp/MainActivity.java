package in.blogspot.iamdhariot.sqlliteapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mBtnSave,mBtnShow;
    private EditText mName;
    private EditText mMobileNumber;

    // creating databaseHandler object
    private DatabaseHandler databaseHandler;

    // progress dialog
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnSave = (Button)findViewById(R.id.btnSave);
        mBtnShow = (Button)findViewById(R.id.btnShow);
        mName = (EditText)findViewById(R.id.nameETView);
        mMobileNumber = (EditText)findViewById(R.id.mobileETView);
        mBtnSave.setOnClickListener(this);
        mBtnShow.setOnClickListener(this);

        // databaseHandler reference
        databaseHandler = new DatabaseHandler(this);
       // progress dialog reference
        progressDialog = new ProgressDialog(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSave:
                // To do store contact to SQLite
                contactsStore();
                break;
            case R.id.btnShow:

                startActivity(new Intent(MainActivity.this,ShowActivity.class));
                break;
        }
    }
    private void contactsStore(){
        String name = mName.getText().toString().trim();
        String mobileNumber = mMobileNumber.getText().toString().trim();
        progressDialog.setMessage("Inserting contact...");
        progressDialog.show();
        databaseHandler.addContact(new Contact(name,mobileNumber));

       /*
       for testing purpose

        databaseHandler.addContact(new Contact("Ravi", "9100000000"));
        databaseHandler.addContact(new Contact("Srinivas", "9199999999"));
        databaseHandler.addContact(new Contact("Tommy", "9522222222"));
        databaseHandler.addContact(new Contact("Karthik", "9533333333"));
         */
        progressDialog.dismiss();
        Toast.makeText(this,"Contact inserted",Toast.LENGTH_SHORT).show();

        // reading the contacts
        List<Contact> contacts= databaseHandler.getAllContacts();
        for(Contact con:contacts){
            // for testing
            String log = "Id: "+con.getmId()+" ,Name: " + con.getmName() + " ,Mobile: " + con.getmMobileNo();
            Log.d("Name : ",log);

        }

    }
}
