package com.example.android.pdp.shoha.db.entity

import androidx.room.Entity
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

@Entity
class CharacterE {
    @PrimaryKey
    var id: Int? = null
    var name: String? = null
    var status: String? = null
    var species: String? = null
    var gender: String? = null
    @ColumnInfo(name = "origin_name")
    var originName: String? = null
    @ColumnInfo(name = "location_name")
    var locationName: String? = null
    var image: String? = null
    @ColumnInfo(name = "episode_size")
    var episodeSize: Int? = null

    constructor()

    constructor(
        id: Int?,
        name: String?,
        status: String?,
        species: String?,
        gender: String?,
        originName: String?,
        locationName: String?,
        image: String?,
        episodeSize: Int?
    ) {
        this.id = id
        this.name = name
        this.status = status
        this.species = species
        this.gender = gender
        this.originName = originName
        this.locationName = locationName
        this.image = image
        this.episodeSize = episodeSize
    }

}