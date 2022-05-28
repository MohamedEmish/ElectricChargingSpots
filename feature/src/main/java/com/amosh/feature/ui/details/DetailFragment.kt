package com.amosh.feature.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.navArgs
import com.amosh.base.BaseFragment
import com.amosh.common.callPhoneNumber
import com.amosh.common.openMapLocation
import com.amosh.feature.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailsBinding>() {

    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDetailsBinding
        get() = FragmentDetailsBinding::inflate

    private val args: DetailFragmentArgs by navArgs()

    override fun prepareView(savedInstanceState: Bundle?) {
        setData()
    }

    private fun setData() {
        if (args.spot == null)
            requireActivity().onBackPressed()

        with(binding) {
            tvName.text = args.spot?.name
            tvAddress.text = args.spot?.address
            tvDistance.text = "${args.spot?.distance} Km"
            tvConnectorsNumber.text = args.spot?.numberOfConnectors?.toString()
            tvMaxPower.text = args.spot?.maxPowerLevel?.toString()
            btnMap.setOnClickListener {
                requireActivity().openMapLocation(
                    lat = args.spot?.latitude ?: return@setOnClickListener,
                    lng = args.spot?.longitude ?: return@setOnClickListener,
                    label = args.spot?.name ?: return@setOnClickListener
                )
            }
        }
    }
}
