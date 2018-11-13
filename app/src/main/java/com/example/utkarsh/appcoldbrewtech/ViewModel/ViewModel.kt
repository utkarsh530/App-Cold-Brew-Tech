package com.example.utkarsh.appcoldbrewtech.ViewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.graphics.BitmapFactory
import com.example.utkarsh.appcoldbrewtech.Model.Image
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.net.URL

class ViewModel : ViewModel() {

    fun NetworkCall(): LiveData<Array<Image?>> {

        val data: MutableLiveData<Array<Image?>> = MutableLiveData()

        val client = OkHttpClient()

        val url = "http://18.222.17.234:8000/getImages"

        val requestBody = RequestBody.create(null, "")

        val request = Request.Builder()
            .url(url)
            .post(requestBody)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {

            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val mResponse = response.body()!!.string()
                    val jsonObject = JSONObject(mResponse)
                    val jsonArray = jsonObject.getJSONArray("data")
                    val iObject: Array<JSONObject?> = arrayOfNulls(4)
                    val images: Array<Image?> = arrayOfNulls(4)
                    val imageurl: Array<String?> = arrayOfNulls(4)
                    for (i in 0..3) {
                        iObject[i] = jsonArray.getJSONObject(i)
                        imageurl[i] = iObject[i]?.getString("image_url")
                        images[i] = Image(
                            BitmapFactory.decodeStream(URL(imageurl[i]).openConnection().getInputStream()),
                            iObject[i]!!.getString("text")
                        )
                    }
                    data.postValue(images)
                }
            }
        })
        return data
    }
}
