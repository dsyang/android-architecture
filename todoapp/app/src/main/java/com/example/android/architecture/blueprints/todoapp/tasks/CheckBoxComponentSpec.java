package com.example.android.architecture.blueprints.todoapp.tasks;

import android.widget.CheckBox;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.Size;
import com.facebook.litho.annotations.MountSpec;
import com.facebook.litho.annotations.OnCreateMountContent;
import com.facebook.litho.annotations.OnMeasure;
import com.facebook.litho.annotations.OnMount;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.utils.MeasureUtils;

@MountSpec
public class CheckBoxComponentSpec {

    @OnCreateMountContent
    static CheckBox onCreateMountContent(ComponentContext c) {
        return new CheckBox(c);
    }

    @OnMeasure
    static void onMeasure(
        ComponentContext c,
        ComponentLayout layout,
        int widthSpec,
        int heightSpec,
        Size size) {
        CheckBox cb = new CheckBox(c);
        cb.measure(
            MeasureUtils.getViewMeasureSpec(widthSpec),
            MeasureUtils.getViewMeasureSpec(heightSpec));

        size.height = cb.getMeasuredHeight();
        size.width = cb.getMeasuredWidth();
    }

    @OnMount
    static void onMount(
        ComponentContext c,
        CheckBox checkBox,
        @Prop boolean isChecked) {
        checkBox.setChecked(isChecked);
    }
}
