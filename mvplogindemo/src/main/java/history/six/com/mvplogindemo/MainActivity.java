package history.six.com.mvplogindemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import history.six.com.mvplogindemo.bean.User;
import history.six.com.mvplogindemo.presenter.LoginViewPresenter;
import history.six.com.mvplogindemo.view.LoginView;

public class MainActivity extends AppCompatActivity  implements LoginView{

    private EditText etUserName,etPassword;
    private Button btnLogin,btnLlear;
    private ProgressBar progressBar;
    private LoginViewPresenter loginviewPresenter=new LoginViewPresenter(this);
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUserName= (EditText) findViewById(R.id.et_username);
        etPassword= (EditText) findViewById(R.id.et_password);

        progressBar= (ProgressBar) findViewById(R.id.progressBar);

        btnLogin= (Button) findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                loginviewPresenter.login();
            }
        });

        btnLlear= (Button) findViewById(R.id.btn_clear);
        btnLlear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginviewPresenter.clear();
            }
        });


    }







    @Override
    public String getUserName()
    {
        return etUserName.getText().toString();
    }

    @Override
    public String getPassword()
    {
        return etPassword.getText().toString();
    }

    @Override
    public void clearUserName()
    {
        etUserName.setText("");
    }

    @Override
    public void clearPassword()
    {
          etPassword.setText("");
    }

    @Override
    public void showLoading()
    {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading()
    {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void Main(User user)
    {
        Toast.makeText(this, "恭喜你，登录成功，再接再厉!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void Failed() {
        Toast.makeText(this, "抱歉失败了，请重新开始", Toast.LENGTH_SHORT).show();
    }
}
