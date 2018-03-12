package com.zestworks.surveys.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

class PageIndicator(context: Context) : RecyclerView.ItemDecoration() {

    companion object {
        const val CIRCLE_RADIUS = 10f
    }

    private var horizontalPadding = 0f
    private var inActivePaint = Paint()
    private var activePaint = Paint()

    init {
        inActivePaint.color = Color.WHITE
        inActivePaint.style = Paint.Style.STROKE
        inActivePaint.strokeWidth = 4f
        activePaint.color = Color.WHITE
        activePaint.style = Paint.Style.FILL
        activePaint.strokeWidth = 4f
        horizontalPadding = 40 * context.resources.displayMetrics.density
    }

    override fun onDrawOver(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State?) {
        super.onDrawOver(canvas, parent, state)
        if (parent.adapter.itemCount == 0) {
            return
        }
        drawInActiveCircles(canvas, parent)
        drawActiveCircle(canvas, parent)

    }

    private fun drawInActiveCircles(canvas: Canvas, parent: RecyclerView) {
        val top = parent.height / (parent.adapter.itemCount * 2)
        val circleListHeight = parent.adapter.itemCount * (2 * CIRCLE_RADIUS)
        val circleTop = (parent.height - circleListHeight) / 2
        canvas.save()
        canvas.translate(parent.width - horizontalPadding, circleTop)
        for (i in 0 until parent.adapter.itemCount) {
            canvas.save()
            canvas.translate(0f, (top * i) / 2f)
            canvas.drawCircle(CIRCLE_RADIUS / 2, CIRCLE_RADIUS / 2, CIRCLE_RADIUS, inActivePaint)
            canvas.restore()
        }
        canvas.restore()
    }

    private fun drawActiveCircle(canvas: Canvas, parent: RecyclerView) {
        val layoutManager = parent.layoutManager as LinearLayoutManager
        val activePosition = layoutManager.findFirstVisibleItemPosition()
        if (activePosition == RecyclerView.NO_POSITION) {
            return
        }

        val top = parent.height / (parent.adapter.itemCount * 2)
        val circleListHeight = parent.adapter.itemCount * (2 * CIRCLE_RADIUS)
        val circleTop = (parent.height - circleListHeight) / 2
        canvas.save()
        canvas.translate(parent.width - horizontalPadding, circleTop)
        canvas.translate(0f, (top * activePosition) / 2f)
        canvas.drawCircle(CIRCLE_RADIUS / 2, CIRCLE_RADIUS / 2, CIRCLE_RADIUS, activePaint)
        canvas.restore()
    }

}