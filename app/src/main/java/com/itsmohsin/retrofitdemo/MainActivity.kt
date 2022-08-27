package com.itsmohsin.retrofitdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import com.itsmohsin.retrofitdemo.databinding.ActivityMainBinding
import okhttp3.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var retService: AlbumService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(AlbumService::class.java)

        getRequestWithQueryParameters()
//        getRequestWithParameters()

    }

    private fun getRequestWithQueryParameters(){
        val responseLiveData = liveData {
            val response = retService.getSortedAlbums(3)
            emit(response)
        }
        responseLiveData.observe(this, Observer {
            val albumsList = it.body()?.listIterator()
            if(albumsList!=null){
                while(albumsList.hasNext()){
                    val albumsItem= albumsList.next()
                    val result : String =" "+"Album Title : ${albumsItem.title}"+"\n"+
                            " "+"Album Id : ${albumsItem.title}"+"\n"+
                            " "+"User Id : ${albumsItem.userId}"+"\n\n\n"
                    binding.textView.append(result)
                }
            }
        })
    }

    private fun getRequestWithParameters(){
        //path parameter example
        val pathResponse = liveData{
            val response = retService.getAlbum(3)
            emit(response)
        }

        pathResponse.observe(this, Observer {
            val title = it.body()?.title
            Toast.makeText(applicationContext,title,Toast.LENGTH_LONG).show()
        })
    }
}