package org.justyand.cuttingtool;

import android.content.Context;
import android.text.Layout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.Button;

/**
 * Created by justy on 18.10.2016.
 */

public class ButtonDynamicTextSize extends Button {

    public ButtonDynamicTextSize(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setSingleLine();
        setEllipsize(TextUtils.TruncateAt.END);
    }

    public ButtonDynamicTextSize(Context context, AttributeSet attrs) {
        super(context, attrs);
        setSingleLine();
        setEllipsize(TextUtils.TruncateAt.END);
    }

    public ButtonDynamicTextSize(Context context) {
        super(context);
        setSingleLine();
        setEllipsize(TextUtils.TruncateAt.END);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        final Layout layout = getLayout();
        if (layout != null) {
            final int lineCount = layout.getLineCount();
            if (lineCount > 0) {
                final int ellipsisCount = layout.getEllipsisCount(lineCount - 1);
                if (ellipsisCount > 0) {

                    final float textSize = getTextSize();

                    // textSize is already expressed in pixels
                    setTextSize(TypedValue.COMPLEX_UNIT_PX, (textSize - 1));

                    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
                }
            }
        }
    }
}
