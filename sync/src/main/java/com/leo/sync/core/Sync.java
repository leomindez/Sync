package com.leo.sync.core;

import android.app.Activity;

import com.leo.sync.callback.SyncCancelCallback;
import com.leo.sync.callback.SyncPreExecuteCallback;
import com.leo.sync.callback.SyncProgressCallback;
import com.leo.sync.callback.SyncResponseCallback;

import java.lang.ref.WeakReference;

/**
 * <p>Abstract class to handle async task callbacks</p>
 *
 * @author Leonel Méndez
 * @see SyncResponseCallback
 * @see SyncProgressCallback
 * @see SyncCancelCallback
 * @see SyncPreExecuteCallback
 */


public abstract class Sync<Params, Result> extends android.os.AsyncTask<Params, Integer, Result> {

    private String errorMessage = "There was an error getting the result";
    private WeakReference<SyncResponseCallback<Result>> syncResponseCallback;
    private WeakReference<SyncProgressCallback> syncProgressCallback;
    private WeakReference<SyncCancelCallback<Result>> syncCancelCallback;
    private WeakReference<SyncPreExecuteCallback> syncPreExecuteCallback;
    private WeakReference<Activity> activityWeakReference;

    /**
     * Empty constructor
     */
    public Sync() {
    }

    /**
     * Constructor to set and instance of activity
     *
     * @param activity
     */
    public Sync(Activity activity) {
        this.activityWeakReference = new WeakReference<>(activity);
    }

    /**
     * Get the activity set from constructor
     *
     * @return @{@link Activity}
     */
    public Activity getActivity() {
        return activityWeakReference.get();
    }

    /**
     * Set action to UI Thread before starting async task
     *
     * @param @{@link SyncPreExecuteCallback}
     */

    public void preExecute(SyncPreExecuteCallback syncPreExecuteCallback) {
        this.syncPreExecuteCallback = new WeakReference<>(syncPreExecuteCallback);
    }

    /**
     * Get the result of async task
     *
     * @param @{@link SyncResponseCallback}
     */
    public void response(SyncResponseCallback<Result> responseCallback) {
        this.syncResponseCallback = new WeakReference<>(responseCallback);
    }

    /**
     * Get progress of async task
     *
     * @param @{@link SyncProgressCallback}
     */
    public void progress(SyncProgressCallback progressCallback) {
        this.syncProgressCallback = new WeakReference<>(progressCallback);
    }

    /**
     * Handle cancel callback of async task
     *
     * @param cancelCallback
     */
    public void cancel(SyncCancelCallback<Result> cancelCallback) {
        this.syncCancelCallback = new WeakReference<>(cancelCallback);
    }

    @Override
    final protected void onPreExecute() {
        setPreExecute();
    }

    @Override
    final protected void onProgressUpdate(Integer... values) {
        setProgress(values);
    }

    @Override
    final protected void onCancelled(Result result) {
        setCancel(result);
    }

    /**
     * Method to set error message
     *
     * @param errorMessage
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    final protected void onPostExecute(Result result) {
        if (!isCancelled()) {
            setCallback(result);
        }
    }

    private void setCallback(Result result) {
        if (syncResponseCallback != null) {
            if (syncResponseCallback.get() != null) {
                if (result != null) {
                    syncResponseCallback.get().onSuccess(result);
                } else {
                    syncResponseCallback.get().onError(new Error(errorMessage));
                }
            }
        }
    }

    private void setProgress(Integer... values) {
        if (!isCancelled()) {
            if (values != null) {
                if (values.length > 0) {
                    if (syncProgressCallback != null) {
                        if (syncProgressCallback.get() != null) {
                            syncProgressCallback.get().onProgress(values);
                        }
                    }
                }
            }
        }
    }

    private void setCancel(Result result) {
        if (isCancelled()) {
            if (syncCancelCallback != null) {
                if (syncCancelCallback.get() != null) {
                    syncCancelCallback.get().onCancel(result);
                }
            }
        }
    }

    private void setPreExecute() {
        if (syncPreExecuteCallback != null) {
            if (syncPreExecuteCallback.get() != null) {
                syncPreExecuteCallback.get().onPreExecute();
            }
        }
    }
}
