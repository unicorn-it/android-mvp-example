package de.unicorn.android_template.mvp_example.app.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.squareup.otto.Subscribe;
import de.unicorn.android_template.mvp_example.app.R;
import de.unicorn.android_template.mvp_example.app.event.LoginResultEvent;
import de.unicorn.android_template.mvp_example.app.event.UnicornEventBus;
import de.unicorn.android_template.mvp_example.app.presenter.MainPresenter;
import de.unicorn.android_template.mvp_example.app.presenter.dto.LoginDTO;
import roboguice.fragment.RoboFragment;
import roboguice.inject.InjectView;

import javax.inject.Inject;

/**
 * The fragment being shown by the MainActivity. This is an example implementation of
 * a fragment using annotations to load view-Elements, using an injected Service and also
 * show how to interact with the eventBus to be able to separate the service-logic from the
 * view.
 */
@SuppressWarnings("BindingAnnotationWithoutInject")
public class LoginActivityFragment extends RoboFragment implements MainPresenter.LoginView {

    @InjectView(R.id.btn_login)
    Button button_login;

    @InjectView(R.id.input_email)
    EditText input_email;

    @InjectView(R.id.input_password)
    EditText input_password;

    @Inject
    UnicornEventBus eventBus;

    MainPresenter mainPresenter;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        eventBus.register(this);
        mainPresenter = new MainPresenter(this.getContext(), this);

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainPresenter.login(new LoginDTO(input_email.getText().toString(),
                    input_password.getText().toString()));
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    /**
     * Example implementation of a listener to certain events.
     * To have other Services etc. being able to catch events, we will have
     * to call eventBus.register(this) and implement a method, annotated with
     * '@Subscribe' and having the Event-Implementation as a parameter of the
     * events we are interested in.
     *
     * @param event LoginResultEvent
     */
    @Subscribe
    public void loginResultEventCaught(LoginResultEvent event) {
        Log.i(this.getClass().getName(),
            "This was being caught by the subscribed listener: " + event.getResults().get(0)
                .getMessage());
    }

    @Override
    public void loginOnError(String errMessage) {
        Toast.makeText(getActivity(), errMessage,
            Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginOnSuccess() {
        Toast.makeText(getActivity(), "Login was successful!",
            Toast.LENGTH_SHORT).show();
    }
}
