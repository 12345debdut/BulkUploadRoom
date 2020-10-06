package com.debdut.bulkuploadroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.debdut.bulkuploadroom.model.Data
import com.debdut.bulkuploadroom.model.RoomModel
import com.debdut.bulkuploadroom.viewmodel.BulkViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var data:List<Data>
    lateinit var model: BulkViewModel
    lateinit var roomData:List<RoomModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        model=ViewModelProvider(this).get(BulkViewModel::class.java)
        model.initViewModel(this)
        model.getListData().observe(this, Observer {
            data=it
        })

        model.getStatus().observe(this, Observer {
            if(it.uploading){
                uploading_status.text="Uploading....."
            }else{
                uploading_status.text="Uploaded...."
            }
            if(it.deleting){
                delete_status.text="Deleting..."
            }else{
                delete_status.text="Deleted..."
            }
            timing.text=it.timing

        })
        model.getAllData()?.observe(this,{
            roomData=it
            content_number.text=it?.size.toString()
        })
        model.getAllDataAttribute()?.observe(this,{
            attr_count.text=it?.size.toString()
        })
    }

    fun deleteData(view: View){
        model.deleteAllData(roomData)
    }

    fun uploadData(view: View){
        model.insertAllData(data)
    }

}
