package com.yashinsergey.clinic.ui.views

import com.xwray.groupie.databinding.BindableItem
import com.yashinsergey.clinic.R
import com.yashinsergey.clinic.databinding.ItemAppointmentTimeBinding
import com.yashinsergey.clinic.model.repos.network.json.Receptions
import io.reactivex.functions.Consumer

class AppointmentTimeItem(
    id: Long,
    private val reception: Receptions,
    private val requestDialogConsumer: Consumer<Pair<Int, String>>
) :
    BindableItem<ItemAppointmentTimeBinding>(id) {

    override fun getLayout(): Int = R.layout.item_appointment_time

    override fun bind(viewBinding: ItemAppointmentTimeBinding, position: Int) {
        viewBinding.item = reception
        viewBinding.itemLayout.setOnClickListener {
            requestDialogConsumer.accept(Pair(reception.id, viewBinding.time.text.toString()))
        }
    }
}