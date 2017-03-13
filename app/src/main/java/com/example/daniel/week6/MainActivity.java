package com.example.daniel.week6;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String TAG = "ElTagListView";
    private DataEntryDAO mDataEntryDao;
    private ArrayList<DataEntry> Entries;
    private CustomAdapter customAdapter;
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        list=(ListView) findViewById(R.id.ListView);

        mDataEntryDao = new DataEntryDAO(this);

        Entries = mDataEntryDao.getAllEntrys();

        customAdapter = new CustomAdapter(this,Entries);

        list.setAdapter(customAdapter);



        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d(TAG,"Click en "+i);
            }


        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                 //       .setAction("Action", null).show();
                Intent i = new Intent(MainActivity.this,ViewActivity.class);
                startActivityForResult(i,1);


            }
        });
    }

    @Override
    protected void onDestroy(){
        mDataEntryDao.close();
        super.onDestroy();
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode==1){
            if(resultCode== Activity.RESULT_OK){
                DataEntry de= (DataEntry) data.getSerializableExtra("entry");
                mDataEntryDao.addDataEntry(de);
            }
        }
    }

    public void OnClickButtonRow(View view){
        DataEntry dataEntry= (DataEntry) view.getTag();
        Log.d(TAG,"Click en Botton "+dataEntry.id);
        //mDataEntryDao.deleteEntry(dataEntry);
       // Entries = mDataEntryDao.getAllEntrys();
       // customAdapter.setData(Entries);
       // ((CustomAdapter)list.getAdapter()).notifyDataSetChanged();

    }
}
