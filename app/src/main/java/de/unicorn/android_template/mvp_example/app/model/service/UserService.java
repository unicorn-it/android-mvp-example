package de.unicorn.android_template.mvp_example.app.model.service;

import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import de.unicorn.android_template.mvp_example.app.presenter.dto.LoginDTO;
import de.unicorn.android_template.mvp_example.app.presenter.dto.LoginResultDTO;
import roboguice.service.RoboService;


/**
 * @author Lukas Tibursky
 * @since 15.02.16
 *
 * Example implementation of a RoboService. RoboServices
 * can be injected in any activity, fragment, service
 */
public class UserService extends RoboService {

    private static final String DUMMY_LOGIN_EMAIL = "unicorn@example.com";
    private static final String DUMMY_LOGIN_PASSWORD = "pw";

    /**
     * Example implementation of a login. Here we should call the backend
     *
     * @param loginDTO LoginDTO
     * @return LoginResultDTO
     */
    public LoginResultDTO login(LoginDTO loginDTO) {

        if (loginDTO.getUsername().equals(DUMMY_LOGIN_EMAIL)
            && loginDTO.getPassword().equals(DUMMY_LOGIN_PASSWORD)) {
            return new LoginResultDTO(true, "worked!");
        }
        return new LoginResultDTO(false, "Login failed! Email or password was wrong!");

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
