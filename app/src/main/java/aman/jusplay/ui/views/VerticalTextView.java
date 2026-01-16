package aman.jusplay.ui.views;

import android.content.Context;
import android.graphics.Canvas;
import android.text.TextPaint;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;

public class VerticalTextView extends AppCompatTextView {

    public VerticalTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // Measure normally...
        super.onMeasure(heightMeasureSpec, widthMeasureSpec);
        // ...then swap Width and Height to report the vertical size
        setMeasuredDimension(getMeasuredHeight(), getMeasuredWidth());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // MANUALLY DRAW THE TEXT
        // This bypasses any layout clipping issues
        TextPaint paint = getPaint();
        paint.setColor(getCurrentTextColor());
        paint.drawableState = getDrawableState();

        canvas.save();

        // Move to the exact center of the view
        canvas.translate(getWidth() / 2f, getHeight() / 2f);

        // Rotate -90 degrees
        canvas.rotate(-90);

        // Calculate Text Position to center it
        String text = getText().toString();
        float textWidth = paint.measureText(text);
        float textHeightOffset = (paint.descent() + paint.ascent()) / 2;

        // Draw text centered
        canvas.drawText(text, -textWidth / 2f, -textHeightOffset, paint);

        canvas.restore();
    }
}
