package com.example.retrofitdogs.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofitdogs.models.Dog
import com.example.retrofitdogs.R

class DogsAdapter(private val context: Context, private var dogsList: List<Dog>) :
    RecyclerView.Adapter<DogsAdapter.DogViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.item_dog, parent, false)
        return DogViewHolder(view)
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        val dog = dogsList[position]

        Glide.with(context)
            .load(dog.imageUrl)
            .placeholder(R.drawable.ic_dog_placeholder)
            .into(holder.ivDogImage)

        holder.tvDogBreed.text = dog.breed
    }

    override fun getItemCount(): Int {
        return dogsList.size
    }

    inner class DogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivDogImage: ImageView = itemView.findViewById(R.id.dog_image_view)
        val tvDogBreed = itemView.findViewById<TextView>(R.id.dog_name_text_view)
    }

    fun updateDogsList(dogs: List<Dog>) {
        dogsList = dogs
        notifyDataSetChanged()
    }
}
