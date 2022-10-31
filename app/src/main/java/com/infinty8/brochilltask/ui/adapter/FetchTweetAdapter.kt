package com.infinty8.brochilltask.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.infinty8.brochilltask.databinding.TweetItemBinding
import com.infinty8.brochilltask.model.TweetResponse

/**
 * @author Ved Prakash
 * @created 10/30/2022
 */
class FetchTweetAdapter(val context: Context) :
    RecyclerView.Adapter<FetchTweetAdapter.FetchTweetAdapterHolder>() {
    class FetchTweetAdapterHolder(var binding: TweetItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val differCallBack = object : DiffUtil.ItemCallback<TweetResponse>() {

        override fun areItemsTheSame(oldItem: TweetResponse, newItem: TweetResponse) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: TweetResponse, newItem: TweetResponse) =
            oldItem == newItem

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FetchTweetAdapterHolder =
        FetchTweetAdapterHolder(
            TweetItemBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onBindViewHolder(holder: FetchTweetAdapterHolder, position: Int) {
        val tweet = differ.currentList[position]
        holder.binding.tweetData.text = tweet.tweet

    }

    override fun getItemCount() =
        differ.currentList.size

}