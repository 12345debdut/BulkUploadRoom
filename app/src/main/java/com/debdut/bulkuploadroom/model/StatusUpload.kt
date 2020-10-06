package com.debdut.bulkuploadroom.model

data class StatusUpload(
    val uploading:Boolean=false,
    val deleting:Boolean=false,
    val timing:String=""
)