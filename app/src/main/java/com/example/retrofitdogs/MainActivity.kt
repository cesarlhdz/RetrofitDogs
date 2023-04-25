package com.example.retrofitdogs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitdogs.adapters.BreedSpinnerAdapter
import com.example.retrofitdogs.adapters.DogsAdapter
import com.example.retrofitdogs.models.Dog
import com.example.retrofitdogs.network.ApiClient
import com.example.retrofitdogs.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var rvDogs: RecyclerView
    private lateinit var breedSpinner: Spinner
    private lateinit var apiService: ApiService
    private lateinit var dogsAdapter: DogsAdapter
    private lateinit var breedSpinnerAdapter: BreedSpinnerAdapter
    private lateinit var breed: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvDogs = findViewById(R.id.rv_images)
        breedSpinner = findViewById(R.id.spinner_breeds)

        rvDogs.layoutManager = LinearLayoutManager(this)
        apiService = ApiClient.buildService(ApiService::class.java)

        breedSpinnerAdapter = BreedSpinnerAdapter(this, R.layout.spinner_item, emptyList())
        breedSpinner.adapter = breedSpinnerAdapter

        breedSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                breed = parent?.getItemAtPosition(position).toString()
                getDogsByBreed(breed)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing
            }
        }

        dogsAdapter = DogsAdapter(this, emptyList())
        rvDogs.adapter = dogsAdapter
    }

    private fun getDogsByBreed(breed: String) {
        apiService.getDogsByBreed(breed).enqueue(object : Callback<DogsResponse> {
            override fun onResponse(
                call: Call<DogsResponse>,
                response: Response<DogsResponse>
            ) {
                if (response.isSuccessful) {
                    val dogsResponse = response.body()
                    dogsResponse?.let {
                    }
                }
            }

            override fun onFailure(call: Call<DogsResponse>, t: Throwable) {
                Log.e(TAG, "Error fetching dogs", t)
                Toast.makeText(
                    this@MainActivity,
                    "Error fetching dogs",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}
