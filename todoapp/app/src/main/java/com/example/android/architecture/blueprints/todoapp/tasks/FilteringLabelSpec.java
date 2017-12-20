package com.example.android.architecture.blueprints.todoapp.tasks;


import com.example.android.architecture.blueprints.todoapp.R;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.Row;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.annotations.ResType;
import com.facebook.litho.widget.Text;
import com.facebook.yoga.YogaEdge;

@LayoutSpec
public class FilteringLabelSpec {

    @OnCreateLayout
    static ComponentLayout onCreateLayout(
        ComponentContext c,
        @Prop(resType = ResType.STRING) String label) {
        return Row.create(c)
            .child(
                Text.create(c, 0, R.style.TextAppearance_AppCompat_Title)
                    .text(label)
                    //workaround for https://github.com/facebook/litho/issues/292
                    .textColorAttr(android.R.attr.textColorPrimary)
                    .marginRes(YogaEdge.LEFT, R.dimen.list_item_padding)
                    .marginRes(YogaEdge.RIGHT, R.dimen.list_item_padding)
                    .marginRes(YogaEdge.TOP, R.dimen.activity_vertical_margin)
                    .marginRes(YogaEdge.BOTTOM, R.dimen.activity_vertical_margin)
                    .build())
            .build();
    }
}
