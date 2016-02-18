package de.unicorn.android_template.mvp_example.app.presenter.dto;

/**
 * @author Lukas Tibursky
 * @since 16.02.16
 */
public class LoginResultDTO {
    private boolean success;
    private String message;

    public LoginResultDTO(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

}
