package com.debdut.bulkuploadroom.model

import androidx.room.*
import com.google.gson.annotations.SerializedName
import java.lang.reflect.Constructor

@Entity(tableName = "diageo_table_kpi")
data class RoomModel(
    @ColumnInfo(name="Achievement__c")
    val achievement:String?,

    @ColumnInfo(name="Actual_Numerator__c")
    val actual_numerator:String?,


    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name="Id")
    val id:String,

    @ColumnInfo(name="KPI_Type__c")
    val kpi_type:String?,


//    @SerializedName("LastModifiedDate")
//    val last_modified_date:Date,

    @ColumnInfo(name="Name")
    val name:String?,

    @ColumnInfo(name="Category__c")
    val catagory_c:String?,

    @ColumnInfo(name="Target__c")
    val target_c:String?,

    @ColumnInfo(name="Outlet__c")
    val outlet_c:String?,

    @ColumnInfo(name="Total_Denominator__c")
    val total_denominator:String?
)

@Entity(foreignKeys = arrayOf(ForeignKey(entity = RoomModel::class,
    parentColumns = arrayOf("Id"),
    childColumns = arrayOf("kpi_id"),
    onDelete = ForeignKey.CASCADE)),tableName = "diageo_table_kpi_attribute")
data class AttributeTable(
    @PrimaryKey(autoGenerate = true)
    val id:Long,

    @ColumnInfo(name = "kpi_id")
    val kpi_id: String?,

    @ColumnInfo(name = "url")
    val url:String?,

    @ColumnInfo(name="type")
    val type:String?

){
    constructor(kpi_id: String?,url: String?,type: String?) : this(
        id=0,
        kpi_id = kpi_id,
        url = url,
        type = type
    )
}