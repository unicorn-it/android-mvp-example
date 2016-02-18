package de.unicorn.android_template.mvp_example.app.presenter.dto;

/**
 * @author Lukas Tibursky
 * @since 16.02.16
 */
public class LoginDTO {

    private String username;
    private String password;

    public LoginDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
