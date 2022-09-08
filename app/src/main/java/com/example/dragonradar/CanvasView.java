package com.example.dragonradar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

public class CanvasView extends View {
    Paint paint;

    public CanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        final float centerX = getWidth() * 0.5f;
        final float centerY = getHeight() * 0.5f;
        final float radius = Math.min(centerX, centerY);

        // 背景、半透明
        canvas.drawColor(Color.argb(0, 255, 255, 255));

        // 内円
        paint.setColor(Color.argb(255, 27, 193, 52));
//        paint.setStrokeWidth(40);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        // (x1,y1,r,paint) 中心x1座標, 中心y1座標, r半径
//        canvas.drawCircle(450, 450, 100, paint);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(centerX, centerY, (float) (radius*0.95), paint);

        // 外円
        paint.setColor(Color.argb(255, 150, 150, 150));
        paint.setStrokeWidth(40);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        // (x1,y1,r,paint) 中心x1座標, 中心y1座標, r半径
//        canvas.drawCircle(450, 450, 100, paint);
        canvas.drawCircle(centerX, centerY, (float) (radius*0.95), paint);

        // 中心の三角
        paint.setColor(Color.argb(255, 200, 15, 15));
        paint.setStyle(Paint.Style.FILL);
        Path path = new Path();
        path.moveTo(centerX, (float) (centerY*0.95));
        path.lineTo((float) (centerX-centerY*0.1/Math.sqrt(3)), (float) (centerY*1.05));
        path.lineTo((float) (centerX+centerY*0.1/Math.sqrt(3)), (float) (centerY*1.05));
        path.close();
        canvas.drawPath(path,paint);


        // 矩形 首
        paint.setColor(Color.argb(255, 120, 120, 120));
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStyle(Paint.Style.FILL);
        // (x1,y1,x2,y2,paint) 左上の座標(x1,y1), 右下の座標(x2,y2)
//        canvas.drawRect(480, 480, 850, 880, paint);
        canvas.drawRect((float) (centerX*0.9), (float) (centerY-radius*1.05),
                (float) (centerX*1.1), (float) (centerY-radius*0.95), paint);

        // 矩形　押すとこ
        paint.setColor(Color.argb(255, 150, 150, 150));
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStyle(Paint.Style.FILL);
        // (x1,y1,x2,y2,paint) 左上の座標(x1,y1), 右下の座標(x2,y2)
//        canvas.drawRect(480, 480, 850, 880, paint);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            canvas.drawRoundRect((float) (centerX*0.8), (float) (centerY-radius*1.15),
                    (float) (centerX*1.2), (float) (centerY-radius*1.05), 10, 10, paint);
        }

        // 円の半径を1mとする radius*0.95
        float circleRadius = 100;

        // parameter get
        float distance = TerminalFragment.getDistance();
        float azimuth = TerminalFragment.getAzimuth();
        float pointX = (float) (distance*Math.sin(Math.toRadians(azimuth)));
        float pointY = (float) (distance*Math.cos(Math.toRadians(azimuth)));

        float pointCircleX = (float) (centerX + radius*0.95*pointX/100);
        float pointCircleY = (float) (centerY + radius*0.95*pointY/100);

        // 点
        paint.setColor(Color.argb(255, 255, 255, 0));
        canvas.drawCircle(pointCircleX, pointCircleY, 15, paint);


        // 線
//        paint.setStrokeWidth(15);
//        paint.setColor(Color.argb(255, 0, 255, 120));
//        // (x1,y1,x2,y2,paint) 始点の座標(x1,y1), 終点の座標(x2,y2)
//        canvas.drawLine(350, 850, 750, 630, paint);
    }
}
