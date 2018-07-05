package suppbook.app.albertochang.com.testapp.Interface;

public interface MainInterface {

    interface View{
        void loginValidations();
        void loginSuccess();
        void loginError();
    }

    interface Presenter{
        void validateLogin(String email,String password);
        void paramError();
        void performLogin();

        void validateRegister(String email, String password);
    }

    interface Model{
        void performLogin(String email,String password);
        void performRegister(String email, String password);
    }

}
