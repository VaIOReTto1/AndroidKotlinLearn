package com.example.networktest

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.active_main.responseText
import kotlinx.android.synthetic.main.active_main.sendRequestBtn
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import org.xml.sax.InputSource
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.StringReader
import java.lang.Exception
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL
import javax.xml.parsers.SAXParserFactory
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.active_main)
        sendRequestBtn.setOnClickListener {
//            sendRequestWithHttpURLConnection()
            sendRequestWithOkHttp()
        }
    }

    private fun sendRequestWithHttpURLConnection() {
        thread {
            var connection: HttpURLConnection? = null
            try {
                val response = StringBuilder()
                val url = URL("https://www.baidu.com")
                connection = url.openConnection() as HttpURLConnection
                connection.connectTimeout = 8000
                connection.readTimeout = 8000
                val input = connection.inputStream
                val reader = BufferedReader(InputStreamReader(input))
                reader.use {
                    reader.forEachLine {
                        response.append(it)
                    }
                }
                showResponse(response.toString())
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                connection?.disconnect()
            }
        }
    }

    private fun sendRequestWithOkHttp() {
        thread {
            try {
                val client = OkHttpClient()
                val request = Request.Builder()
                    //.url("http://10.0.2.2/get_data.xml")
                    .url("http://10.0.2.2/get_data.json")
                    .build()
                val response = client.newCall(request).execute()
                val responseData = response.body?.string()
                val responseData1="[{\"id\":\"5\",\"version\":\"5.5\",\"name\":\"Clash of Clans\"},\n" +
                        "{\"id\":\"6\",\"version\":\"7.0\",\"name\":\"Boom Beach\"},\n" +
                        "{\"id\":\"7\",\"version\":\"3.5\",\"name\":\"Clash Royale\"}]"
                Log.d("MainActivity", "JSON Response: $responseData1")
                if (responseData != null)
                    parseJSONWithJSONObject(responseData1)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun parseXMLWithPull(xmlData: String) {
        try {
            val factory = XmlPullParserFactory.newInstance()
            val xmlPullParser = factory.newPullParser()
            xmlPullParser.setInput(StringReader(xmlData))
            var eventType = xmlPullParser.eventType
            var id = ""
            var name = ""
            var version = ""
            while (eventType != XmlPullParser.END_DOCUMENT) {
                Log.d("MainActivity","hh")
                val nodeName = xmlPullParser.name
                when (eventType) {
                    XmlPullParser.START_TAG -> {
                        when (nodeName) {
                            "id" -> id = xmlPullParser.nextText()
                            "name" -> name = xmlPullParser.nextText()
                            "version" -> version = xmlPullParser.nextText()
                        }
                    }
                    XmlPullParser.END_TAG -> {
                        if ("app" == nodeName) {
                            Log.d("MainActivity", "id is $id")
                            Log.d("MainActivity", "name is $name")
                            Log.d("MainActivity", "version is $version")
                        }
                        Log.d("MainActivity", "id is $id")
                        Log.d("MainActivity", "name is $name")
                        Log.d("MainActivity", "version is $version")
                    }
                }
                eventType = xmlPullParser.next()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun parseXMLWithSAX(xmlData: String){
        try {
            val factory = SAXParserFactory.newInstance()
            val xmlReader=factory.newSAXParser().xmlReader
            val handler=ContentHandler()
            xmlReader.contentHandler=handler
            xmlReader.parse(InputSource(StringReader(xmlData)))
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    private fun parseJSONWithJSONObject(jsonData:String){
        try {
            val jsonArray= JSONArray(jsonData)
            for (i in 0 until jsonArray.length()){
                val jsonObject=jsonArray.getJSONObject(i)
                val id=jsonObject.getString("id")
                val name=jsonObject.getString("name")
                val version=jsonObject.getString("version")
                Log.d("MainActivity", "id is $id")
                Log.d("MainActivity", "name is $name")
                Log.d("MainActivity", "version is $version")
            }
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    private fun parseJSONWithGSON(jsonData:String){
        val gson=Gson()
        val typeOf=object :TypeToken<List<App>>(){}.type
        val addList=gson.fromJson<List<App>>(jsonData,typeOf)
        for (app in addList){
            Log.d("MainActivity", "id is ${app.id}")
            Log.d("MainActivity", "name is ${app.name}")
            Log.d("MainActivity", "version is ${app.version}")
        }
    }

    private fun showResponse(response: String) {
        runOnUiThread {
            responseText.text = response
        }
    }
}

class App(val id:String,val name:String,val version:String)