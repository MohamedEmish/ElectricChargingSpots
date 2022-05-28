package com.amosh.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.amosh.common.AppMessage
import com.tapadoo.alerter.Alerter

/**
 * Base class for all [Fragment] instances
 */
abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    private var _binding: VB? = null
    abstract val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> VB

    protected val binding : VB
        get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = bindLayout.invoke(inflater, container, false)
        return requireNotNull(_binding).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareView(savedInstanceState)
    }

    abstract fun prepareView(savedInstanceState: Bundle?)

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    fun showAppMessage(message: AppMessage, duration: Long = 3000L) {
        Alerter.create(requireActivity())
            .setDuration(duration)
            .setBackgroundColorRes(message.getMessageColor())
            .setText(message.message)
            .setTextAppearance(com.amosh.base.R.style.AppMessageStyle)
            .hideIcon()
            .show()
    }

}