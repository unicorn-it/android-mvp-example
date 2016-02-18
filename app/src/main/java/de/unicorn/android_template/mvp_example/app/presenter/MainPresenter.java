package de.unicorn.android_template.mvp_example.app.presenter;

import android.content.Context;
import de.unicorn.android_template.mvp_example.app.event.LoginResultEvent;
import de.unicorn.android_template.mvp_example.app.event.UnicornEventBus;
import de.unicorn.android_template.mvp_example.app.model.service.UserService;
import de.unicorn.android_template.mvp_example.app.presenter.dto.LoginDTO;
import de.unicorn.android_template.mvp_example.app.presenter.dto.LoginResultDTO;

import javax.inject.Inject;

/**
 * @author Lukas Tibursky
 * @since 16.02.16
 *
 * Example presenter implementation to separate the business logic from the views.
 * When the method login is being invoked, the presenter does trigger
 * some logic and tells the view to react as it wants to.
 * Like that the different views can react differently. That keeps us the possibility to
 * have f.e. different behaviours for tablet- and mobile-views.
 */
public class MainPresenter extends UnicornPresenter {

    private LoginView view;

    @Inject
    UnicornEventBus eventBus;

    @Inject
    UserService userService;

    public MainPresenter(Context context, LoginView view) {
        super(context);
        this.view = view;
    }

    public void login(LoginDTO loginDTO) {
        LoginResultDTO loginResultDTO = userService.login(loginDTO);
        if (loginResultDTO.isSuccess()) {
            view.loginOnSuccess();
        } else {
            view.loginOnError(loginResultDTO.getMessage());
        }
        //inform observers of LoginResultEvent
        eventBus.post(new LoginResultEvent(loginResultDTO));
    }

    public interface LoginView {
        void loginOnError(String errMessage);

        void loginOnSuccess();
    }
}
