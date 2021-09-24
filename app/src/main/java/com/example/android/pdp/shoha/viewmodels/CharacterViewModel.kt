package com.example.android.pdp.shoha.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.demo.android.cassiana.rickandmortycardapp.api.Repository
import com.example.android.pdp.shoha.paging.CharacterDataSource

class CharacterViewModel(private val context: Context, private val repository: Repository) :
    ViewModel() {

    val characters = Pager(PagingConfig(1)) {
        CharacterDataSource(context, repository)
    }.flow.cachedIn(viewModelScope)

}