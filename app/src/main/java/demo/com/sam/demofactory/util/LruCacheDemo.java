package demo.com.sam.demofactory.util;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by samwang on 2018/4/7.
 */

public class LruCacheDemo {
    LruCacheDemo() {
        int cacheSize = (int) (Runtime.getRuntime().maxMemory() / 8);//bytes
        LruCache lruCache = new LruCache<String, Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes();
            }
        };

//        lruCache.put("key",bitmap);
        lruCache.get("key");

    }
}
