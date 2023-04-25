package com.example.retrofitdogs.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.retrofitdogs.R

class BreedSpinnerAdapter(context: Context, resource: Int, private val breeds: List<String>) :
    ArrayAdapter<String>(context, resource, breeds) {

    private val layoutInflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: layoutInflater.inflate(R.layout.spinner_item, parent, false)
        val breed = breeds[position]

        val textView = view.findViewById<TextView>(R.id.spinner_item_text_view)
        textView.text = breed

        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: layoutInflater.inflate(R.layout.spinner_dropdown_item, parent, false)
        val breed = breeds[position]

        val textView = view.findViewById<TextView>(R.id.spinner_dropdown_item_text_view)
        textView.text = breed

        return view
    }
}
