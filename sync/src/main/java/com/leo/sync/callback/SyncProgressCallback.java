package com.leo.sync.callback;

import com.leo.sync.core.Sync;

/**
 * Interface to handle progress of @{@link Sync}
 * Use publish progress method from @{@link android.os.AsyncTask} class to get the progress
 *
 * @author Leonel Méndez
 */
public interface SyncProgressCallback {
    void onProgress(Integer... progress);
}
