package com.demo.android.cassiana.rickandmortycardapp.ui.fragment.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.demo.android.cassiana.rickandmortycardapp.R
import com.demo.android.cassiana.rickandmortycardapp.model.Character
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_list.view.*

class CharacterAdapter :
    PagingDataAdapter<Character, CharacterAdapter.CharacterViewHolder>(CharacterDiffUtil()) {

    class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image_character = itemView.character_img
        var status_type = itemView.txt_status
        var id_number = itemView.txt_id_character
        var name_character = itemView.txt_name_character

        fun bind(character: Character) {
            Picasso.get().load(character.image).into(image_character)
            status_type.text = character.status
            id_number.text = character.id.toString()
            name_character.text = character.name
        }
    }

    class CharacterDiffUtil : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }

        holder.itemView.setOnClickListener { view ->
            val action = getItem(position)?.let {
                ListFragmentDirections.actionListFragmentToDetailFragment(it)
            }
            action?.let { view.findNavController().navigate(it) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return CharacterViewHolder(view)
    }

}