 package cse340.doodle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import cse340.doodle.ColorUtils;
import cse340.doodle.DimHelp;
import cse340.doodle.Quadrant;

public class LineView extends DrawView {

    /**
     * The location of the start of the line in this box
     */
    private Quadrant mStart;

    /**
     * Constructor of the Line
     *
     * @param context      The Context the view is running in, through which it can access the current theme, resources, etc.
     * @param parentStartX The x position of the start of the line (in parent coordinates in dp)
     * @param parentStartY The x position of the start of the line (in parent coordinates in dp)
     * @param parentEndX   The x position of the start of the line (in parent coordinates in dp)
     * @param parentEndY   The x position of the start of the line (in parent coordinates in dp)
     * @param brush        The Paint object used to style the line
     */
    public LineView(Context context, float parentStartX, float parentStartY, float parentEndX, float parentEndY, Paint brush) {
        super(context, brush);

        initFromParentLinePX(DimHelp.DP2PX(parentStartX, context),
                DimHelp.DP2PX(parentStartY, context),
                DimHelp.DP2PX(parentEndX, context),
                DimHelp.DP2PX(parentEndY, context));
        setContentDescription(ColorUtils.GetColorName(brush.getColor()) + mStart);
    }

    /* Initializes the line from parent coordinates. This sets the starting and ending
     * quadrant of the line, and assigns a correct width and height.
     * @param parentStartX The starting X position of the line in parent coordinates (in pixels)
     * @param parentStartY The starting Y position of the line in parent coordinates (in pixels)
     * @param parentEndX The ending X position of the line in parent coordinates (in pixels)
     * @param parentEndY The ending Y position of the line in parent coordinates (in pixels)
     */
    public void initFromParentLinePX(float parentStartX, float parentStartY, float parentEndX, float parentEndY) {
        /**
         * Draw a Line on the Canvas
         * @param canvas the canvas that is drawn upon
         */
        /*
         * TODO calculate the left, right, top and bottom of the line's bounding box in parent coordinates
         */

        float topEdgeLocation = Math.min(parentStartY, parentEndY);
        float bottomEdgeLocation = Math.max(parentStartY, parentEndY);
        float rightEdgeLocation = Math.max(parentStartX, parentEndX);
        float leftEdgeLocation = Math.min(parentStartX, parentEndX);
        /*
         * TODO calculate the width and height of the line's bounding box
         */
        float height = bottomEdgeLocation - topEdgeLocation;
        float width = rightEdgeLocation - leftEdgeLocation;

        /*
         * TODO calculate what quadrant the line starts and ends in
         */
        if (parentStartX == leftEdgeLocation && parentStartY == topEdgeLocation) {
            mStart = Quadrant.TOPLEFT;
        } else if (parentStartX == rightEdgeLocation && parentStartY == topEdgeLocation) {
            mStart = Quadrant.TOPRIGHT;
        } else if (parentStartX == leftEdgeLocation && parentStartY == bottomEdgeLocation) {
            mStart = Quadrant.BOTTOMLEFT;
        } else if (parentStartX == rightEdgeLocation && parentStartY == bottomEdgeLocation) {
            mStart = Quadrant.BOTTOMRIGHT;
        }

        if (mStart == Quadrant.TOPLEFT) {
            if (parentEndX > parentStartX && parentEndY == parentStartY) {
                mStart = Quadrant.HORIZONTALLEFTRIGHT;
            } else if (parentEndY > parentStartY && parentEndX == parentStartX) {
                mStart = Quadrant.VERTICALTOPBOTTOM;
            }
        } else if (mStart == Quadrant.BOTTOMRIGHT) {
            if (parentEndX < parentStartX && parentEndY == parentStartY) {
                mStart = Quadrant.HORIZONTALRIGHTLEFT;
            } else if (parentEndY < parentStartY && parentEndX == parentStartX) {
                mStart = Quadrant.VERTICALBOTTOMTOP;
            }
        } else if (mStart == Quadrant.BOTTOMLEFT && parentEndX == parentStartX && parentEndY < parentStartY) {
            if (parentEndX > parentStartX && parentEndY == parentStartY) {
                mStart = Quadrant.HORIZONTALLEFTRIGHT;
            } else if (parentEndY < parentStartY && parentEndX == parentStartX) {
                mStart = Quadrant.VERTICALBOTTOMTOP;
            }
        } else if (mStart == Quadrant.TOPRIGHT && parentEndY == parentStartY && parentEndX < parentStartX) {
            if (parentEndX < parentStartX && parentEndY == parentStartY) {
                mStart = Quadrant.HORIZONTALRIGHTLEFT;
            } else if (parentEndY > parentStartY && parentEndX == parentStartX) {
                mStart = Quadrant.VERTICALTOPBOTTOM;
            }
        }

        /*
         * TODO initialize bounding box from parent coordinates
         * use initializeFromParent
         */

        if(mStart == Quadrant.VERTICALBOTTOMTOP || mStart == Quadrant.VERTICALTOPBOTTOM) {
            initFromParentCoordsPX((float)leftEdgeLocation-getBrush().getStrokeWidth()/2,
                    (float) topEdgeLocation-getBrush().getStrokeWidth()/2,
                    (float) width+getBrush().getStrokeWidth(),
                    (float) height);
        } else if(mStart == Quadrant.HORIZONTALRIGHTLEFT || mStart == Quadrant.HORIZONTALLEFTRIGHT) {
            initFromParentCoordsPX((float) leftEdgeLocation-getBrush().getStrokeWidth()/2,
                    (float) topEdgeLocation-getBrush().getStrokeWidth()/2,
                    (float) width,
                    (float) height+getBrush().getStrokeWidth());
        } else {
            initFromParentCoordsPX((float)leftEdgeLocation,
                    (float) topEdgeLocation,
                    (float) width,
                    (float) height);
        }



    }
    /**
     * Draw a Line on the Canvas
     * @param canvas the canvas that is drawn upon
     */
    protected void onDraw(Canvas canvas) {
        /*
         * TODO draw the line (in child coordinates)
         * To do so you will need to decide where to start and end it
         * based on this view's bounding box and the quadrant stored
         * in mStart
         *
         * Be sure to adjust properly for thickness in horizontal and vertical lines
         * You do *NOT* need to worry about thickness for angled lines, they will have
         * pointed ends due to clipping.
         *
         * Remember: onDraw should use px
         */
        float startX = 0;
        float startY = 0;
        float endX = 0;
        float endY = 0;
        if(mStart == Quadrant.TOPLEFT) {
            endX = getMaxWidth();
            endY = getMaxHeight();
        } else if (mStart == Quadrant.TOPRIGHT) {
            startX = getMaxWidth();
            endY = getMaxHeight();
        } else if (mStart == Quadrant.BOTTOMLEFT) {
            endX = getMaxWidth();
            startY = getMaxHeight();
        } else if (mStart == Quadrant.BOTTOMRIGHT) {
            startX = getMaxWidth();
            startY = getMaxHeight();
        }

        float strokeWidth = getBrush().getStrokeWidth();
        if(mStart == Quadrant.VERTICALBOTTOMTOP || mStart == Quadrant.VERTICALTOPBOTTOM) {
            canvas.drawLine(startX+strokeWidth/2, startY, startX+strokeWidth/2, getMaxHeight(), getBrush());
        } else if(mStart == Quadrant.HORIZONTALLEFTRIGHT || mStart == Quadrant.HORIZONTALRIGHTLEFT) {
            canvas.drawLine(startX, startY+strokeWidth/2, getMaxWidth(), startY+strokeWidth/2, getBrush());
        } else {
            canvas.drawLine(startX, startY, endX, endY, getBrush());
        }
    }
}

