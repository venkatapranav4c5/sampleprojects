package com.getusersapp.ui.getusers.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.getusersapp.R
import com.getusersapp.data.models.LearningItemClickListener
import com.getusersapp.data.models.Learnings

class GetLearningsListAdapter(
    private val learningsList: List<Learnings>,
    private val learningItemClickListener: LearningItemClickListener
) : RecyclerView.Adapter<GetUsersListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GetUsersListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return GetUsersListViewHolder(
            DataBindingUtil.inflate(
                inflater
                , R.layout.layout_learning_item, parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return learningsList.size
    }

    override fun onBindViewHolder(holder: GetUsersListViewHolder, position: Int) {
        holder.layoutLearningItemBinding.learning = learningsList[position]
        holder.itemView.setOnClickListener {
            learningItemClickListener.onItemClick(position)
        }
    }
}