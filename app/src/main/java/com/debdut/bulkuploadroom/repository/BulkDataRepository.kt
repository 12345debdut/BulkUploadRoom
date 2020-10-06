package com.debdut.bulkuploadroom.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.debdut.bulkuploadroom.DatabaseRoom.DataDatabase
import com.debdut.bulkuploadroom.model.AttributeTable
import com.debdut.bulkuploadroom.model.Data
import com.debdut.bulkuploadroom.model.RoomModel
import com.debdut.bulkuploadroom.model.StatusUpload
import com.debdut.bulkuploadroom.util.JsonToString
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.reflect.Type

class BulkDataRepository(private val context: Context){
    private val dataDataBase=DataDatabase.getInstance(context)
    private val dataDao=dataDataBase?.dataDao()

    fun getData():List<Data>{
        val gson= GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        val string:String?=JsonToString.getAssetFromJson(context, "dummydata.json")
        val listDataType: Type = object : TypeToken<List<Data>>() {}.type
        return gson.fromJson(string,listDataType)
    }
    fun insertAllData(statusUpload: MutableLiveData<StatusUpload>,data:List<Data>){
        CoroutineScope(IO).launch {
            val start = System.currentTimeMillis()
            val other:ArrayList<RoomModel> = arrayListOf<RoomModel>()
            for(d in data){
                other.add(
                    RoomModel(d.achievement,
                        d.actual_numerator,
                        d.id,d.kpi_type,
                        d.name,d.catagory_c,
                        d.target_c,
                        d.outlet_c,
                        d.total_denominator)
                )
            }
            dataDao?.insertListRoomData(other)
            val execTime=System.currentTimeMillis() - start
            withContext(Main) {
                val temp=StatusUpload(uploading = false,deleting = false,timing = execTime.toString()+"ms")
                statusUpload.value=temp
            }
        }
    }

    fun deleteAllData(statusUpload: MutableLiveData<StatusUpload>,data:List<RoomModel>?){
        CoroutineScope(IO).launch {
            val start = System.currentTimeMillis()
            dataDao?.deleteAllDataWithAttr()
            val execTime=System.currentTimeMillis() - start
            withContext(Main) {
                val temp=StatusUpload(uploading = false,deleting = false,timing = execTime.toString()+"ms")
                statusUpload.value=temp
            }
        }
    }

    fun getAllDataCount():LiveData<List<RoomModel>>?{
        return dataDao?.getAllData()
    }

    fun getAllAttribute():LiveData<List<AttributeTable>>?{
        return dataDao?.getAllDataAttr()
    }

}