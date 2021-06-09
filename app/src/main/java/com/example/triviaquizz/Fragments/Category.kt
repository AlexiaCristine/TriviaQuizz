package com.example.triviaquizz.Fragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.triviaquizz.Adapters.AdapterCategory
import com.example.triviaquizz.R
import kotlinx.android.synthetic.main.fragment_category.view.*

class Category : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, // relembrar inflater
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_category, container, false)
        val build: AlertDialog.Builder = AlertDialog.Builder(activity)
        build.setView(R.layout.activity_loading)
        build.setCancelable(false)
        val load: AlertDialog = build.create()
        load.show()

        view.listCategory.adapter = AdapterCategory(view)
        view.listCategory.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL, // colocar as categorias em vertical e n horizontal
            false
        )
        load.dismiss()
        return view
    }


}