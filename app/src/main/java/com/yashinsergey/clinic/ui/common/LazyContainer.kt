package com.yashinsergey.clinic.ui.common

class LazyContainer<T>() {

    var value: T? = null

    fun getValue(create:() -> T, onCreate:(T) -> Unit): T {
        val v = value ?: create.invoke()
        if (value != v) { onCreate.invoke(v) }
        value = v
        return v
    }
}