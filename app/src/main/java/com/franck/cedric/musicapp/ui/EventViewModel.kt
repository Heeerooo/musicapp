package com.franck.cedric.musicapp.ui

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel


abstract class EventViewModel<T : EventViewModel.Event> : ViewModel() {

    private val event: MutableLiveData<T> = MutableLiveData()


    abstract class Event {
        override fun toString() = this.javaClass.simpleName.toString()
    }

    fun observe(owner: LifecycleOwner, observer: Observer<T>) = event.observe(owner, observer)

    protected fun dispatch(event: T) {
        this.event.value = event
    }

}