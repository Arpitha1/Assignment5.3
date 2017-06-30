package com.example.abhi.conmenu;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends Activity implements OnItemClickListener {

    ListView lv;
    SecondActivity lvadap;
    private final static String name[]={"Pushpa","Latha","Arjun","Kiran","Arnav"};
    private final static String number[]={"94489438285","94489438280","94489438283","94489438289","9001234567"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView)findViewById(R.id.lv);
        lvadap = new SecondActivity(this,name,number);
        lv.setOnItemClickListener(this);
        System.out.println("Adapter = "+lvadap.getCount());
        lv.setAdapter(lvadap);
        registerForContextMenu(lv);
    }


    public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
        Toast.makeText(this,"Title => "+name[position]+"=> n Description"+number[position], Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("Select Action");
        getMenuInflater().inflate(R.menu.context_menu,menu);
        super.onCreateContextMenu(menu, v, menuInfo);


    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {


        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        String num;

        try {
            num = number[info.position];


            if (item.getTitle() == "Call") {


                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + num));
                if (true)
                startActivity(callIntent);


            } else if (item.getTitle() == "Send SMS") {
                Intent smsIntent = new Intent(Intent.ACTION_VIEW);
                smsIntent.setType("vnd.android-dir/mms-sms");
                smsIntent.putExtra("address", num);
                startActivity(smsIntent);


            } else {
                return false;
            }
            return true;
        } catch (Exception e) {
            return true;
        }




        }
    }

