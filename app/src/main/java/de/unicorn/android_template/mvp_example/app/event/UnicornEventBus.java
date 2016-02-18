package de.unicorn.android_template.mvp_example.app.event;

import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import com.squareup.otto.Bus;
import de.unicorn.android_template.mvp_example.app.constant.EventBusConstants;
import roboguice.inject.ContextSingleton;
import roboguice.service.RoboService;

/**
 * @author Lukas Tibursky
 * @since 16.02.16
 *
 * Otto Bus wrapper to be able to define a ContextSingleton which will be garbage collected
 * if the app gets closed. If we want to keep the bus, even if the app gets closed,
 * we will have to use the @Singleton - annotation
 */
@ContextSingleton
public class UnicornEventBus extends RoboService {

    private final Bus eventBus;

    public UnicornEventBus() {
        this.eventBus = new Bus(EventBusConstants.MAIN_EVENT_THREAD_ID);
    }

    public void register(Object object) {
        eventBus.register(object);
    }

    public void post(UnicornEvent event) {
        eventBus.post(event);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
