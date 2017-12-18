package com.example.android.architecture.blueprints.todoapp;

import android.app.Application;
import com.facebook.soloader.SoLoader;

public class TodoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SoLoader.init(this, false);
    }
}
