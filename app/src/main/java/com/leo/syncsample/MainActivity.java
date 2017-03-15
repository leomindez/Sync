package com.leo.syncsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.leo.sync.callback.SyncPreExecuteCallback;
import com.leo.sync.callback.SyncResponseCallback;
import com.leo.sync.core.Sync;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.start_sync_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SyncExample sync = new SyncExample();

                sync.response(new SyncResponseCallback<String>() {
                    @Override
                    public void onSuccess(String s) {
                        Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(Error error) {
                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();

                    }
                });

                sync.execute("Start Sync librayr");
            }
        });

    }

    private class SyncExample extends Sync<String, String> {
        @Override
        protected String doInBackground(String... params) {
            return "Example from" + params[0];
        }
    }

}

