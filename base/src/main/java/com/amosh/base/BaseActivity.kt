package com.amosh.base

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.amosh.common.AppMessage
import com.amosh.common.addIfNotExistAndNotNull
import com.tapadoo.alerter.Alerter

/**
 * Base class for all [AppCompatActivity] instances
 */
abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    private var _binding: VB? = null
    abstract val bindLayout: (LayoutInflater) -> VB

    protected val binding: VB
        get() = requireNotNull(_binding)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindLayout.invoke(layoutInflater)
        setContentView(requireNotNull(_binding).root)
        prepareView(savedInstanceState)
    }

    abstract fun prepareView(savedInstanceState: Bundle?)

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    fun showAppMessage(message: AppMessage, duration: Long = 3000L) {
        Alerter.create(this)
            .setDuration(duration)
            .setBackgroundColorRes(message.getMessageColor())
            .setText(message.message)
            .setTextAppearance(R.style.AppMessageStyle)
            .hideIcon()
            .show()
    }
}