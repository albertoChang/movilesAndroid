package suppbook.app.albertochang.com.testapp.Model.objetos.Utils;

import android.content.SharedPreferences;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class InstanceIdService extends FirebaseInstanceIdService {
    SharedPreferences settings;
    SharedPreferences.Editor editor;

    public InstanceIdService() {
        super();
    }


    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();

        String token = FirebaseInstanceId.getInstance().getToken();

        //sends this token to the server
        sendToServer(token);
    }

    private void sendToServer(String token) {
        settings = getApplicationContext().getSharedPreferences("TAXICAB", MODE_PRIVATE);
        editor = settings.edit();
        editor.putString("token", token);
        editor.commit();
    }
}
