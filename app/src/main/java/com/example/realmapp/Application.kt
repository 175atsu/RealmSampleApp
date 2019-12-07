package com.example.realmapp

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class Application: Application() {

    override fun onCreate() {
        super.onCreate()

        //Realmの初期化
        Realm.init(this)
        val config = RealmConfiguration.Builder()
                //もともとあったデータを全て削除してくれる
            .deleteRealmIfMigrationNeeded()
            .build()
        Realm.setDefaultConfiguration(config)
    }

}