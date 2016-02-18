package de.unicorn.android_template.mvp_example.app.event;

import java.util.List;

/**
 * @author Lukas Tibursky
 * @since 15.02.16
 *
 * Interface to always be sure to have a certain structure
 * when posting to the EventBus
 * {@link de.unicorn.android_template.mvp_example.app.event.UnicornEventBus}
 */
public interface UnicornEvent<T> {

    List<T> getResults();
}
