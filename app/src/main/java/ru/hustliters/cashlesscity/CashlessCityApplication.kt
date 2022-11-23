package ru.hustliters.cashlesscity

import android.app.Application
import com.yandex.mapkit.MapKitFactory

class CashlessCityApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        MapKitFactory.setApiKey("d10fa8fb-8987-4e23-a8a7-6dd8194bc88e");
    }
}