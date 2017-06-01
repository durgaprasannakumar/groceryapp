package com.example.surendra.groceryapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;
import java.util.Map;

import customadaptors.ANavigationCustom;
import customadaptors.UserAdaptor;
import webserviceAccess.AppUserService;

public class Grocers extends AppCompatActivity {
    private ListView mDrawerList;
    ANavigationCustom n = new ANavigationCustom();
    ListView lv;
    AppUserService aus = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocers);
        mDrawerList = (ListView)findViewById(R.id.navList);

        n.addDrawerItems(mDrawerList,this);
        navigateActivity(this);
        aus=new AppUserService();
        lv = (ListView) findViewById(R.id.listView);
        List<Map<String,String>> list = aus.getGrocerList();
        lv.setAdapter(new UserAdaptor(Grocers.this,list));
    }

    public void navigateActivity(final Context context){
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context,n.renderPage(position));
                startActivity(intent);
            }
        });
    }
}
