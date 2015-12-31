package cn.softrice.ripple_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by softrice on 15/12/30.
 */
public class RippleView extends View {

    //计时器
    Timer timer;
    int circlePadding;// 水圈之间的距离 px
    int firstRadiu;//最里圈水圈的半径 px
    int maxRadiu;//水圈的最大直径 px
    int velocity;//水圈速度 px/18ms
    int ringWidth;//水圈宽度 px
    int center;//水圈中心坐标 px
    int minRadiu;//最小半径  px
    Paint paint;

    public RippleView(Context context) {
        super(context);
        init();
    }

    public RippleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RippleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setBackgroundColor(Color.TRANSPARENT);
        timer = new Timer(true);
        timer.schedule(new UpdateUITimer(), 0, 18);
        firstRadiu = 0;
        circlePadding = dp2px(16);
        maxRadiu = dp2px(100);
        velocity = 1;
        minRadiu = dp2px(32);
        ringWidth = 3;
        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(ringWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        center = getWidth() / 2;
        firstRadiu += velocity;
        firstRadiu %= circlePadding;
        int tmpRadiu = firstRadiu;
        while (tmpRadiu < maxRadiu) {
            if (tmpRadiu < minRadiu) {
                tmpRadiu += circlePadding;
                continue;
            }
            paint.setAlpha(100 - 100 * (tmpRadiu - minRadiu) / (maxRadiu - minRadiu));
            canvas.drawCircle(center, center, tmpRadiu, paint);
            tmpRadiu += circlePadding;
        }
    }

    class UpdateUITimer extends TimerTask {

        @Override
        public void run() {
            update();
        }
    }

    void update() {
        postInvalidate();
    }

    public int dp2px(float dp) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
}
