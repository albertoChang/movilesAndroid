package suppbook.app.albertochang.com.testapp.Main;

import android.text.TextUtils;

import suppbook.app.albertochang.com.testapp.Main.MainInterface;
import suppbook.app.albertochang.com.testapp.Main.MainModel;

public class MainPresenter implements MainInterface.Presenter{

    private MainInterface.View view;
    private MainInterface.Model model;

    public MainPresenter(MainInterface.View view){
        this.view = view;
        model = new MainModel();
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
    public void validateRegister(String email, String password) {
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
            view.loginValidations();
        }else{
            model.performRegister(email,password);
        }
    }

    @Override
    public void auth() {
        if (model.auth()){
            view.loginSuccess();
        }
    }
}
