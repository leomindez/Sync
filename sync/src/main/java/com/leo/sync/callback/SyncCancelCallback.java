package com.leo.sync.callback;

import com.leo.sync.core.Sync;

/**
 * Interface to handle cancel callback of @{@link Sync}
 *
 * @author Leonel Méndez
 */
public interface SyncCancelCallback<Result> {
    void onCancel(Result result);
}
