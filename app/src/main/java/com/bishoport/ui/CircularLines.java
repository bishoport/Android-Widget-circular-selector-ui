package com.bishoport.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.Html;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by bishoport on 29/6/16.
 */
public class CircularLines extends View {

    private Context context;

    private int xBitmapLess = 100;
    private int yBitmapLess = 100;

    private int xBitmapMore = 100;
    private int yBitmapMore = 100;


    private Bitmap bitmapLess;
    private Bitmap bitmapMore;

    private Paint mPaint;

    private int r = 230;

    private int indexLine = 5;

    private int iNumberOfLines = 20 ;
    private int indexLineMax = iNumberOfLines;

    private float circleStroke = 60;

    private int colorOn = Color.parseColor("#ffffff");

    private Paint paintText;
    private Paint paintCircle;
    private Paint paintButton;
    private OnSetNumberCircularLinesListener listener;



    //Public Functions API
    public void setListener(OnSetNumberCircularLinesListener listener) {
        this.listener = listener;
    }



    public void setInitialValue(String value){
        indexLine = indexLineMax - Integer.parseInt(value);
        invalidate();
    }



    public void setiMaximumValue(int iNumberOfLines) {
        this.iNumberOfLines = iNumberOfLines;
        this.indexLineMax = iNumberOfLines;
        init();
        invalidate();
    }




    //Constructors
    public CircularLines(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public CircularLines(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public CircularLines(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }


    private void init(){
        bitmapLess = BitmapFactory.decodeResource(getResources(),R.drawable.btn_less);
        bitmapMore = BitmapFactory.decodeResource(getResources(),R.drawable.btn_more);
        mPaint = new Paint();
        mPaint.setStrokeWidth(10);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setColor(colorOn);

        paintText = new Paint();
        paintText.setColor(Color.WHITE);
        paintText.setTextSize(120);
        paintText.setAntiAlias(true);
        paintText.setTextAlign(Paint.Align.CENTER);


        paintCircle = new Paint();
        paintCircle.setStyle(Paint.Style.STROKE);
        paintCircle.setStrokeWidth(circleStroke);
        paintCircle.setColor(0xFFFFFFFF);
        paintCircle.setAlpha(60);


        paintButton = new Paint();
        paintButton.setAntiAlias(true);
        paintButton.setFilterBitmap(true);
        paintButton.setDither(true);
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float x = getWidth() / 2;
        float y = getHeight() / 2;

        r =  getWidth() / 2;

        circleStroke = getWidth() / 17.0f;

        paintCircle.setStrokeWidth(circleStroke);
        mPaint.setStrokeWidth(circleStroke / 5.0f);
        paintText.setTextSize(circleStroke * 4);

        bitmapLess = Bitmap.createScaledBitmap(bitmapLess, getWidth() / 6, getWidth() / 6, false);
        bitmapMore = Bitmap.createScaledBitmap(bitmapMore, getWidth() / 6, getWidth() / 6, false);

        //LINES
        float iNumberOfLinesFLoat = new Float(iNumberOfLines);
        float correctionAngleStart = circleStroke / 5.0f;

        for( int i = 0; i < iNumberOfLines; i++ ){

            if(i < indexLine){
                mPaint.setAlpha(60);
            }
            else{
                mPaint.setAlpha(255);
            }

            float x2 = (float)( x + (r - 10) * Math.cos(Math.toRadians((i / iNumberOfLinesFLoat * - (180.0f + correctionAngleStart)))));
            float y2 = (float)( y + (r - 10) * Math.sin(Math.toRadians((i / iNumberOfLinesFLoat * - (180.0f + correctionAngleStart)))));



            canvas.drawLine(x2 ,
                            y2 ,
                            (float)(x+((r / 2.0f) + circleStroke + ( circleStroke / 1.5f) )*Math.cos(Math.toRadians(((i / iNumberOfLinesFLoat) * - (180.0f + correctionAngleStart))))),
                            (float)(y+((r / 2.0f) + circleStroke + ( circleStroke / 1.5f))*Math.sin(Math.toRadians(((i / iNumberOfLinesFLoat) * - (180.0f + correctionAngleStart))))),
                            mPaint);

            canvas.save();
        }



        //BUTTONS
        xBitmapLess = (int) (x - (r  ));
        yBitmapLess = (getHeight() / 2) + 30;

        xBitmapMore = (int) (x + (r - bitmapMore.getWidth()));
        yBitmapMore = (getHeight() / 2) + 30;

        canvas.drawBitmap(bitmapLess, xBitmapLess, yBitmapLess, paintButton);
        canvas.drawBitmap(bitmapMore, xBitmapMore, yBitmapMore, paintButton);



        //CIRCLE
        canvas.drawCircle(x, y, r - (r / 2.5f), paintCircle);


        //TEXT
        canvas.drawText(Html.fromHtml(String.valueOf(indexLineMax - indexLine) + "<sup>º</sup>").toString(), x + 5 , y + (circleStroke * 1.5f), paintText);
    }



    public boolean onTouchEvent(MotionEvent event){
        int action = event.getAction();
        int x = (int) event.getX();  // or getRawX();
        int y = (int) event.getY();

        switch(action){
            case MotionEvent.ACTION_DOWN:

                //ButtonLess
                if (x >= xBitmapLess && x < (xBitmapLess + bitmapLess.getWidth()) && y >= yBitmapLess && y < (yBitmapLess + bitmapLess.getHeight())) {

                    indexLine++;


                    if (indexLine > iNumberOfLines){
                        indexLine = iNumberOfLines;
                    }

                    listener.onSetNumberCircularLines(String.valueOf(indexLineMax - indexLine));

                    invalidate();
                    return true;
                }
                else if (x >= xBitmapMore && x < (xBitmapMore + bitmapMore.getWidth()) && y >= yBitmapMore && y < (yBitmapMore + bitmapMore.getHeight())) {
                //ButtonMore
                    indexLine--;

                    if (indexLine < 0){
                        indexLine = 0;
                    }

                    listener.onSetNumberCircularLines(String.valueOf(indexLineMax - indexLine));

                    invalidate();
                    return true;
                }
        }
        return false;
    }



    public interface OnSetNumberCircularLinesListener{
        void onSetNumberCircularLines(String value);
    }




}