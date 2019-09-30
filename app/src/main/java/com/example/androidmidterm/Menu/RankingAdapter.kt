package com.example.androidmidterm.Menu

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.androidmidterm.R
import com.example.androidmidterm.Services.UserViewModel
import kotlinx.android.synthetic.main.ranking_item.view.*

class RankingAdapter(activity: Activity, user: Array<UserViewModel>) : BaseAdapter() {
    private val activity = activity
    private val user = user

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val user = user[position]
        val inflater =
            activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.activity_ranking, null)

        view.tvName.text = user.Name
        //view.ivUser.setImageResource(user.Id) //TODO For P'Oat
        return view


    }

    override fun getItem(position: Int): Any {
        return user[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return user.count()
    }
}