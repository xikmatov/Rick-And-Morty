package com.example.android.pdp.shoha.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import com.example.android.pdp.shoha.db.entity.CharacterE

@Dao
interface CharacterDao {

    @Insert(onConflict = REPLACE)
    fun addCharacter(characterList: List<CharacterE>)

    @Query("select * from CharacterE")
    fun getAllCharacters(): List<CharacterE>

}