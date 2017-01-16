package com.example.administrator.materialdesign;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toobar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toobar);

//        drawerLayout= (DrawerLayout) findViewById(R.id.drawer_layout);
//
//        ActionBar actionBar=getSupportActionBar();
//        if(actionBar!=null)
//        {
//            actionBar.setDisplayHomeAsUpEnabled(true);
//            actionBar.setHomeAsUpIndicator(R.drawable.float_fab_close);
//        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.toolbar,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.backup:
                Toast.makeText(this, "item:" + "Backup", Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(this, "item:" + "Delete", Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings:
                Toast.makeText(this, "item:" + "Settings", Toast.LENGTH_SHORT).show();
                break;
//            case android.R.id.home:
//                drawerLayout.openDrawer(GravityCompat.START);
//
//                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
