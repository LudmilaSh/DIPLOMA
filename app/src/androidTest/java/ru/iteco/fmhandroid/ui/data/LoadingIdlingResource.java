package ru.iteco.fmhandroid.ui.data;

import androidx.test.espresso.IdlingResource;

public class LoadingIdlingResource implements IdlingResource {
    private final long startTime;
    private final long waitingTime;
    private ResourceCallback resourceCallback;

    public LoadingIdlingResource(long waitingTime) {
        this.startTime = System.currentTimeMillis();
        this.waitingTime = waitingTime;
    }

    @Override
    public String getName() {
        return LoadingIdlingResource.class.getName();
    }

    @Override
    public boolean isIdleNow() {
        long elapsedTime = System.currentTimeMillis() - startTime;
        boolean idle = (elapsedTime >= waitingTime);
        if (idle && resourceCallback != null) {
            resourceCallback.onTransitionToIdle();
        }
        return idle;
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback resourceCallback) {
        this.resourceCallback = resourceCallback;
    }

}
