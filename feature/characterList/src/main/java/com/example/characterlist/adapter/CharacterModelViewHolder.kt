package com.example.characterlist.adapter

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.characterlist.databinding.CharacterItemBinding
import com.example.characterlist.ui.GlideApp
import com.example.core_model.CharacterModel
import com.android.navigation.CharacterHandler

class CharacterModelViewHolder(private val view: CharacterItemBinding) :
    RecyclerView.ViewHolder(view.root) {

    fun onBindContent(content: CharacterModel, handler: CharacterHandler) {
        view.tvCharacterName.text = content.name
        view.ivCharacter.scaleType = ImageView.ScaleType.FIT_XY

        val params: ViewGroup.LayoutParams = view.ivCharacter.layoutParams
        params.width = LinearLayout.LayoutParams.MATCH_PARENT
        params.height = 300

        view.ivCharacter.layoutParams = params

        GlideApp.with(view.root.context)
            .load(content.imageUrl)
            .into(view.ivCharacter)

        view.root.setOnClickListener {
            handler.onCharacterClick(content)
        }
    }
}