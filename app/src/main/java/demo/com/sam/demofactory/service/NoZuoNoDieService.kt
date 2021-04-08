package demo.com.sam.demofactory.service

import android.app.Service
import android.content.Intent
import android.os.IBinder

class NoZuoNoDieService: Service() {
    override fun onBind(intent: Intent?): IBinder? {
         return null
    }

    override fun onCreate() {
        super.onCreate()
        // 小睡会模拟耗时
//        Thread.sleep(25_000)
    }
}