# Sync
Small Android library to handle Async Task methods. 
Use Interface Segregation Principle to divide the actions into indivual callbacks. 

## Usage 
Create a class and extends from Sync class. Sync is generic class, set <Params, Result> types and then implement doInBackground method.
```
private class SyncExample extends Sync<String, String> {
        @Override
        protected String doInBackground(String... params) {
            return "Example from" + params[0];
    }
 }

```
Create an instance 

```
SyncExample sync = new SyncExample();
```

then call execute method 
```
sync.executer("Sync");
```

Use interface callbacks to handle Sync behavior. 

### Response Callback
```
sync.response(new SyncResponseCallback<String>(){
  @Override
public void onSuccess(String s) {
Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
}
@Override
public void onError(Error error) {
Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
}
});
```
### PreExecute Callback

```
sync.preExecute(new SyncPreExecuteCallback() {
@Override
public void onPreExecute() {       
}
});    
                
```

### Progress Callback
```
sync.progress(new SyncProgressCallback() {
@Override
public void onProgress(Integer... progress) {
}
});
```

### Cancel Callback
```
sync.cancel(new SyncCancelCallback<String>() {
@Override
public void onCancel(String s) {
}
});
```
