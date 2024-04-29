package cse340.doodle;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.view.View;
import android.widget.FrameLayout;

public class Part2Activity extends AbstractMainActivity {

    /**
     * Callback that is called when the activity is first created.
     * @param savedInstanceState contains the activity's previously saved state
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     }

    /**
     * This is the function that does the actual doodling! It gets a doodleView and can then
     * call addLine, addCircle, addImage, and addText to doodle on the it.
     *
     * @param doodleView    Canvas on which to doodle.
     */
    public void doodle(FrameLayout doodleView) {
        // TODO: Implement your own doodle code below!
        Paint brush = new Paint();
        brush.setStyle(Paint.Style.STROKE);
        brush.setColor(Color.MAGENTA);
        brush.setStrokeWidth(5);

        float halfX = PHONE_DIMS.x / 2;
        float halfY = PHONE_DIMS.y/ 2;

        //Objects fade in
        MyView drawnPolygon = new MyView(this,DimHelp.DP2PX(halfX, this)-200,
                            DimHelp.DP2PX(halfY, this)-400,
                                  400, 800, brush);
        doodleView.addView(drawnPolygon);

        android.animation.ObjectAnimator fadeIn = android.animation.ObjectAnimator.ofFloat(
                drawnPolygon,"alpha", 0f, 1f);
        fadeIn.setDuration(1000);
        fadeIn.start();

        brush.setColor(Color.RED);

        CircleView drawnCircle = new CircleView(this, halfX, halfY, 50, brush);
        doodleView.addView(drawnCircle);

        android.animation.ObjectAnimator fadeInTwo = android.animation.ObjectAnimator.ofFloat(
                drawnCircle, "alpha", 0f, 1f);
        fadeInTwo.setDuration(5000);
        fadeInTwo.start();

        //fade in slide in lines
        brush.setColor(Color.BLUE);
        LineView rayOne = new LineView(this, halfX, halfY-125, halfX+50,halfY-75, brush);
        LineView rayTwo = new LineView(this, halfX, halfY+125, halfX+50,halfY+75, brush);
        LineView rayThree = new LineView(this, halfX+200, halfY-125, halfX+150,halfY-75, brush);
        LineView rayFour = new LineView(this, halfX+200, halfY+125, halfX+150,halfY+75, brush);

        slider(rayOne, doodleView, 0f,310f);
        slider(rayTwo, doodleView,0f, 310f);
        slider(rayThree, doodleView, 900f, 640f);
        slider(rayFour, doodleView, 900f, 640f);

        //Image of eye URL: https://pngimg.com/uploads/eye/eye_PNG35657.png
        addAllImagesFromData(doodleView, "part2.csv");

        //Everything fades out except the eye
        android.os.Handler handler = new android.os.Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                android.animation.ObjectAnimator fadeOutOne = android.animation.ObjectAnimator.ofFloat(
                        drawnCircle, "alpha", 1f, 0f);
                fadeOutOne.setDuration(3000);
                fadeOutOne.start();

                android.animation.ObjectAnimator fadeOutTwo = android.animation.ObjectAnimator.ofFloat(
                        drawnPolygon, "alpha", 1f, 0f);
                fadeOutTwo.setDuration(3000);
                fadeOutTwo.start();

                android.animation.ObjectAnimator fadeOutThree = android.animation.ObjectAnimator.ofFloat(
                        rayOne, "alpha", 1f, 0f);
                fadeOutThree.setDuration(3000);
                fadeOutThree.start();

                android.animation.ObjectAnimator fadeOutFour = android.animation.ObjectAnimator.ofFloat(
                        rayTwo, "alpha", 1f, 0f);
                fadeOutFour.setDuration(3000);
                fadeOutFour.start();

                android.animation.ObjectAnimator fadeOutFive = android.animation.ObjectAnimator.ofFloat(
                        rayThree, "alpha", 1f, 0f);
                fadeOutFive.setDuration(3000);
                fadeOutFive.start();

                android.animation.ObjectAnimator fadeOutSix = android.animation.ObjectAnimator.ofFloat(
                        rayFour, "alpha", 1f, 0f);
                fadeOutSix.setDuration(3000);
                fadeOutSix.start();

            }
        }, 5000);

        //Text fades in
        TextView text = new TextView(this, halfX/8, halfY+50, 32, "Welcome to the Syndicate", brush);
        handler.postDelayed(new Runnable() {
            public void run() {
              doodleView.addView(text);
                android.animation.ObjectAnimator fadeIn = android.animation.ObjectAnimator.ofFloat(
                        text,"alpha", 0f, 1f);
                fadeIn.setDuration(3000);
                fadeIn.start();
            }
        }, 8000);

    }

    public static void slider(LineView ray, FrameLayout doodleView, float start, float end) {
        android.animation.ObjectAnimator textViewAnimator = android.animation.ObjectAnimator.ofFloat(
                ray, "translationX", start, end);
        textViewAnimator.setDuration(5000);
        textViewAnimator.start();

        android.animation.ObjectAnimator fadeIn = android.animation.ObjectAnimator.ofFloat(
                ray, "alpha", 0f, 1f);
        fadeIn.setDuration(5000);
        doodleView.addView(ray);
    }

}
