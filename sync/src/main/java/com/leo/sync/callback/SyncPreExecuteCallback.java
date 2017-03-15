package com.leo.sync.callback;

/**
 * Interface to set actions into UI to pre execute @{@link android.os.AsyncTask}
 */

public interface SyncPreExecuteCallback {
    void onPreExecute();
}
