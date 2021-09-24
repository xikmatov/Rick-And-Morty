package com.example.android.pdp.shoha.paging

import java.lang.Exception
import android.content.Context
import androidx.paging.PagingSource
import androidx.paging.PagingState

import com.example.android.pdp.shoha.utils.NetworkHelper
import com.demo.android.cassiana.rickandmortycardapp.api.Repository
import com.demo.android.cassiana.rickandmortycardapp.model.Character

class CharacterDataSource(private val context: Context, private val repository: Repository) :
    PagingSource<Int, Character>() {

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        try {
            val nextPageNum = params.key ?: 1
            val characters = repository.getCharacters(context, nextPageNum)

            if (NetworkHelper(context).isNetworkConnected()) {
                if (nextPageNum >= 34) {
                    return LoadResult.Page(characters.results, nextPageNum - 1, null)
                }

                return if (nextPageNum > 1) {
                    LoadResult.Page(characters.results, nextPageNum - 1, nextPageNum + 1)
                } else {
                    LoadResult.Page(characters.results, null, nextPageNum + 1)
                }
            } else {
                return LoadResult.Page(characters.results, null, null)
            }

        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

}