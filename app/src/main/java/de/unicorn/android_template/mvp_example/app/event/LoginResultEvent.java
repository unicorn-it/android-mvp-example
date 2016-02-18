package de.unicorn.android_template.mvp_example.app.event;

import com.google.common.collect.Lists;
import de.unicorn.android_template.mvp_example.app.presenter.dto.LoginResultDTO;

import java.util.List;

/**
 * @author Lukas Tibursky
 * @since 15.02.16
 *
 * Example implementation of an UnicornEvent. When implementing listeners, you will have
 * to use the actual implementation and not the interface.
 */
public class LoginResultEvent implements UnicornEvent<LoginResultDTO> {

    private LoginResultDTO loginResultDTO;

    public LoginResultEvent(LoginResultDTO loginResultDTO) {
        this.loginResultDTO = loginResultDTO;
    }

    @Override
    public List<LoginResultDTO> getResults() {
        return Lists.newArrayList(loginResultDTO);
    }
}
