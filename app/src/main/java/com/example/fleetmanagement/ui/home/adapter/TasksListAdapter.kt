package com.example.fleetmanagement.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fleetmanagement.base.OnRecycleItemClick
import com.example.fleetmanagement.data.model.Message
import com.example.fleetmanagement.data.model.Task
import com.example.fleetmanagement.databinding.CustomTasksBinding

class TasksListAdapter(
    private val items: ArrayList<Message>,
    val onClick: OnRecycleItemClick<Message>, ) :
    RecyclerView.Adapter<TasksListAdapter.ViewHolderTasksList>() {
    inner class ViewHolderTasksList(val binding: CustomTasksBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TasksListAdapter.ViewHolderTasksList {
        return ViewHolderTasksList(
            CustomTasksBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(
        holder: TasksListAdapter.ViewHolderTasksList,
        position: Int
    ) {
        val item = items[position]
        holder.binding.itemTask = item
        holder.itemView.setOnClickListener { onClick.onClick(item, it) }
    }

    override fun getItemCount() = items.size
}