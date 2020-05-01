package com.getusersapp.ui.learningslist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.getusersapp.R
import com.getusersapp.data.models.listeners.LearningItemClickListener
import com.getusersapp.data.models.Learnings

class LearningsListAdapter(
    private val learningsList: List<Learnings>,
    private val learningItemClickListener: LearningItemClickListener
) : RecyclerView.Adapter<LearningsListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LearningsListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return LearningsListViewHolder(
            DataBindingUtil.inflate(
                inflater
                , R.layout.layout_learning_item, parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return learningsList.size
    }

    override fun onBindViewHolder(holder: LearningsListViewHolder, position: Int) {
        holder.layoutLearningItemBinding.learning = learningsList[position]
        holder.itemView.setOnClickListener {
            learningItemClickListener.onItemClick(position)
        }
    }
}