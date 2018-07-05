package suppbook.app.albertochang.com.testapp.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import suppbook.app.albertochang.com.testapp.Interface.MainInterface;
import suppbook.app.albertochang.com.testapp.Presenter.MainPresenter;
import suppbook.app.albertochang.com.testapp.R;

public class MainActivity extends AppCompatActivity implements MainInterface.View{

    Button buttonRegister, buttonSignin;
    EditText editTextEmail, editTextPass;

    //FirebaseAuth.AuthStateListener mAuthListener;

    private MainInterface.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setviews();
        presenter = new MainPresenter(this);
        setlisteners();
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
        Intent intent = new Intent(MainActivity.this, PrincipalActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void loginError() {
        Toast.makeText(MainActivity.this, "error en los parametros", Toast.LENGTH_SHORT).show();
    }
}
