package com.example.tarde.customview_canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by tarde on 13/02/2015.
 */
public class CirculoView extends View {

    private Paint pincel;

    public CirculoView(Context context) {
        this(context, null);
    }

    public CirculoView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CirculoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        pincel = new Paint();
        pincel.setColor(Color.RED);
        pincel.setStrokeWidth(1.5f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(500,300,100,pincel);
    }
}
