package com.wazing.keywordsearch.adapter

import android.content.Context
import android.graphics.Color
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wazing.keywordsearch.R
import java.util.regex.Pattern

/**
 * @author wazing
 */
class SearchAdapter(
    private val context: Context
) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    private val list: ArrayList<String> = ArrayList()

    private var pattern: Pattern? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(parent, R.layout.item_search)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun setInputKeyword(inputKey: String) {
        this.pattern = Pattern.compile("(?i)${Pattern.quote(inputKey)}")
    }

    fun addItem(list: List<String>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    inner class SearchViewHolder(
        parent: ViewGroup, layoutId: Int
    ) : RecyclerView.ViewHolder(
        LayoutInflater.from(context).inflate(layoutId, parent, false)
    ) {
        fun bind(item: String) {
            val ss = SpannableString(item)
            pattern?.let {
                val matcher = it.matcher(item)
                while (matcher.find()) {
                    ss.setSpan(
                        ForegroundColorSpan(Color.RED),
                        matcher.start(), matcher.end(),
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                }
            }
            (itemView as TextView).text = ss
        }
    }

}