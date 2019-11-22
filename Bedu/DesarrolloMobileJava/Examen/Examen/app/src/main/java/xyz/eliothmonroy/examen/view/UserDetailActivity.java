package xyz.eliothmonroy.examen.view;

import android.os.Build;
import android.os.Bundle;
import android.transition.Explode;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import xyz.eliothmonroy.examen.R;
import xyz.eliothmonroy.examen.data.Data;

public class UserDetailActivity extends AppCompatActivity {

    Data user;
    ImageView userDetailImage;
    TextView userDetailId, userDetailEmail, userDetailFirstName, userDetailLastName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        userDetailImage=findViewById(R.id.userDetailImage);
        userDetailId=findViewById(R.id.userDetailId);
        userDetailEmail=findViewById(R.id.userDetailEmail);
        userDetailFirstName=findViewById(R.id.userDetailFirstName);
        userDetailLastName=findViewById(R.id.userDetailLastName);

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(getString(R.string.title_activity_user_detail));
        }

        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP){
            //Agregamos la transición de entrada
            getWindow().setEnterTransition(new Explode().setDuration(500));
            //Para poner el status bar transparente
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        if(getIntent().getExtras()!=null){
            user=getIntent().getExtras().getParcelable("User");
            Picasso.get().load(user.getAvatar()).into(userDetailImage);
            userDetailId.setText(getString(R.string.user_id_placeholder,String.valueOf(user.getId())));
            userDetailEmail.setText(getString(R.string.user_email_placeholder,user.getEmail()));
            userDetailFirstName.setText(getString(R.string.user_first_name_placeholder,user.getFirst_name()));
            userDetailLastName.setText(getString(R.string.user_last_name_placeholder,user.getLast_name()));
        }

    }
}
