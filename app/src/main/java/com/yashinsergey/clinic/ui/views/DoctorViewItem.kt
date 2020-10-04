package com.yashinsergey.clinic.ui.views

import com.xwray.groupie.databinding.BindableItem
import com.yashinsergey.clinic.R
import com.yashinsergey.clinic.common.loadImage
import com.yashinsergey.clinic.databinding.ItemDoctorBinding
import com.yashinsergey.clinic.model.repos.network.json.Doctor
import com.yashinsergey.clinic.ui.ButtonId
import io.reactivex.Observer

class DoctorViewItem(
    id: Long,
    private val doctor: Doctor,
    private val clickSubject: Observer<ButtonId>,
    private val doctorObserver: Observer<Doctor>
): BindableItem<ItemDoctorBinding>(id) {

    override fun getLayout(): Int = R.layout.item_doctor

    override fun bind(viewBinding: ItemDoctorBinding, position: Int) {
        viewBinding.item = doctor
        loadImage(doctor.photoUrl, viewBinding.itemImage)
        viewBinding.itemRippleBackground.setOnClickListener {
            clickSubject.onNext(ButtonId.CALENDAR)
            doctorObserver.onNext(doctor)
        }
    }
}