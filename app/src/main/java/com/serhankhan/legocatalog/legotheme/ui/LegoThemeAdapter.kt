package com.serhankhan.legocatalog.legotheme.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.serhankhan.legocatalog.databinding.ListItemThemeBinding
import com.serhankhan.legocatalog.legotheme.data.LegoTheme

/**
 * Adapter for the [RecyclerView] in [LegoThemeFragment].
 */
class LegoThemeAdapter:ListAdapter<LegoTheme,LegoThemeAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListItemThemeBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val legoTheme = getItem(position)
        holder.apply {
            bind(createOnClickListener(legoTheme.id, legoTheme.name), legoTheme)
            itemView.tag = legoTheme
        }
    }

    private fun createOnClickListener(id: Int, name: String): View.OnClickListener {
        return View.OnClickListener {
        }
    }

    class ViewHolder(private val binding: ListItemThemeBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: View.OnClickListener, item: LegoTheme) {
            binding.apply {
                clickListener = listener
                legoTheme = item
                executePendingBindings()
            }
        }
    }
}


private class DiffCallback : DiffUtil.ItemCallback<LegoTheme>() {
    override fun areItemsTheSame(oldItem: LegoTheme, newItem: LegoTheme): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: LegoTheme, newItem: LegoTheme): Boolean {
        return oldItem == newItem
    }
}
