package com.example.android.architecture.blueprints.todoapp.tasks;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import com.example.android.architecture.blueprints.todoapp.R;
import com.example.android.architecture.blueprints.todoapp.data.Task;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.annotations.*;
import com.facebook.litho.sections.SectionContext;
import com.facebook.litho.sections.common.DataDiffSection;
import com.facebook.litho.sections.common.RenderEvent;
import com.facebook.litho.sections.widget.RecyclerCollectionComponent;
import com.facebook.litho.viewcompat.SimpleViewBinder;
import com.facebook.litho.viewcompat.ViewBinder;
import com.facebook.litho.viewcompat.ViewCreator;
import com.facebook.litho.widget.ComponentRenderInfo;
import com.facebook.litho.widget.RenderInfo;
import com.facebook.litho.widget.ViewRenderInfo;

import java.util.List;

@LayoutSpec
public class TasksSpec {

    private static final ViewCreator TASK_ITEM_VIEW_CREATOR = new ViewCreator() {
        @Override
        public View createView(Context c) {
            LayoutInflater inflater = LayoutInflater.from(c);
            return inflater.inflate(R.layout.task_item, null, false);
        }
    };

    @OnCreateLayout
    static ComponentLayout onCreateLayout(
        ComponentContext c,
        @Prop List<Task> tasks) {
        return RecyclerCollectionComponent.create(c)
            .disablePTR(true)
            .heightPercent(100)
            .section(DataDiffSection.<Task>create(new SectionContext(c))
                .data(tasks)
                .renderEventHandler(Tasks.renderTask(c))
                .build())
            .buildWithLayout();
    }

    @OnEvent(RenderEvent.class)
    static RenderInfo renderTask(
        final ComponentContext c,
        @FromEvent int index,
        @FromEvent final Task model,
        @Prop final TasksFragment.TaskItemListener itemListener) {
        if (index % 2 == 0) {
            return ComponentRenderInfo.create()
                .component(
                    TaskItem.create(c)
                        .task(model)
                        .itemListener(itemListener)
                        .build())
                .build();
        }


        return ViewRenderInfo.create()
            .viewCreator(TASK_ITEM_VIEW_CREATOR)
            .viewBinder(new SimpleViewBinder() {

                @Override
                public void bind(View rowView) {
                    TextView titleTV = (TextView) rowView.findViewById(R.id.title);
                    titleTV.setText(model.getTitleForList());

                    CheckBox completeCB = (CheckBox) rowView.findViewById(R.id.complete);

                    // Active/completed task UI
                    completeCB.setChecked(model.isCompleted());
                    if (model.isCompleted()) {
                        rowView.setBackgroundDrawable(
                            c.getResources().getDrawable(R.drawable.list_completed_touch_feedback));
                    } else {
                        rowView.setBackgroundDrawable(
                            c.getResources().getDrawable(R.drawable.touch_feedback));
                    }

                    completeCB.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (!model.isCompleted()) {
                                itemListener.onCompleteTaskClick(model);
                            } else {
                                itemListener.onActivateTaskClick(model);
                            }
                        }
                    });

                    rowView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            itemListener.onTaskClick(model);
                        }
                    });
                }
            })
            .build();
    }
}
