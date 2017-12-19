package com.example.android.architecture.blueprints.todoapp.tasks;

import android.graphics.Typeface;
import com.example.android.architecture.blueprints.todoapp.R;
import com.example.android.architecture.blueprints.todoapp.data.Task;
import com.facebook.litho.ClickEvent;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.Row;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.OnEvent;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.widget.Text;

import static com.facebook.yoga.YogaAlign.CENTER;
import static com.facebook.yoga.YogaEdge.BOTTOM;
import static com.facebook.yoga.YogaEdge.LEFT;
import static com.facebook.yoga.YogaEdge.RIGHT;
import static com.facebook.yoga.YogaEdge.TOP;

@LayoutSpec
public class TaskItemSpec {

    @OnCreateLayout
    static ComponentLayout onCreateLayout(
        ComponentContext c,
        @Prop Task task) {
        return Row.create(c)
            .widthPercent(100)
            .heightAttr(R.attr.listPreferredItemHeight)
            .backgroundRes(
                task.isCompleted()
                    ? R.drawable.list_completed_touch_feedback
                    : R.drawable.touch_feedback)
            .paddingRes(LEFT, R.dimen.activity_horizontal_margin)
            .paddingRes(RIGHT, R.dimen.activity_horizontal_margin)
            .paddingRes(BOTTOM, R.dimen.list_item_padding)
            .paddingRes(TOP, R.dimen.list_item_padding)
            .alignItems(CENTER)
            .child(CheckBoxComponent.create(c)
                .alignSelf(CENTER)
                .isChecked(task.isCompleted())
                .clickHandler(TaskItem.onCheckboxClick(c))
                .build())
            .child(Text.create(c, 0, R.style.TextAppearance_AppCompat_Title)
                .textStyle(Typeface.BOLD)
                //.textColorAttr(android.R.attr.textColorPrimary)
                .marginRes(LEFT, R.dimen.activity_horizontal_margin)
                .text(task.getTitleForList()))
            .clickHandler(TaskItem.onTaskClick(c))
            .build();
    }

    @OnEvent(ClickEvent.class)
    static void onTaskClick(
        ComponentContext c,
        @Prop Task task,
        @Prop TasksFragment.TaskItemListener itemListener) {
        itemListener.onTaskClick(task);
    }

    @OnEvent(ClickEvent.class)
    static void onCheckboxClick(
        ComponentContext c,
        @Prop Task task,
        @Prop TasksFragment.TaskItemListener itemListener) {
        if (!task.isCompleted()) {
            itemListener.onCompleteTaskClick(task);
        } else {
            itemListener.onActivateTaskClick(task);
        }
    }
}
