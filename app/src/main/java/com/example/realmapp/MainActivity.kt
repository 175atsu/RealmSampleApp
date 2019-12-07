package com.example.realmapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Display
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.realm.Realm
import io.realm.RealmResults
import io.realm.Sort
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private val mRealm: Realm by lazy {
        Realm.getDefaultInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val read = readList()

        btnAdd.setOnClickListener {
            addList(editText.text.toString())
            read
        }

        // タスクリストが空だったときにダミーデータを生成する
//        if (taskList.isEmpty()) {
//            createDummyData()
//        }

        val adapter = Adapter(this, read, true)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    //アダプターなどなどの設定
//    fun recyclerViewInitialSetting() {
//        val rv = recyclerView
//        val adapter = Adapter(searchGitHubRepositoryByCoroutines(), object : Adapter.ListListener {
//        })
//        //リストのtrueコンテンツの大きさがデータによって変わらないならを渡す。これをRecyclerViewにいつもすることで、パフォーマンスが良くなる。
//        rv.setHasFixedSize(true)
//        rv.layoutManager = LinearLayoutManager(activity)
//        rv.layoutManager = GridLayoutManager(context, 2)
//        rv.adapter = adapter
//
//    }

    fun readList(): RealmResults<Model> {
        return mRealm.where(Model::class.java).findAll()
    }

    fun addList(title:String){
        mRealm.executeTransaction {
            var model = mRealm.createObject(Model::class.java , UUID.randomUUID().toString())
            model.title = title
            mRealm.copyToRealm(model)
        }
    }

    fun delete(id:String){
        mRealm.executeTransaction {
            var model = mRealm.where(Model::class.java).equalTo("id",id).findAll()
            model.deleteFromRealm(0)
        }
    }
}
