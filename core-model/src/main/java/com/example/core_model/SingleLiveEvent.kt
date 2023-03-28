package com.example.core_model

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

class SingleLiveEvent<T> : MediatorLiveData<T>() {

    private val mPending = AtomicBoolean(false)

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        // Observe the internal MutableLiveData
        super.observe(
            owner,
            Observer { t ->
                if (mPending.compareAndSet(true, false)) {
                    observer.onChanged(t)
                }
            }
        )
    }

    @MainThread
    override fun setValue(t: T?) {
        mPending.set(true)
        super.setValue(t)
    }

    @MainThread
    fun call() {
        value = null
    }

    companion object {

        private val TAG = "SingleLiveEvent"
    }
}

fun <T> MutableLiveData<T>.notLet(block: (MutableLiveData<T>) -> Unit) {
    if (this.value == null) block.invoke(this)
}