package com.wazing.keywordsearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Filter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.wazing.keywordsearch.adapter.SearchAdapter
import kotlinx.android.synthetic.main.activity_main.*
import java.util.regex.Pattern
import kotlin.collections.ArrayList

/**
 * @author wazing
 */
class MainActivity : AppCompatActivity() {

    private val adapter: SearchAdapter by lazy { SearchAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler_view.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))
            this.adapter = this@MainActivity.adapter
        }
        val list = arrayListOf(
            "a1", "a22", "cc1", "d2", "027-00000000", "1*(5+5)=10", "100@gmail.com",
            "wazing.github.io"
        )
        adapter.addItem(list)

        search_input_key.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val keyword = s?.toString() ?: ""
                adapter.setInputKeyword(keyword)
//                val tempList =  list.stream().filter { it.contains(inputKey) }.collect(Collectors.toList())

                // Pattern.quote()转成文本形式
                val pattern: Pattern = Pattern.compile("(?i)${Pattern.quote(keyword)}")
                val tempList = ArrayList<String>()
                for (str in list) {
                    val matcher = pattern.matcher(str)
                    if (matcher.find()) {
                        tempList.add(str)
                    }
                }
                adapter.addItem(tempList)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit
        })
    }

}
