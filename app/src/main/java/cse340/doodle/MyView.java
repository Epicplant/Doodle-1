package cse340.doodle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * TODO Extend or modify this class as you wish as part of Activity3
 */
public class MyView extends DrawView {
    /**
     * Constructor for a basic Draw View
     *
     * @param context The Context the view is running in, through which it can access the current theme, resources, etc.
     * @param brush   A paint object for styling when drawing
     */
    public MyView(Context context, float parentStartX, float parentStartY, float width, float height,  Paint brush) {
        super(context, brush);
        initFromParentCoordsPX(parentStartX,
                parentStartY,
                width,
                height);
    }

    /**
     * Draw something on the Canvas
     * @param canvas the canvas that is drawn upon
     */
    protected void onDraw(Canvas canvas) {
        float strokeWidth = getBrush().getStrokeWidth();
        canvas.drawLine(0, getMaxHeight()/2, getMaxWidth()/2, 0, getBrush());
        canvas.drawLine(0, getMaxHeight()/2, getMaxWidth()/2, getMaxHeight(), getBrush());
        canvas.drawLine(0, getMaxHeight()/2, getMaxWidth()/2, getMaxHeight()/4, getBrush());
        canvas.drawLine(0, getMaxHeight()/2, getMaxWidth()/2, getMaxHeight()*3/4, getBrush());

        canvas.drawLine(getMaxWidth()/2, 0, getMaxWidth()/2, getMaxHeight()/4, getBrush());
        canvas.drawLine(getMaxWidth()/2, getMaxHeight(), getMaxWidth()/2, getMaxHeight()*3/4, getBrush());

        canvas.drawLine(getMaxWidth(), getMaxHeight()/2, getMaxWidth()/2, 0, getBrush());
        canvas.drawLine(getMaxWidth(), getMaxHeight()/2, getMaxWidth()/2, getMaxHeight(), getBrush());
        canvas.drawLine(getMaxWidth(), getMaxHeight()/2, getMaxWidth()/2, getMaxHeight()*3/4, getBrush());
        canvas.drawLine(getMaxWidth(), getMaxHeight()/2, getMaxWidth()/2, getMaxHeight()/4, getBrush());

    }
}
