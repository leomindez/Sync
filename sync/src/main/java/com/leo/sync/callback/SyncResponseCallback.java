package com.leo.sync.callback;

import com.leo.sync.core.Sync;

/**
 * Interface to handle response callback of @{@link Sync}
 *
 * @author Leonel Méndez
 */
public interface SyncResponseCallback<Result> {
    void onSuccess(Result result);

    void onError(Error error);
}
