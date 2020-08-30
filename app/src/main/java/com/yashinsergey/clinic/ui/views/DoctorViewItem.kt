package com.yashinsergey.clinic.ui.views

import com.xwray.groupie.databinding.BindableItem
import com.yashinsergey.clinic.R
import com.yashinsergey.clinic.common.loadImage
import com.yashinsergey.clinic.databinding.ItemDoctorBinding
import com.yashinsergey.clinic.model.repos.network.json.Doctor

class DoctorViewItem(id: Long, private val doctor: Doctor): BindableItem<ItemDoctorBinding>(id) {

    val TEMPORARY_IMAGE_URL = "https://avatars.mds.yandex.net/get-zen_doc/1712971/pub_5e3d3f756e37684c66f326a9_5e417145fbe2fb5ea305e1fb/scale_1200"

    override fun getLayout(): Int = R.layout.item_doctor

    override fun bind(viewBinding: ItemDoctorBinding, position: Int) {
        viewBinding.item = doctor
        loadImage(TEMPORARY_IMAGE_URL, viewBinding.itemImage)
    }
}