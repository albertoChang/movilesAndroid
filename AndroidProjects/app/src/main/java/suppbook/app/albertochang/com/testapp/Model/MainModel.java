package suppbook.app.albertochang.com.testapp.Model;

import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import suppbook.app.albertochang.com.testapp.Interface.MainInterface;

public class MainModel extends AppCompatActivity implements MainInterface.Model {

    MainInterface.Presenter presenter;

    private FirebaseAuth mAuth;
    private String node = "Users";

    public MainModel(MainInterface.Presenter presenter){
        this.presenter = presenter;
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void performLogin(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(MainModel.this, task -> {
            if (!task.isSuccessful()) {
                presenter.paramError();
            } else{
                presenter.performLogin();
            }
        });
    }

    @Override
    public void performRegister(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(MainModel.this, task -> {
            if (!task.isSuccessful()) {
                presenter.paramError();
            } else {
                String user_id = mAuth.getCurrentUser().getUid();
                DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child(node);

                User user = new User(email,user_id);
                current_user_db.push().setValue(user);

                presenter.performLogin();
            }
        });
    }
}
