
import com.google.inject.AbstractModule;
import de.unicorn.android_template.mvp_example.app.event.UnicornEvent;
import de.unicorn.android_template.mvp_example.app.event.UnicornEventBus;
import de.unicorn.android_template.mvp_example.app.model.service.UserService;
import de.unicorn.android_template.mvp_example.app.presenter.MainPresenter;
import de.unicorn.android_template.mvp_example.app.presenter.dto.LoginDTO;
import de.unicorn.android_template.mvp_example.app.presenter.dto.LoginResultDTO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import roboguice.RoboGuice;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Lukas Tibursky
 * @since 17.02.16
 */
@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner.class)
public class MainPresenterTest {

    protected MainPresenter mainPresenter;
    protected UserService userServiceMock = mock(UserService.class);
    protected UnicornEventBus unicornEventBusMock = mock(UnicornEventBus.class);
    protected MainPresenter.LoginView loginViewMock = mock(MainPresenter.LoginView.class);

    @Before
    public void setup() {
        // Override the default RoboGuice module
        RoboGuice.overrideApplicationInjector(RuntimeEnvironment.application, new MyTestModule());
        mainPresenter = new MainPresenter(
            RuntimeEnvironment.application.getApplicationContext(), loginViewMock);

    }

    @After
    public void teardown() {
        // Don't forget to tear down our custom injector to avoid polluting other test classes
        RoboGuice.Util.reset();
    }

    @Test
    public void login_shouldCallTheServiceMethod() {
        LoginDTO loginDTO = new LoginDTO("", "");
        when(userServiceMock.login(any(LoginDTO.class))).thenReturn(new LoginResultDTO(true, ""));

        mainPresenter.login(loginDTO);
        verify(userServiceMock).login(loginDTO);
    }

    @Test
    public void login_shouldCallTheRightViewCallBack() {
        LoginDTO loginDTO = new LoginDTO("", "");

        // we assume that the login was right
        when(userServiceMock.login(any(LoginDTO.class))).thenReturn(new LoginResultDTO(true, ""));

        mainPresenter.login(loginDTO);

        // and verify that the success-callback was called
        verify(loginViewMock).loginOnSuccess();

        String errMessage = "err";

        // now we assume that the login was wrong
        when(userServiceMock.login(any(LoginDTO.class)))
            .thenReturn(new LoginResultDTO(false, errMessage));

        mainPresenter.login(loginDTO);

        // and verify that the error-callback was called
        verify(loginViewMock).loginOnError(errMessage);
    }

    @Test
    public void login_shouldCallTheEventBusMethod() {
        LoginDTO loginDTO = new LoginDTO("", "");
        when(userServiceMock.login(any(LoginDTO.class))).thenReturn(new LoginResultDTO(true, ""));

        mainPresenter.login(loginDTO);
        verify(unicornEventBusMock).post(any(UnicornEvent.class));
    }

    public class MyTestModule extends AbstractModule {
        @Override
        protected void configure() {
            bind(UnicornEventBus.class).toInstance(unicornEventBusMock);
            bind(UserService.class).toInstance(userServiceMock);
        }
    }
}