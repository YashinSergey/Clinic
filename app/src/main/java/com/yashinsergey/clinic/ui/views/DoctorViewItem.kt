package com.yashinsergey.clinic.ui.views

import com.xwray.groupie.databinding.BindableItem
import com.yashinsergey.clinic.R
import com.yashinsergey.clinic.databinding.ItemDoctorBinding
import com.yashinsergey.clinic.model.repos.network.json.Doctor

class DoctorViewItem(id: Long, private val doctor: Doctor): BindableItem<ItemDoctorBinding>(id) {

    override fun getLayout(): Int = R.layout.item_doctor

    override fun bind(viewBinding: ItemDoctorBinding, position: Int) {
        viewBinding.item = doctor
    }
}