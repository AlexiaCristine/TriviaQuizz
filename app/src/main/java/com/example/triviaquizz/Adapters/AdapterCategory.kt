package com.example.triviaquizz.Adapters

import android.app.Activity
import android.app.AlertDialog
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.triviaquizz.ControllGame
import com.example.triviaquizz.DAO.CategoryDao
import com.example.triviaquizz.InitController
import com.example.triviaquizz.Models.Category.Category
import com.example.triviaquizz.R
import kotlinx.android.synthetic.main.rv_category.view.*

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class AdapterCategory(view : View) : RecyclerView.Adapter<AdapterCategory.CategoryHolder>() {
    private val categorydao = CategoryDao()
    private val gameAdapter = ControllGame()
    private var category = listOf<Category>()
    val build : AlertDialog.Builder = AlertDialog.Builder(view.context)
        .setView(R.layout.activity_loading)
        .setCancelable(false)
    val load : AlertDialog = build.create()

    init {

        load.show()
        categorydao.getCategory { categoriesAPI ->
            category = categoriesAPI.dataCat.categories
            notifyDataSetChanged()
            load.dismiss()
        }
    }


    override fun getItemViewType(position : Int) : Int {
        return R.layout.rv_category
    }

    override fun onCreateViewHolder(parent : ViewGroup , viewType : Int) =
        CategoryHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(viewType , parent , false)
        )

    override fun onBindViewHolder(holder : CategoryHolder , position : Int) {
        val categoryy = category[position]
        holder.fillView(categoryy)
    }

    override fun getItemCount() : Int {
        return category.size
    }

    inner class CategoryHolder(item : View) : RecyclerView.ViewHolder(item) {

        fun fillView(category : Category) {
            itemView.txtCategory.text = category.name
            itemView.setOnClickListener {
                load.show()
                InitController.category = category
                gameAdapter.start {game->
                    load.dismiss()
                    InitController.game = game.data!!.game

                    it.findNavController().navigate(R.id.action_category_to_game)
                }

            }
        }
    }
}