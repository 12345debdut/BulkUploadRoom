package com.debdut.bulkuploadroom.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.debdut.bulkuploadroom.model.AttributeTable
import com.debdut.bulkuploadroom.model.Data
import com.debdut.bulkuploadroom.model.RoomModel
import com.debdut.bulkuploadroom.model.StatusUpload
import com.debdut.bulkuploadroom.repository.BulkDataRepository

class BulkViewModel:ViewModel(){
    lateinit var context:Context
    lateinit var repository:BulkDataRepository
    private lateinit var status:MutableLiveData<StatusUpload>
    private lateinit var data: MutableLiveData<List<Data>>
    fun initViewModel(context: Context){
        this.context=context
        repository= BulkDataRepository(context)
    }
    fun getStatus():LiveData<StatusUpload>{
        status= MutableLiveData()
        return status
    }

    fun getListData():LiveData<List<Data>>{
        data=MutableLiveData()
        data.value=repository.getData()
        return data
    }

    fun insertAllData(data:List<Data>){
        status.value= StatusUpload(uploading = true,deleting = false,timing = "")
        repository.insertAllData(status,data)
    }

    fun deleteAllData(roomData:List<RoomModel>){
        status.value= StatusUpload(uploading = false,deleting = true,timing = "")
        repository.deleteAllData(status,roomData)
    }

    fun getAllData():LiveData<List<RoomModel>>?{
        return repository.getAllDataCount()
    }

    fun getAllDataAttribute():LiveData<List<AttributeTable>>?{
        return repository.getAllAttribute()
    }

}