package com.debdut.bulkuploadroom.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.debdut.bulkuploadroom.model.Attribute
import com.debdut.bulkuploadroom.model.AttributeTable
import com.debdut.bulkuploadroom.model.Data
import com.debdut.bulkuploadroom.model.RoomModel
import org.w3c.dom.Attr

@Dao
abstract class DataDao {
    @Insert
    abstract suspend fun insertData(roomModel: RoomModel)

    @Insert
    abstract suspend fun insertAttributes(attributeTable: AttributeTable)

    @Insert
    abstract suspend fun insertListRoomData(data:List<RoomModel>)

    @Transaction
    open suspend fun insertAllData(data:List<Data>){
        for(d in data){
            insertData(
                RoomModel(d.achievement,
                    d.actual_numerator,
                    d.id,d.kpi_type,
                    d.name,d.catagory_c,
                    d.target_c,
                    d.outlet_c,
                    d.total_denominator))
            insertAttributes(AttributeTable(d.id,d.attribute.url,d.attribute.type))
        }
    }

    @Delete
    abstract suspend fun deleteDataOne(data:RoomModel)

    @Query("DELETE FROM diageo_table_kpi")
    abstract suspend fun deleteAllData()


    @Transaction
    open suspend fun deleteAllDataWithAttr(){
        deleteAllData()
    }

    @Transaction
    open suspend fun deleteAllAlternative(data: List<RoomModel>?){
        if (data != null) {
            for(d in data){
                deleteDataOne(d)
            }
        }
    }

    @Query("SELECT * FROM diageo_table_kpi")
    abstract fun getAllData():LiveData<List<RoomModel>>

    @Query("SELECT * FROM diageo_table_kpi_attribute")
    abstract fun getAllDataAttr():LiveData<List<AttributeTable>>

}