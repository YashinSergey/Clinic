package com.yashinsergey.clinic.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.yashinsergey.clinic.common.logD
import com.yashinsergey.clinic.databinding.FragmentDoctorsListBinding
import com.yashinsergey.clinic.databinding.RecyclerViewDoctorsBinding
import com.yashinsergey.clinic.model.data.BranchId
import com.yashinsergey.clinic.model.repos.network.json.Doctor
import com.yashinsergey.clinic.ui.common.LazyContainer
import com.yashinsergey.clinic.ui.views.DoctorViewItem
import io.reactivex.functions.Consumer
import io.reactivex.subjects.BehaviorSubject

class DoctorListFragment: Fragment() {

    private val lazyContainer = LazyContainer<FragmentDoctorsListBinding>()
    private lateinit var binding: FragmentDoctorsListBinding

    private lateinit var inflater: LayoutInflater
    private var container: ViewGroup? = null

    val doctorListSubject = BehaviorSubject.create<List<Doctor>>()

    private val therapyList = mutableListOf<Doctor>()
    private val surgeryList = mutableListOf<Doctor>()
    private val intensiveCareUnitList = mutableListOf<Doctor>()

    private val doctorListConsumer = Consumer<List<Doctor>> {
        binding.recyclerViewsLayout.removeAllViews()
        therapyList.clear()
        surgeryList.clear()
        intensiveCareUnitList.clear()
        it.forEach { doctor ->
            logD("${doctor.name}: branch id is ${doctor.branch.id}, branch name is ${doctor.branch.name}")
            when(doctor.branch.id)  {
                BranchId.THERAPY.id -> therapyList.add(doctor)
                BranchId.SURGERY.id -> surgeryList.add(doctor)
                BranchId.INTENSIVE_CARE_UNIT.id -> intensiveCareUnitList.add(doctor)
            }

            val therapyView = therapyList.createDoctorsList(inflater, container, therapyAdapter)
            val surgeryView = surgeryList.createDoctorsList(inflater, container, surgeryAdapter)
            val intensiveCareUnitView = intensiveCareUnitList.createDoctorsList(inflater, container, intensiveCareUnitAdapter)

            with(binding.recyclerViewsLayout) {
                addView(therapyView)
                addView(surgeryView)
                addView(intensiveCareUnitView)
            }
        }
    }
    private fun clearList(list: MutableList<Doctor>) {
        list.clear()
    }

    private val therapyAdapter : GroupAdapter<GroupieViewHolder> by lazy {
        val groupAdapter = GroupAdapter<GroupieViewHolder>()
        groupAdapter.setHasStableIds(true)
        groupAdapter
    }

    private val surgeryAdapter : GroupAdapter<GroupieViewHolder> by lazy {
        val groupAdapter = GroupAdapter<GroupieViewHolder>()
        groupAdapter.setHasStableIds(true)
        groupAdapter
    }

    private val intensiveCareUnitAdapter : GroupAdapter<GroupieViewHolder> by lazy {
        val groupAdapter = GroupAdapter<GroupieViewHolder>()
        groupAdapter.setHasStableIds(true)
        groupAdapter
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        lazyContainer.getValue(
            { FragmentDoctorsListBinding.inflate(inflater, container, false) },
            { initFragmentViews(it, inflater, container) }
        ).root


    @SuppressLint("CheckResult")
    private fun initFragmentViews(binding: FragmentDoctorsListBinding, inflater: LayoutInflater, container: ViewGroup?) {
        this.inflater = inflater
        this.container = container
        this.binding = binding
        doctorListSubject.subscribe(doctorListConsumer)
    }

    private fun fillAdapter(adapter: GroupAdapter<GroupieViewHolder>, list: List<Doctor>) {
        if(adapter.groupCount > 0) adapter.clear()
        adapter.addAll(createGroups(list))
        adapter.notifyDataSetChanged()
    }

    private fun createGroups(list: List<Doctor>): List<DoctorViewItem> {
        val groups = mutableListOf<DoctorViewItem>()
        list.forEachIndexed { i, data ->
            groups.add(DoctorViewItem(i.toLong(),data))
        }
        return groups
    }

    private fun List<Doctor>.createDoctorsList(layoutInflater: LayoutInflater, container: ViewGroup?, adapter: GroupAdapter<GroupieViewHolder>): View {
        val itemBinding = RecyclerViewDoctorsBinding.inflate(layoutInflater, container, false)
        itemBinding.doctorsRecycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        itemBinding.doctorsRecycler.adapter = adapter
        fillAdapter(adapter, this)
        return itemBinding.root
    }
}