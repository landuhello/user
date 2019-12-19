package com.example.personaldetails.activity.renwu;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.blankj.utilcode.util.ColorUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.example.personaldetails.R;


/*
 *@Auther:老刘
 *@Date: 时间
 *@Description:功能
 * */
public class SignBar extends View {

    private Paint paint;
    private Paint paint2;
    private Paint paint3;
    private int textsize;
    private int day;

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public SignBar(Context context) {
        this(context, null);
    }

    public SignBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SignBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initA();
    }

    private void initA() {
        paint = new Paint();
        paint.setColor(Color.CYAN);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(3);

        paint2 = new Paint();
        paint2.setColor(Color.BLUE);
        paint2.setStyle(Paint.Style.FILL);
        paint2.setAntiAlias(true);
        paint2.setStrokeWidth(3);

        paint3 = new Paint();
        paint3.setColor(Color.BLACK);
        paint3.setStyle(Paint.Style.FILL);
        paint3.setAntiAlias(true);
        textsize = SizeUtils.sp2px(12);
        paint3.setTextSize(textsize);
        paint3.setFakeBoldText(true);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int high = 100;
        int i1 = SizeUtils.dp2px(20);
        int wd = (width - 2 * i1) / 7;
        for (int i = 0; i < 7; i++) {
            int cx = wd * i + i1;
            canvas.drawText("+ " + (i + 1), cx + i1 - 16, 50, paint3);
            canvas.drawCircle(cx + i1, high, 16, paint);
            canvas.drawText((i + 1) + "天", cx + i1 - 16, 150 + paint3.getTextSize(), paint3);
            if (i < 6) {
                canvas.drawLine(cx + i1, high, wd * (i + 1) + i1 * 2, high, paint);
            }
        }

        if (day<=7) {
            for (int i = 0; i < day; i++) {
                int cx2 = wd * i + i1;
                canvas.drawCircle(cx2 + i1, high, 16, paint2);
                if (i <day-1) {
                    canvas.drawLine(cx2 + i1, high,  wd * (i + 1) + i1 * 2 , high, paint2);
                }
            }
        }else if (day>7){
            day=7;
            for (int i = 0; i < day; i++) {
                int cx2 = wd * i + i1;
                canvas.drawCircle(cx2 + i1, high, 16, paint2);
                if (i <day-1) {
                    canvas.drawLine(cx2 + i1, high,  wd * (i + 1) + i1 * 2 , high, paint2);
                }
            }
        }else if (day==0){
            return;
        }
    }
}

