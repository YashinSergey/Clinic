package com.yashinsergey.clinic.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.yashinsergey.clinic.databinding.FragmentDoctorsListBinding
import com.yashinsergey.clinic.model.repos.network.json.Doctor
import com.yashinsergey.clinic.ui.common.LazyContainer
import com.yashinsergey.clinic.ui.views.DoctorViewItem

class DoctorListFragment(private val doctorList: List<Doctor>): Fragment() {

    private val lazyContainer = LazyContainer<FragmentDoctorsListBinding>()

    private val adapter : GroupAdapter<GroupieViewHolder> by lazy {
        val groupAdapter = GroupAdapter<GroupieViewHolder>()
        groupAdapter.setHasStableIds(true)
        groupAdapter
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        lazyContainer.getValue(
            { FragmentDoctorsListBinding.inflate(inflater, container, false) },
            { initFragmentViews(it) }
        ).root


    private fun initFragmentViews(binding: FragmentDoctorsListBinding) {
        binding.doctorsRecycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.doctorsRecycler.adapter = adapter
        fillAdapter()
    }

    private fun fillAdapter() {
        if(adapter.groupCount > 0) adapter.clear()
        adapter.addAll(createGroups())
        adapter.notifyDataSetChanged()
    }

    private fun createGroups(): List<DoctorViewItem> {
        val groups = mutableListOf<DoctorViewItem>()
        doctorList.forEachIndexed { i, data -> groups.add(DoctorViewItem(i.toLong(),data)) }
        return groups
    }
}