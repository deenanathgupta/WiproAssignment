package com.sample.wiproassignment.common

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.widget.LinearLayoutCompat
import com.sample.wiproassignment.databinding.NoIntenetLayoutBinding
import com.sample.wiproassignment.extension.singleClickListener

class NoInternetLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayoutCompat(context, attrs, defStyleAttr) {

    private var callback: Callback? = null
    private var binding: NoIntenetLayoutBinding

    init {
        binding = NoIntenetLayoutBinding.inflate(LayoutInflater.from(context), this, true)
        binding.retryButton.singleClickListener { callback?.onRetryClick() }
    }

    fun setCallbackListener(callback: Callback) {
        this.callback = callback
    }

    interface Callback {
        fun onRetryClick()
    }
}