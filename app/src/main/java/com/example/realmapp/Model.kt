package com.example.realmapp

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class Model : RealmObject() {
    @PrimaryKey
    var id: String = UUID.randomUUID().toString()
    var title: String = ""
}