package ru.iteco.fmhandroid.ui.data;
import android.graphics.Bitmap;
import android.os.Environment;
import androidx.test.runner.screenshot.ScreenCapture;
import androidx.test.runner.screenshot.Screenshot;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ScreenshotOnFailureRule extends TestWatcher {

    @Override
    protected void failed(Throwable e, Description description) {
        super.failed(e, description);
        captureScreenshot(description.getMethodName());
    }

    private void captureScreenshot(String methodName) {
        ScreenCapture capture = Screenshot.capture();
        Bitmap bitmap = capture.getBitmap();

        String screenshotDir = Environment.getExternalStorageDirectory() + "/screenshots/";
        File directory = new File(screenshotDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        String screenshotPath = screenshotDir + methodName + ".png";
        File file = new File(screenshotPath);

        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

