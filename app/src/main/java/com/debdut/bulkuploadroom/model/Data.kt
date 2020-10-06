package com.debdut.bulkuploadroom.model

import com.google.gson.annotations.SerializedName


data class Attribute(
    @SerializedName("url")//It's optional to do so
    val url:String,

    @SerializedName("type")//It's optional to do so
    val type:String
)

data class Data(
    @SerializedName("attributes")
    val attribute: Attribute,

    @SerializedName("Achievement__c")
    val achievement:String,

    @SerializedName("Actual_Numerator__c")
    val actual_numerator:String,


    @SerializedName("Id")
    val id:String,

    @SerializedName("KPI_Type__c")
    val kpi_type:String,


//    @SerializedName("LastModifiedDate")
//    val last_modified_date:Date,
    @SerializedName("Name")
    val name:String,

    @SerializedName("Category__c")
    val catagory_c:String,

    @SerializedName("Target__c")
    val target_c:String,

    @SerializedName("Outlet__c")
    val outlet_c:String,

    @SerializedName("Total_Denominator__c")
    val total_denominator:String
)
