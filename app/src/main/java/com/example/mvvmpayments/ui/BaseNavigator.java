package com.example.mvvmpayments.ui;

public interface BaseNavigator {

    void notifyUser(String text, NotifyType type);

    void onNavigateBack(String className);

    void onNavigateForward(String className);

    void onAPIResponse(Object obj);

    enum NotifyType {
        TYPE_TOAST,
        TYPE_ALERT,
        TYPE_SNACKBAR
    }
}
