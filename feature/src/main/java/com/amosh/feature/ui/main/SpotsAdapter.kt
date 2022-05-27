package com.amosh.feature.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.amosh.base.BaseRecyclerAdapter
import com.amosh.base.BaseViewHolder
import com.amosh.common.Constants
import com.amosh.feature.databinding.RowSpotItemLayoutBinding
import com.amosh.feature.model.ChargerSpotUiModel


/**
 * Adapter class for RecyclerView
 */
class SpotsAdapter constructor(
    private val clickFunc: ((ChargerSpotUiModel?) -> Unit)? = null,
    private val fetchNext: (() -> Unit)? = null,
) : BaseRecyclerAdapter<ChargerSpotUiModel, RowSpotItemLayoutBinding, SpotsAdapter.SpotViewHolder>(
    SpotItemDiffUtil()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpotViewHolder {
        val binding = RowSpotItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return SpotViewHolder(binding = binding, click = clickFunc, fetchNext = fetchNext)
    }

    inner class SpotViewHolder(
        private val binding: RowSpotItemLayoutBinding,
        private val click: ((ChargerSpotUiModel?) -> Unit)? = null,
        private val fetchNext: (() -> Unit)? = null,
    ) : BaseViewHolder<ChargerSpotUiModel, RowSpotItemLayoutBinding>(binding) {
        override fun bind() {
            if ((currentList.size - 1) - absoluteAdapterPosition == Constants.LIST_BOTTOM_OFFSET)
                fetchNext?.invoke()

            getRowItem()?.let {
                with(binding) {
                    root.setOnClickListener {
                        click?.invoke(getRowItem())
                    }
                    tvName.text = it.name
                    tvAddress.text = it.address
                    tvDistance.text = "${it.distance} KM"
                    tvConnectorsNumber.text = it.numberOfConnectors?.toString()

                }
            }
        }
    }
}


class SpotItemDiffUtil : DiffUtil.ItemCallback<ChargerSpotUiModel>() {
    override fun areItemsTheSame(oldItem: ChargerSpotUiModel, newItem: ChargerSpotUiModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ChargerSpotUiModel, newItem: ChargerSpotUiModel): Boolean {
        return oldItem == newItem
    }
}