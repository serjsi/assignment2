package com.shpp.ssierykh.assignment1.utils

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.shpp.ssierykh.assignment1.R
import com.shpp.ssierykh.assignment1.utils.Constants.DEFAULT_COLOR_BACKGRAUND_GOOGLE
import com.shpp.ssierykh.assignment1.utils.Constants.DEFAULT_COLOR_BLUE_GOOGLE
import com.shpp.ssierykh.assignment1.utils.Constants.DEFAULT_COLOR_GREEN_GOOGLE
import com.shpp.ssierykh.assignment1.utils.Constants.DEFAULT_COLOR_RED_GOOGLE
import com.shpp.ssierykh.assignment1.utils.Constants.DEFAULT_COLOR_YELLOW_GOOGLE
import com.shpp.ssierykh.assignment1.utils.Constants.DEFAULT_CORNER_RADIUS
import com.shpp.ssierykh.assignment1.utils.Constants.DEFAULT_MARGIN_X_GOOGLE
import com.shpp.ssierykh.assignment1.utils.Constants.DEFAULT_MARGIN_Y_GOOGLE
import com.shpp.ssierykh.assignment1.utils.Constants.DEFAULT_SIZE_HEIGHT_GOOGLE
import com.shpp.ssierykh.assignment1.utils.Constants.DEFAULT_SIZE_WIDTH_GOOGLE
import com.shpp.ssierykh.assignment1.utils.Constants.DEFAULT_TEXT_SIZE
import com.shpp.ssierykh.assignment1.utils.Constants.DEFAULT_TURN_G
import com.shpp.ssierykh.assignment1.utils.extensions.convertDpToPixels
import com.shpp.ssierykh.assignment1.utils.extensions.convertSpToPixels
import com.shpp.ssierykh.myapplication.extentions.dpToPx




class GoogleCustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {



    private val paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val attitudeToTextSizeForInnerRadius = 0.40F
    private val attitudeToTextSizeForOuterRadius = 0.65F
    private val indentFromGAttitudeToTextSize = 3
    private val indentFromGOOGLEAttitudeToTextSize = 0.65F


    //colors default
    private var colorBackgroundGoogle = Color.parseColor(DEFAULT_COLOR_BACKGRAUND_GOOGLE)
    private var colorBlueGoogle =Color.parseColor(DEFAULT_COLOR_BLUE_GOOGLE)
    private var colorRedGoogle = Color.parseColor(DEFAULT_COLOR_RED_GOOGLE)
    private var colorYellowGoogle = Color.parseColor(DEFAULT_COLOR_YELLOW_GOOGLE)
    private var colorGreenGoogle = Color.parseColor(DEFAULT_COLOR_GREEN_GOOGLE)

    //dimens
    private var textSizeGoogle = DEFAULT_TEXT_SIZE
    private var cornerRadius = DEFAULT_CORNER_RADIUS
    private var marginGoogleX = DEFAULT_MARGIN_X_GOOGLE
    private var marginGoogleY = DEFAULT_MARGIN_Y_GOOGLE
    private var rotationG = DEFAULT_TURN_G


    init {
        paint.isAntiAlias = true
        setupAttributes(attrs)
    }

    private fun setupAttributes(attrs: AttributeSet?) {
        val typedArray = context.theme.obtainStyledAttributes(
            attrs, R.styleable.GoogleCustomView,
            0, 0
        )
        colorBackgroundGoogle = typedArray.getColor(
            R.styleable.GoogleCustomView_colorBackgroundGoogle, colorBackgroundGoogle
        )
        colorBlueGoogle = typedArray.getColor(
            R.styleable.GoogleCustomView_colorBlueGoogle,
            colorBlueGoogle
        )
        colorRedGoogle = typedArray.getColor(
            R.styleable.GoogleCustomView_colorRedGoogle,
            colorRedGoogle
        )
        colorYellowGoogle = typedArray.getColor(
            R.styleable.GoogleCustomView_colorYellowGoogle,
            colorYellowGoogle
        )
        colorGreenGoogle = typedArray.getColor(
            R.styleable.GoogleCustomView_colorGreenGoogle,
            colorGreenGoogle
        )
        textSizeGoogle = typedArray.getDimension(
            R.styleable.GoogleCustomView_textSizeGoogle.convertSpToPixels(context),
            DEFAULT_TEXT_SIZE.toFloat()
        ).toInt()
        cornerRadius = typedArray.getDimension(
            R.styleable.GoogleCustomView_cornerBackground.convertDpToPixels(context),
            DEFAULT_CORNER_RADIUS
        )
        marginGoogleX = typedArray.getDimension(
            R.styleable.GoogleCustomView_marginGoogleX.convertDpToPixels(context),
            DEFAULT_MARGIN_X_GOOGLE
        )
        marginGoogleY = typedArray.getDimension(
            R.styleable.GoogleCustomView_marginGoogleY.convertDpToPixels(context),
            DEFAULT_MARGIN_X_GOOGLE
        )
        rotationG = typedArray.getDimension(
            R.styleable.GoogleCustomView_rotationG,
            DEFAULT_TURN_G.toFloat()
        ).toInt()
        typedArray.recycle()
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val initSizeX = resolveDefaultSizeWidth(widthMeasureSpec)
        val initSizeY = resolveDefaultSizeHeight(heightMeasureSpec)
        setMeasuredDimension(initSizeX, initSizeY)
    }

    private fun resolveDefaultSizeWidth(spec: Int): Int {
        return when (MeasureSpec.getMode(spec)) {
            MeasureSpec.UNSPECIFIED -> context.dpToPx(DEFAULT_SIZE_WIDTH_GOOGLE).toInt()
            MeasureSpec.AT_MOST -> (marginGoogleX * 2 + pairRadiusLaterG().second * 8 +
                    pairRadiusLaterG().first * 2).toInt()
            MeasureSpec.EXACTLY -> MeasureSpec.getSize(spec)
            else -> MeasureSpec.getSize(spec)
        }
    }

    private fun resolveDefaultSizeHeight(spec: Int): Int {
        return when (MeasureSpec.getMode(spec)) {
            MeasureSpec.UNSPECIFIED -> context.dpToPx(DEFAULT_SIZE_HEIGHT_GOOGLE).toInt()
            MeasureSpec.AT_MOST -> (marginGoogleY * 2 + pairRadiusLaterG().first * 2 +
                    pairRadiusLaterG().second).toInt()
            MeasureSpec.EXACTLY -> MeasureSpec.getSize(spec)
            else -> MeasureSpec.getSize(spec)
        }
    }

    private fun startDrawingForCenteringWidth(): Float {
        return (measuredWidth - (marginGoogleX * 2 + pairRadiusLaterG().second * 8 +
                pairRadiusLaterG().first * 2).toInt()) / 2 +
                pairRadiusLaterG().second / 2 + pairRadiusLaterG().first + marginGoogleX
    }

    private fun startDrawingForCenteringHeight(): Float {
        return (measuredHeight - (marginGoogleY * 2 + pairRadiusLaterG().first * 2 +
                pairRadiusLaterG().second).toInt()) / 2 +
                pairRadiusLaterG().second / 2 + pairRadiusLaterG().first + marginGoogleY
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val (innerRadiusLaterG, outerRadiusLaterG) = pairRadiusLaterG()

        if (canvas != null) {
            drawBackground(canvas)
        }
        if (canvas != null) {
            drawG(canvas, outerRadiusLaterG, innerRadiusLaterG)
        }
        if (canvas != null) {
            drawLetterGoogle(outerRadiusLaterG, canvas)
        }


    }

    private fun pairRadiusLaterG(): Pair<Float, Float> {
        val innerRadiusLaterG = textSizeGoogle * attitudeToTextSizeForInnerRadius
        val outerRadiusLaterG = textSizeGoogle * attitudeToTextSizeForOuterRadius
        return Pair(innerRadiusLaterG, outerRadiusLaterG)
    }

    private fun drawBackground(canvas: Canvas) {
        paint.apply {
            style = Paint.Style.FILL
            isAntiAlias = true
            paint.color = colorBackgroundGoogle
        }

        canvas.drawRoundRect(
            0F,
            0F,
            measuredWidth.toFloat(),
            measuredHeight.toFloat(),
            cornerRadius,
            cornerRadius,
            paint
        )
    }

    private fun drawG(canvas: Canvas, outerRadiusLaterG: Float, innerRadiusLaterG: Float) {
        canvas.rotate(
            rotationG.toFloat(),
            startDrawingForCenteringWidth(),
            startDrawingForCenteringHeight()
        )
        drawStartG(outerRadiusLaterG, innerRadiusLaterG, canvas)
        arcLetterG(canvas, outerRadiusLaterG, innerRadiusLaterG, -12, 75, colorBlueGoogle)
        arcLetterG(canvas, outerRadiusLaterG, innerRadiusLaterG, 60, 95, colorGreenGoogle)
        arcLetterG(canvas, outerRadiusLaterG, innerRadiusLaterG, 155, 45, colorYellowGoogle)
        arcLetterG(canvas, outerRadiusLaterG, innerRadiusLaterG, 200, 110, colorRedGoogle)
        canvas.rotate(
            -rotationG.toFloat(),
            startDrawingForCenteringWidth(),
            startDrawingForCenteringHeight()
        )
    }

    private fun drawStartG(outerRadiusLaterG: Float, innerRadiusLaterG: Float, canvas: Canvas) {
        paint.apply {
            style = Paint.Style.FILL
            isAntiAlias = true
        }

        paint.color = colorBlueGoogle
        val startLaterX = startDrawingForCenteringWidth()
        val startLaterY = startDrawingForCenteringHeight()
        val halfRadius = (outerRadiusLaterG - innerRadiusLaterG) / 2

        canvas.drawRect(
            startLaterX,
            startLaterY - halfRadius,
            startLaterX + outerRadiusLaterG,
            startLaterY + halfRadius,
            paint
        )
    }

    private fun arcLetterG(
        canvas: Canvas?,
        outer_radius: Float,
        inner_radius: Float,
        arc_ofSet: Int,
        arc_sweep: Int,
        colorArc: Int
    ) {
        val startLaterX = startDrawingForCenteringWidth()
        val startLaterY = startDrawingForCenteringHeight()

        val outerRect = RectF(

            (startLaterX - outer_radius),
            (startLaterY - outer_radius),
            (startLaterX + outer_radius),
            (startLaterY + outer_radius)
        )
        val innerRect = RectF(
            (startLaterX - inner_radius),
            (startLaterY - inner_radius),
            (startLaterX + inner_radius),
            (startLaterY + inner_radius)
        )

        val path = Path()
        path.arcTo(outerRect, arc_ofSet.toFloat(), arc_sweep.toFloat())
        path.arcTo(innerRect, (arc_ofSet + arc_sweep).toFloat(), (-arc_sweep).toFloat())
        path.close()

        val fill = Paint()
        fill.color = colorArc
        canvas!!.drawPath(path, fill)

        val border = Paint()
        border.style = Paint.Style.STROKE
        border.strokeWidth = 2F
    }


    private fun drawLetterGoogle(
        outerRadiusLaterG: Float,
        canvas: Canvas
    ) {
        val startLaterX = startDrawingForCenteringWidth()
        val startLaterY = startDrawingForCenteringHeight()
        val shiftGoogleX = startLaterX + outerRadiusLaterG
        val shiftGoogleY = startLaterY + textSizeGoogle / indentFromGAttitudeToTextSize
        val longBetweenLater = textSizeGoogle * indentFromGOOGLEAttitudeToTextSize
        paint.apply {
            style = Paint.Style.FILL
            isAntiAlias = true
            textSize = textSizeGoogle.toFloat()
            typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
        }

        paint.color = colorBlueGoogle
        canvas.drawText("G", shiftGoogleX + longBetweenLater * 1F, shiftGoogleY, paint)
        canvas.drawText("G", shiftGoogleX + longBetweenLater * 4F, shiftGoogleY, paint)
        paint.color = colorRedGoogle
        canvas.drawText("O", shiftGoogleX + longBetweenLater * 2F, shiftGoogleY, paint)
        canvas.drawText("E", shiftGoogleX + longBetweenLater * 6F, shiftGoogleY, paint)
        paint.color = colorYellowGoogle
        canvas.drawText("O", shiftGoogleX + longBetweenLater * 3F, shiftGoogleY, paint)
        paint.color = colorGreenGoogle
        canvas.drawText("L", shiftGoogleX + longBetweenLater * 5F, shiftGoogleY, paint)
    }

//for test animation
/*private fun animeTurnG(): Boolean{
        val va: ValueAnimator = ValueAnimator.ofInt(0,   360).apply {
            duration = 500
            interpolator = LinearInterpolator()
        }
        va.addUpdateListener {
            turnG = it.animatedValue as Int
            requestLayout()
        }

       // va.start()
        return true
    }*/


}