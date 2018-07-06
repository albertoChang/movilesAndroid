package suppbook.app.albertochang.com.testapp.Main;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;

import suppbook.app.albertochang.com.testapp.Model.User;

public class MainModel extends AppCompatActivity implements MainInterface.Model {

    private FirebaseAuth mAuth;
    private String node = "Users";
    private SharedPreferences settings;
    private SharedPreferences.Editor editor;

    public MainModel(){
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void performLogin(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(MainModel.this, task -> {
            if (!task.isSuccessful()) {
                Toast.makeText(MainModel.this, "ERROR EN LOS PARAMETROS", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void performRegister(String email, String password) {

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(MainModel.this, task -> {
            if (!task.isSuccessful()) {
                Toast.makeText(MainModel.this, "ERROR EN LOS PARAMETROS", Toast.LENGTH_SHORT).show();
            } else {
                String user_id = mAuth.getCurrentUser().getUid();
                DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child(node);

                User user = new User(email,user_id);
                current_user_db.push().setValue(user);
            }
        });
    }

    @Override
    public boolean auth() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String token = activateFirebaseMessaging();

            String userId = user.getUid();

            DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child(node).child(userId).child("token");
            ref.setValue(token);

            Toast.makeText(MainModel.this, "llegué al model", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    private String activateFirebaseMessaging() {
        FirebaseMessaging.getInstance().setAutoInitEnabled(true);

        settings = this.getSharedPreferences("TestApp", MODE_PRIVATE);
        editor = settings.edit();

        return settings.getString("token", "");

    }


}
