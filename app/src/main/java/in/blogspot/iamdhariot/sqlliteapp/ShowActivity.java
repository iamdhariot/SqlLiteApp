package in.blogspot.iamdhariot.sqlliteapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ShowActivity extends AppCompatActivity {


    public List<Contact> contactList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ContactAdapter mAdapter;
    private DatabaseHandler databaseHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        databaseHandler = new DatabaseHandler(this);


        recyclerView   = (RecyclerView)findViewById(R.id.contactRecycleView);
        mAdapter = new ContactAdapter(contactList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        prepareContactData();
    }

    private void prepareContactData(){

        Contact contact;

        Toast.makeText(this,"Contact inserted",Toast.LENGTH_SHORT).show();

        // reading the contacts
        List<Contact> contacts= databaseHandler.getAllContacts();
        for(Contact con:contacts){
            // for testing
            String log = "Id: "+con.getmId()+" ,Name: " + con.getmName() + " ,Mobile: " + con.getmMobileNo();
            Log.d("Name : ",log);
            contact = new Contact(con.getmName(),con.getmMobileNo());
            contactList.add(contact);



            mAdapter.notifyDataSetChanged();

        }



    }
}
