package com.example.mvvmpayments.ui.main;

import androidx.lifecycle.LifecycleOwner;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.mvvmpayments.ApplicationMain;
import com.example.mvvmpayments.ui.BaseNavigator;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

@RunWith(AndroidJUnit4.class)
public class MainViewModelTest extends TestCase {

    private MainViewModel viewModel;
    @Mock
    private LifecycleOwner owner;

    public MainViewModelTest() {
    }

    @Before
    @Override
    protected void setUp() throws Exception {
        super.setUp();

        ApplicationMain application = ApplicationProvider.getApplicationContext();
        BaseNavigator navigator = new BaseNavigator() {
            @Override
            public void notifyUser(String text, NotifyType type) {

            }

            @Override
            public void onNavigateBack(String className) {

            }

            @Override
            public void onNavigateForward(String className) {

            }

            @Override
            public void onAPIResponse(Object obj) {

            }
        };

        MockitoAnnotations.initMocks(LifecycleOwner.class);

        viewModel = new MainViewModel(application,navigator,owner);

    }
}