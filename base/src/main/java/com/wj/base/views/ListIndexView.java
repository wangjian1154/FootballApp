package com.wj.base.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * Created by wj on 2018/3/6.
 * ListView、RecyclerView右侧索引栏目
 */

public class ListIndexView extends View {

    private String[] array = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
            "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "#"};

    private Paint mPaint;

    private int selectPosition = -1;

    private TextView textViewDialog;

    private UpdateListView updateListView;

    public ListIndexView(Context context) {
        super(context);
        initPaint();
    }

    public ListIndexView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public ListIndexView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }


    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(24);
    }

    public void setTextViewDialog(TextView textViewDialog) {
        this.textViewDialog = textViewDialog;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int perTextHeight = getHeight() / array.length;
        for (int i = 0; i < array.length; i++) {
            if (i == selectPosition) {
                mPaint.setColor(Color.RED);
            } else {
                mPaint.setColor(Color.BLACK);
            }
            canvas.drawText(array[i], (getWidth() - mPaint.measureText(array[i])) / 2,
                    (i + 1) * perTextHeight, mPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int perTextHeight = getHeight() / array.length;
        float y = event.getY();
        int currentPosition = (int) (y / perTextHeight);
        if (currentPosition > -1 && currentPosition < array.length) {
            String letter = array[currentPosition];

            switch (event.getAction()) {

                case MotionEvent.ACTION_UP:

                    setBackgroundColor(Color.TRANSPARENT);

                    if (textViewDialog != null) {
                        textViewDialog.setVisibility(View.GONE);
                    }
                    break;

                default:
                    setBackgroundColor(Color.parseColor("#cccccc"));
                    if (currentPosition > -1 && currentPosition < array.length) {
                        if (textViewDialog != null) {
                            textViewDialog.setVisibility(View.VISIBLE);
                            textViewDialog.setText(letter);
                        }
                        if (updateListView != null) {
                            updateListView.updateListView(letter);
                        }
                        selectPosition = currentPosition;
                    }
                    break;
            }

            invalidate();
        }
        return true;
    }

    public void setUpdateListView(UpdateListView updateListView) {
        this.updateListView = updateListView;
    }

    public interface UpdateListView {
        void updateListView(String currentChar);
    }

    public void updateLetterIndexView(int currentChar) {

        for (int i = 0; i < array.length; i++) {
            if (currentChar == array[i].charAt(0)) {
                selectPosition = i;
                invalidate();
                break;
            }
        }
    }
}
