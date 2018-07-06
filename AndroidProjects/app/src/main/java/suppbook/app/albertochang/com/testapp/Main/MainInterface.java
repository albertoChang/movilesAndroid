package suppbook.app.albertochang.com.testapp.Main;

public interface MainInterface {

    interface View{
        void loginValidations();
        void loginSuccess();
    }

    interface Presenter{
        void validateLogin(String email,String password);
        void validateRegister(String email, String password);

        void auth();
    }

    interface Model{
        void performLogin(String email,String password);
        void performRegister(String email, String password);

        boolean auth();
    }
}
