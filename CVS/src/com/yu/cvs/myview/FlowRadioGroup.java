package com.yu.cvs.myview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RadioGroup;

public class FlowRadioGroup extends RadioGroup {

	public FlowRadioGroup(Context context) {
		super(context);
	}

	public FlowRadioGroup(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int maxWidth = MeasureSpec.getSize(widthMeasureSpec);
		int childCount = getChildCount();
		int x = 0;
		int y = 0;
		int row = 0;
		int marginTop = 0;
		int marginRight = 0;
		int marginBottom = 0;
		int marginLeft = 0;

		for (int index = 0; index < childCount; index++) {
			final View child = getChildAt(index);
			if (child.getVisibility() != View.GONE) {
				child.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
				// 此处增加onlayout中的换行判断，用于计算所需的高度
				
				RadioGroup.LayoutParams p = (LayoutParams) child.getLayoutParams();
				int width = child.getMeasuredWidth() + p.leftMargin + p.rightMargin;
				int height = child.getMeasuredHeight() + p.topMargin + p.bottomMargin;
				
				x += width;
				y = row * height + height;
				if (x > maxWidth) {
					x = width;
					row++;
					y = row * height + height;
				}
			}
		}
		// 设置容器所需的宽度和高度
		setMeasuredDimension(maxWidth, y);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		final int childCount = getChildCount();
		int maxWidth = r - l;
		int x = 0;
		int y = 0;
		int row = 0;
		for (int i = 0; i < childCount; i++) {
			final View child = this.getChildAt(i);
			RadioGroup.LayoutParams p = (LayoutParams) child.getLayoutParams();
			if (child.getVisibility() != View.GONE) {
				int viewWidth = child.getMeasuredWidth();
				int viewHeight = child.getMeasuredHeight();
				
				int layoutWidth = viewWidth + p.leftMargin + p.rightMargin;
				int layoutHeight = viewHeight + p.topMargin + p.bottomMargin;
				
				
				x += layoutWidth;
				y = row * layoutHeight + layoutHeight;
				if (x > maxWidth) {
					x = layoutWidth;
					row++;
					y = row * layoutHeight + layoutHeight;
				}
				
				child.layout(x - layoutWidth + p.leftMargin , y - layoutHeight + p.topMargin , x - p.rightMargin, y - p.bottomMargin);
			}
		}
	}

}
