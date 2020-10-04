package com.yashinsergey.clinic.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.yashinsergey.clinic.common.logE
import com.yashinsergey.clinic.databinding.FragmentDoctorsListBinding
import com.yashinsergey.clinic.model.data.BranchId
import com.yashinsergey.clinic.model.repos.network.json.Doctor
import com.yashinsergey.clinic.ui.adapters.DoctorsListsAdapters
import com.yashinsergey.clinic.ui.common.LazyContainer
import com.yashinsergey.clinic.viewmodel.DoctorsListFragmentViewModel
import io.reactivex.functions.Consumer
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import org.koin.androidx.viewmodel.ext.android.viewModel

class DoctorsListFragment: Fragment() {

    private val doctorsViewModel: DoctorsListFragmentViewModel by viewModel()
    private val lazyContainer = LazyContainer<FragmentDoctorsListBinding>()
    private lateinit var binding: FragmentDoctorsListBinding

    private lateinit var inflater: LayoutInflater
    private var container: ViewGroup? = null
    val click = PublishSubject.create<ButtonId>()

    val doctorListSubject = BehaviorSubject.create<List<Doctor>>()

    private val therapyList = mutableListOf<Doctor>()
    private val surgeryList = mutableListOf<Doctor>()
    private val intensiveCareList = mutableListOf<Doctor>()
    private lateinit var adapters: DoctorsListsAdapters

    private val doctorListConsumer = Consumer<List<Doctor>> {
        clearPage()
        it.forEach { doctor ->
            when (doctor.branch.id) {
                BranchId.THERAPY.id -> therapyList.add(doctor)
                BranchId.SURGERY.id -> surgeryList.add(doctor)
                BranchId.INTENSIVE_CARE_UNIT.id -> intensiveCareList.add(doctor)
            }
        }

        val therapyView = initView(adapters.therapyAdapter, therapyList)
        val surgeryView = initView(adapters.surgeryAdapter, surgeryList)
        val intensiveCareView = initView(adapters.intensiveCareAdapter, intensiveCareList)

        with(binding.recyclerViewsLayout) {
            addView(therapyView)
            addView(surgeryView)
            addView(intensiveCareView)
        }
    }

    private fun initView(adapter: GroupAdapter<GroupieViewHolder>, doctorsList: List<Doctor>): View =
        adapters.initRecyclerView(inflater, container, adapter, doctorsList, click)

    private fun clearPage() {
        binding.recyclerViewsLayout.removeAllViews()
        therapyList.clear()
        surgeryList.clear()
        intensiveCareList.clear()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        lazyContainer.getValue(
            { FragmentDoctorsListBinding.inflate(inflater, container, false) },
            {
                initFragmentViews(it, inflater, container)
                viewModelConnect()
            }
        ).root


    @SuppressLint("CheckResult")
    private fun initFragmentViews(binding: FragmentDoctorsListBinding, inflater: LayoutInflater, container: ViewGroup?) {
        this.inflater = inflater
        this.container = container
        this.binding = binding
        adapters = DoctorsListsAdapters(requireContext())
        doctorListSubject.subscribe(doctorListConsumer)
    }

    override fun onResume() {
        super.onResume()
        doctorsViewModel.getDoctorsList()
    }

    private fun viewModelConnect() {
        doctorsViewModel.allDoctorListResult.observe(viewLifecycleOwner, Observer {
            if (it.isSuccess) {
                it.getOrNull()?.let { data ->
                    doctorListSubject.onNext(data)
                }
            } else {
                logE("${it.exceptionOrNull()?.message}")
            }
        })
    }
}