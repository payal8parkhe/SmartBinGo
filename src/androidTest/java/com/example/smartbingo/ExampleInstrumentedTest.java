package com.example.smartbingo;
import android.content.Context;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test
        Context appContext = ApplicationProvider.getApplicationContext();
        assertEquals("com.example.smartbingo", appContext.getPackageName());
    }
}
