package com.yashinsergey.clinic.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.yashinsergey.clinic.databinding.ItemDoctorsListBinding
import com.yashinsergey.clinic.model.repos.network.json.Doctor
import com.yashinsergey.clinic.ui.ButtonId
import com.yashinsergey.clinic.ui.views.DoctorViewItem
import io.reactivex.Observer
import io.reactivex.subjects.PublishSubject

class DoctorsListsAdapters(val context: Context) {

    val therapyAdapter : GroupAdapter<GroupieViewHolder> by lazy {
        val groupAdapter = GroupAdapter<GroupieViewHolder>()
        groupAdapter.setHasStableIds(true)
        groupAdapter
    }

    val surgeryAdapter : GroupAdapter<GroupieViewHolder> by lazy {
        val groupAdapter = GroupAdapter<GroupieViewHolder>()
        groupAdapter.setHasStableIds(true)
        groupAdapter
    }

    val intensiveCareAdapter : GroupAdapter<GroupieViewHolder> by lazy {
        val groupAdapter = GroupAdapter<GroupieViewHolder>()
        groupAdapter.setHasStableIds(true)
        groupAdapter
    }

    fun createGroups(list: List<Doctor>, click: Observer<ButtonId>): List<DoctorViewItem> {
        val groups = mutableListOf<DoctorViewItem>()
        list.forEachIndexed { i, data ->
            groups.add(DoctorViewItem(i.toLong(), data, click))
        }
        return groups
    }

    fun initRecyclerView(layoutInflater: LayoutInflater, container: ViewGroup?,
                                                         adapter: GroupAdapter<GroupieViewHolder>,
                         doctorsList: List<Doctor>, click: Observer<ButtonId>): View {
        val itemBinding = ItemDoctorsListBinding.inflate(layoutInflater, container, false)
        itemBinding.branch.text = doctorsList[0].branch.name
        itemBinding.doctorsRecycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        itemBinding.doctorsRecycler.adapter = adapter
        fillAdapter(adapter,  click, doctorsList)
        return itemBinding.root
    }

    private fun fillAdapter(adapter: GroupAdapter<GroupieViewHolder>, click: Observer<ButtonId>, list: List<Doctor>) {
        if(adapter.groupCount > 0) adapter.clear()
        adapter.addAll(createGroups(list, click))
        adapter.notifyDataSetChanged()
    }
}