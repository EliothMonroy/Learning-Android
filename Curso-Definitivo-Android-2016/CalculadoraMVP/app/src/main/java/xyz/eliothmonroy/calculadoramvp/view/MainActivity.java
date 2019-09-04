package xyz.eliothmonroy.calculadoramvp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import xyz.eliothmonroy.calculadoramvp.R;
import xyz.eliothmonroy.calculadoramvp.presenter.MainActivityPresenter;
import xyz.eliothmonroy.calculadoramvp.presenter.MainActivityPresenterImpl;

public class MainActivity extends AppCompatActivity implements MainActivityView {

    private MainActivityPresenter mainActivityPresenter;
    private EditText edtNumber1, edtNumber2;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNumber1=findViewById(R.id.edtNumber1);
        edtNumber2=findViewById(R.id.edtNumber2);
        result=findViewById(R.id.result);
        mainActivityPresenter=new MainActivityPresenterImpl(this);
    }

    public void sumar(View view){
        String number1=edtNumber1.getText().toString();
        String number2=edtNumber2.getText().toString();
        mainActivityPresenter.sumar(number1,number2);
    }

    @Override
    public void showResult(String result) {
        this.result.setText(getString(R.string.result, result));
    }

    @Override
    public void showError(String error) {
        this.result.setText(getString(R.string.error, error));
    }
}
