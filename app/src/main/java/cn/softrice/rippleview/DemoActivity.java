package cn.softrice.rippleview;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

/**
 * Created by softrice on 15/12/31.
 */
public class DemoActivity extends Activity {


    RippleView rippleView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        rippleView = (RippleView) findViewById(R.id.rippleView);
        rippleView.setCircleColor(Color.RED);
        rippleView.setCirclePadding(30);
        rippleView.setMaxRadiu(400);
        rippleView.setMinRadiu(0);
        rippleView.setRingWidth(5);
        rippleView.setVelocity(1);
    }
}
