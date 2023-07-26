package ru.iteco.fmhandroid.ui.data;

import static androidx.test.InstrumentationRegistry.getTargetContext;

import android.os.Environment;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.UiDevice;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import java.io.File;

public class ScreenshotTestRule extends TestWatcher {

    @Override
    protected void failed(Throwable e, Description description) {
        super.failed(e, description);

        File path = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
                + "/screenshots/" + getTargetContext().getPackageName());
        if (!path.exists()) {
            path.mkdirs();
        }

        UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        String filename = description.getClassName() + "-" + description.getMethodName() + ".png";
        device.takeScreenshot(new File(path, filename));
    }
}

