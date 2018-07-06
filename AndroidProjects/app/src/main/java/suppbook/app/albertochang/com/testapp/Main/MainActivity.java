package suppbook.app.albertochang.com.testapp.Main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import suppbook.app.albertochang.com.testapp.R;
import suppbook.app.albertochang.com.testapp.Home.PrincipalActivity;

public class MainActivity extends AppCompatActivity implements MainInterface.View{

    Button buttonRegister, buttonSignin;
    EditText editTextEmail, editTextPass;

    FirebaseAuth.AuthStateListener mAuthListener;

    private MainInterface.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setviews();
        presenter = new MainPresenter(this);
        setlisteners();
        mAuthListener = firebaseAuth -> {
            presenter.auth();
        };
    }

    private void setviews(){
        setContentView(R.layout.activity_main);

        buttonRegister = (Button) findViewById(R.id.registration);
        buttonSignin =  (Button) findViewById(R.id.login);

        editTextEmail = (EditText) findViewById(R.id.email);
        editTextPass = (EditText) findViewById(R.id.password);
    }

    private void setlisteners(){
        buttonRegister.setOnClickListener((View v) -> {
            final String email = editTextEmail.getText().toString();
            final String password = editTextPass.getText().toString();
            presenter.validateRegister(email,password);
        });

        buttonSignin.setOnClickListener((View v) -> {
            final String email = editTextEmail.getText().toString();
            final String password = editTextPass.getText().toString();
            presenter.validateLogin(email,password);
        });
    }

    @Override
    public void loginValidations() {
        Toast.makeText(MainActivity.this, "No se ha ingresado parametros", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginSuccess() {
        Toast.makeText(MainActivity.this, "voy a cambiar de pestaña", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, PrincipalActivity.class);
        startActivity(intent);
        finish();
    }
}
