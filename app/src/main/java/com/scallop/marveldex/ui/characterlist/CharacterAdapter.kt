package com.scallop.marveldex.ui.characterlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.scallop.marveldex.R
import com.scallop.marveldex.databinding.ItemCharacterBinding
import com.scallop.marveldex.entities.MarvelCharacter
import com.scallop.marveldex.ui.commons.OnItemClick

class CharacterAdapter(private val mListener: OnItemClick<MarvelCharacter>) :
    ListAdapter<MarvelCharacter, CharacterAdapter.CharacterListViewHolder>(MigraineDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterListViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
        return CharacterListViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CharacterListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private lateinit var mItem: MarvelCharacter
        private val mBinding = ItemCharacterBinding.bind(itemView)

        init {
            itemView.setOnClickListener { mListener.onItemClicked(mItem) }
        }

        fun bind(item: MarvelCharacter) {
            mItem = item

            with(mBinding) {
                characterName.text = item.name
                characterImage.load("${item.thumbnail.path}/portrait_incredible.${item.thumbnail.extension}")
            }
        }
    }

    class MigraineDiffCallback : DiffUtil.ItemCallback<MarvelCharacter>() {

        override fun areItemsTheSame(oldItem: MarvelCharacter, newItem: MarvelCharacter) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: MarvelCharacter, newItem: MarvelCharacter) =
            oldItem == newItem
    }
}
