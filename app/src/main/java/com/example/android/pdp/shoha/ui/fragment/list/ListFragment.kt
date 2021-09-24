package com.demo.android.cassiana.rickandmortycardapp.ui.fragment.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.fragment_list.*
import com.demo.android.cassiana.rickandmortycardapp.R
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.demo.android.cassiana.rickandmortycardapp.api.Repository
import com.example.android.pdp.shoha.viewmodels.CharacterViewModel
import com.example.android.pdp.shoha.viewmodels.ViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ListFragment : Fragment(R.layout.fragment_list) {

    private var adapter = CharacterAdapter()
    private val characterVm: CharacterViewModel by activityViewModels{ ViewModelFactory(requireContext(), Repository()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch{
            characterVm.characters.collectLatest {
                adapter.submitData(it)
            }
        }

        recycclerview.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recycclerview.adapter = adapter
    }

}