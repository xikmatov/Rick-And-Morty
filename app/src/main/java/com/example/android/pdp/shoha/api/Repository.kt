package com.demo.android.cassiana.rickandmortycardapp.api

import android.content.Context
import kotlinx.coroutines.launch
import kotlinx.coroutines.GlobalScope
import com.example.android.pdp.shoha.db.AppDb
import com.example.android.pdp.shoha.utils.NetworkHelper
import com.example.android.pdp.shoha.db.entity.CharacterE
import com.demo.android.cassiana.rickandmortycardapp.model.Character
import com.demo.android.cassiana.rickandmortycardapp.model.LocationData
import com.demo.android.cassiana.rickandmortycardapp.model.CharacterList
import com.squareup.picasso.Picasso

class Repository {

    suspend fun getCharacters(context: Context, page: Int): CharacterList {
        if (NetworkHelper(context).isNetworkConnected()) {
            // network connected

            val characters = RetrofitInstance.api.getCharacters(page)

            // Save to db
            GlobalScope.launch {
                val characterList = ArrayList<CharacterE>()

                for (character in characters.results) {
                    val characterE = CharacterE(
                        character.id,
                        character.name,
                        character.status,
                        character.species,
                        character.gender,
                        character.origin.name,
                        character.location.name,
                        character.image,
                        character.episode.size
                    )

                    characterList.add(characterE)
                }

                AppDb.getInstance(context).characterDao().addCharacter(characterList)
            }

            return characters
        } else {
            // network disconnected

            val results = ArrayList<Character>()
            val allCharacters = AppDb.getInstance(context).characterDao().getAllCharacters()

            for (character in allCharacters) {
                val character1 = Character(
                    character.id ?: 0,
                    character.name ?: "Unknown",
                    character.status ?: "Unknown",
                    character.species ?: "Unknown",
                    character.gender ?: "Unknown",
                    LocationData(character.locationName ?: "Unknown", "no"),
                    LocationData(character.originName ?: "Unknown", "no"),
                    character.image ?: "no",
                    arrayOfNulls<String>(character.episodeSize ?: 0).toList()
                )
                results.add(character1)
            }

            return CharacterList(results)
        }
    }

    suspend fun getCharactersByName(name: String): CharacterList {
        return RetrofitInstance.api.getCharactersByName(name)
    }

    suspend fun getCharactersbyStatusAndGender(
        status: String,
        gender: String,
        page: Int
    ): CharacterList {
        return RetrofitInstance.api.getCharactersbyStatusAndGender(status, gender, page)
    }

    suspend fun getCharactersByStatus(status: String, page: Int): CharacterList {
        return RetrofitInstance.api.getCharactersByStatus(status, page)
    }

    suspend fun getCharactersByGender(gender: String, page: Int): CharacterList {
        return RetrofitInstance.api.getCharactersByGender(gender, page)
    }
}