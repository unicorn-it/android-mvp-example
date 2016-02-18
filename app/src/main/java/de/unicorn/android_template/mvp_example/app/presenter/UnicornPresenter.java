package de.unicorn.android_template.mvp_example.app.presenter;

import android.content.Context;
import roboguice.RoboGuice;

/**
 * @author Lukas Tibursky
 * @since 17.02.16
 */
public abstract class UnicornPresenter {

    /**
     * To be able to use @Inject for our services, we
     * need to make sure that we have injected the members into the context
     *
     * @param context android.content.Context
     */
    protected UnicornPresenter(Context context) {
        RoboGuice.getInjector(context).injectMembersWithoutViews(this);
    }
}
