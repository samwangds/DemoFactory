package demo.com.sam.demofactory.activity.livedata;

import android.arch.lifecycle.MutableLiveData;

public class LiveDataHolder extends MutableLiveData<String> {

    int value = 0;
    public void countAdd() {
        value++;
        setValue(String.valueOf(value));
    }

    private static LiveDataHolder liveData;
    public static LiveDataHolder getInstance() {
        if (liveData == null) {
            liveData = new LiveDataHolder();
        }
        return liveData;
    }
}
