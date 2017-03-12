package com.hasta.meetingdialer;

import android.app.*;
import android.os.Bundle;
import android.content.*;
import android.net.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity {

    public static final String PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        for(int i=1;i<5;i++) {
            String numToCall = settings.getString("number"+i, "");
            getET(i).setText(numToCall);
        }
    }

    @Override
    protected void onStop()
    {
        super.onStop();

        // We need an Editor object to make preference changes.
        // All objects are from android.context.Context
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        for(int i=1;i<5;i++) {
            String numToCall = getET(i).getText().toString();
            editor.putString("number"+i, numToCall);
        }

        // Commit the edits!
        editor.commit();
    }

    public void call(View view)
    {
        int etId =-1;
        switch (view.getId()) {
            case R.id.call_one:
                etId=1;
                break;
            case R.id.call_two:
                etId=2;
                break;
            case R.id.call_three:
                etId=3;
                break;
            case R.id.call_four:
                etId=4;
                break;
        }
        final String numToCall = getET(etId).getText().toString();
        Toast.makeText(this, "Calling: " + numToCall, Toast.LENGTH_SHORT).show();
        final Uri uri = new Uri.Builder().scheme("tel").encodedPath(numToCall).build();
        Intent intent = new Intent(Intent.ACTION_CALL, uri);
        startActivity(intent);
    }

    private EditText getET (int id) {
        if(id==1)
            return (EditText) findViewById(R.id.num_one);
        if(id==2)
            return (EditText) findViewById(R.id.num_two);
        if(id==3)
            return (EditText) findViewById(R.id.num_three);
        if(id==4)
            return (EditText) findViewById(R.id.num_four);
        return null;
    }
}
