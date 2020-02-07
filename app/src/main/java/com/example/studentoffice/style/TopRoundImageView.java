package com.example.studentoffice.style;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;


public class TopRoundImageView extends AppCompatImageView {

    private final RectF roundRect = new RectF();
    private float rect_radius = 16;
    private final Paint maskPaint = new Paint();
    private final Paint zonePaint = new Paint();


    public TopRoundImageView(Context context) {
        super(context);
        init();
    }

    public TopRoundImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TopRoundImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        maskPaint.setAntiAlias(true);
        maskPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        zonePaint.setAntiAlias(true);
        zonePaint.setColor(Color.WHITE);
        float density = getResources().getDisplayMetrics().density;
        rect_radius *= density;
    }

//    public void setRectAdius(float adius) {
//        rect_radius = adius;
//        invalidate();
//    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        int w = getWidth();
        int h = getHeight();
        roundRect.set(0, 0, w, h + rect_radius);
    }

    @Override
    public void draw(Canvas canvas) {
        Canvas mConvas = canvas;

        canvas.saveLayer(roundRect, zonePaint, Canvas.ALL_SAVE_FLAG);
        canvas.drawRoundRect(roundRect, rect_radius, rect_radius, zonePaint);
        canvas.saveLayer(roundRect, maskPaint, Canvas.ALL_SAVE_FLAG);
        try {
            super.draw(canvas);
        } catch (Exception e){
            super.draw(new Canvas());
        }
        canvas.restore();
    }

}