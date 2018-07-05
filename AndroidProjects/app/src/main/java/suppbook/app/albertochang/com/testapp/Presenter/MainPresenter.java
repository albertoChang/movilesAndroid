package suppbook.app.albertochang.com.testapp.Presenter;

import android.text.TextUtils;

import suppbook.app.albertochang.com.testapp.Interface.MainInterface;
import suppbook.app.albertochang.com.testapp.Model.MainModel;

public class MainPresenter implements MainInterface.Presenter{

    private MainInterface.View view;
    private MainInterface.Model model;

    public MainPresenter(MainInterface.View view){
        this.view = view;
        model = new MainModel(this);
    }

    @Override
    public void validateLogin(String email, String password) {
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
            view.loginValidations();
        }else{
            model.performLogin(email,password);
        }
    }

    @Override
    public void paramError() {
        view.loginError();
    }

    @Override
    public void performLogin() {
        view.loginSuccess();
    }

    @Override
    public void validateRegister(String email, String password) {
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
            view.loginValidations();
        }else{
            model.performRegister(email,password);
        }
    }
}
