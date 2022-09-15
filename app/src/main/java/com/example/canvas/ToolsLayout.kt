package com.example.canvas

import android.content.Context
import android.util.AttributeSet
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.canvas.base.setAdapterAndCleanupOnDetachFromWindow
import com.example.canvas.model.ToolItem

class ToolsLayout @JvmOverloads constructor(

    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {

    private var onClick: (Int) -> Unit = {}

    private val toolsList: RecyclerView by lazy { findViewById(R.id.rvTools) }

    private val adapterDelegate = ListDelegationAdapter(
        colorAdapterDelegate {
            onClick(it)
        },
        toolsAdapterDelegate {
            onClick(it) },

        sizeAdapterDelegate {
            onClick(it) }

    )

//    private val adapterDelegate = ListDelegationAdapter(
//        colorAdapterDelegate {
//            onClick(it)
//        },
//        toolsAdapterDelegate {
//            onClick(it)
//        }
//    )

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
// ресуклервью, у которго есть LinearLayoutManager менеджер, reverseLayout- это нужно ли повернуть Layout
        toolsList.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        toolsList.setAdapterAndCleanupOnDetachFromWindow(adapterDelegate)
    }

    // В этот адаптер делегат будут помещаться тулайтемы, которые будут отрисовываться внутри нашего ресайклера
    fun render(list: List<ToolItem>) {
        adapterDelegate.setData(list)
    }

    fun setOnClickListener(onClick: (Int) -> Unit) {
        this.onClick = onClick
    }
}