package com.example.characterlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.characterlist.databinding.CharacterItemBinding
import com.example.core_model.CharacterModel
import com.android.navigation.CharacterHandler

class CharactersListAdapter(private val handler: CharacterHandler) :
    ListAdapter<CharacterModel, RecyclerView.ViewHolder>(
        diffCallback
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val binding =
            CharacterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterModelViewHolder(binding)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        getItem(position)?.let { (holder as CharacterModelViewHolder).onBindContent(it, handler) }

    }

    companion object {

        private val diffCallback = object : DiffUtil.ItemCallback<CharacterModel>() {
            override fun areItemsTheSame(
                oldItem: CharacterModel,
                newItem: CharacterModel
            ): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: CharacterModel,
                newItem: CharacterModel
            ): Boolean =
                oldItem == newItem
        }
    }
}