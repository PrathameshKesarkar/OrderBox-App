package com.madinfotech.orderbox.ui.screens.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import com.madinfotech.orderbox.R;

/**
 * Created by prathameshkesarkar on 28/07/16.
 */
public class DayLeftView extends View {
    Paint backgroundPaint;
    TextPaint numberPaint;

    private static final int DEFAULT_HEIGHT = 150;
    private static final int DEFAULT_WIDTH = 150;
    private int defaultWidth;
    private int defaultHeight;
    private float centerX;
    private float centerY;
    private float radius;
    private int strokeWidth;
    private long numberOfDays=0;

    public DayLeftView(Context context) {
        super(context);
        init(context);
    }


    private void init(Context context) {
        backgroundPaint = new Paint();
        numberPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            backgroundPaint.setColor(context.getColor(R.color.dull_lime));
            numberPaint.setColor(context.getColor(R.color.dull_lime));
        } else {
            backgroundPaint.setColor(context.getResources().getColor(R.color.dull_lime));
            backgroundPaint.setColor(context.getResources().getColor(R.color.dull_lime));

        }
        backgroundPaint.setStyle(Paint.Style.STROKE);
        strokeWidth = dpToPixel(context, 2);
        backgroundPaint.setStrokeWidth(strokeWidth);
        numberPaint.setTextSize(22f * getResources().getDisplayMetrics().scaledDensity);

        defaultHeight = dpToPixel(context, DEFAULT_HEIGHT);
        defaultWidth = dpToPixel(context, DEFAULT_WIDTH);

    }

    public DayLeftView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DayLeftView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(21)
    public DayLeftView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int resultWidth = 0;
        int resultHeight = 0;

        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);

        if (modeWidth == MeasureSpec.EXACTLY) {
            resultWidth = sizeWidth;
        } else {
            resultWidth = defaultWidth;
            if (modeWidth == MeasureSpec.AT_MOST) {
                resultWidth = Math.min(resultWidth, sizeWidth);
            }
        }

        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);

        if (modeHeight == MeasureSpec.EXACTLY) {
            resultHeight = sizeHeight;
        } else {
            resultHeight = defaultHeight;
            if (modeHeight == MeasureSpec.AT_MOST) {
                resultHeight = Math.min(resultHeight, sizeHeight);
            }
        }
        setMeasuredDimension(resultWidth, resultHeight);
    }


    private int dpToPixel(Context context, int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return (int) ((dp * displayMetrics.density) + 0.5);
    }

    public void setDayLeftDate(long days) {
        numberOfDays = days;
        if (numberOfDays <= 2) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                backgroundPaint.setColor(getContext().getColor(R.color.sunset_orange_delay));
                numberPaint.setColor(getContext().getColor(R.color.sunset_orange_delay));
            } else {
                backgroundPaint.setColor(getContext().getResources().getColor(R.color.sunset_orange_delay));
                backgroundPaint.setColor(getContext().getResources().getColor(R.color.sunset_orange_delay));

            }
        } else if (numberOfDays >= 3 && numberOfDays <= 5) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                backgroundPaint.setColor(getContext().getColor(R.color.whiskey));
                numberPaint.setColor(getContext().getColor(R.color.whiskey));
            } else {
                backgroundPaint.setColor(getContext().getResources().getColor(R.color.whiskey));
                backgroundPaint.setColor(getContext().getResources().getColor(R.color.whiskey));

            }
        } else if (numberOfDays > 6) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                backgroundPaint.setColor(getContext().getColor(R.color.dull_lime));
                numberPaint.setColor(getContext().getColor(R.color.dull_lime));
            } else {
                backgroundPaint.setColor(getContext().getResources().getColor(R.color.dull_lime));
                backgroundPaint.setColor(getContext().getResources().getColor(R.color.dull_lime));

            }
        }
        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        centerX = w / 2;
        centerY = h / 2;
        radius = Math.min(w, h);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int canvasWidth = canvas.getWidth();
        int canvasHeight = canvas.getHeight();
        centerX = Math.round(canvasWidth * 0.5f);
        centerY = Math.round(canvasHeight * 0.5f);
        float textOffsetX = numberPaint.measureText(String.valueOf(numberOfDays)) * 0.5f;
        float textOffsetY = numberPaint.getFontMetrics().ascent * -0.4f;


        radius = ((canvasWidth < canvasHeight ? canvasWidth : canvasHeight) * 0.5f) - (strokeWidth * 0.5f);
        canvas.drawCircle(centerX, centerY, radius, backgroundPaint);
        canvas.drawText(String.valueOf(numberOfDays), centerX - textOffsetX, centerY + textOffsetY, numberPaint);
    }
}
