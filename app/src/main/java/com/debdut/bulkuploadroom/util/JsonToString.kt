package com.debdut.bulkuploadroom.util

import android.content.Context
import java.io.IOException
import java.io.InputStream

class JsonToString {
    companion object{
        fun getAssetFromJson(context:Context,fileName:String):String?{
            var jsonString: String?
            try {
                val inputStream: InputStream = context.assets.open(fileName)
                val size: Int = inputStream.available()
                val buffer = ByteArray(size)
                inputStream.read(buffer)
                inputStream.close()
                jsonString=buffer.decodeToString()
            } catch (e: IOException) {
                e.printStackTrace()
                jsonString=null
            }

            return jsonString
        }
    }
}