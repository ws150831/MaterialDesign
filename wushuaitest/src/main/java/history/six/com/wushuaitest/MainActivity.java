package history.six.com.wushuaitest;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import history.six.com.wushuaitest.bean.User;
import history.six.com.wushuaitest.presenter.LoginPresenter;
import history.six.com.wushuaitest.view.LoginViewPresenter;

public class MainActivity extends AppCompatActivity implements LoginViewPresenter
{


    private Button btnClear,btnLogin;
    private EditText etUserName,etPassword;
    private ProgressBar progressBar;

    private LoginPresenter loginPresenter=new LoginPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //初始化控件源
        initView();


    }

    private void initView()
    {

        btnClear= (Button) findViewById(R.id.btn_clear);
        btnLogin= (Button) findViewById(R.id.btn_login);

        etUserName= (EditText) findViewById(R.id.et_username);
        etPassword= (EditText) findViewById(R.id.et_password);

        progressBar= (ProgressBar) findViewById(R.id.progressBar);

        btnLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                loginPresenter.login();
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                loginPresenter.clear();
            }
        });

    }

    @Override
    public String getUserName() {
        return etUserName.getText().toString();
    }

    @Override
    public String getPassword() {
        return etPassword.getText().toString();
    }

    @Override
    public void clearUserName() {
        etUserName.setText("");
    }

    @Override
    public void clearPassword() {
        etPassword.setText("");
    }

    @Override
    public void hideLoading() {

        progressBar.setVisibility(View.GONE);
    }


    @Override
    public void showLoading() {


        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void LoginSuccess(User user) {

        Toast.makeText(this, "恭喜你登录成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void LoginFailed() {
        Toast.makeText(this, "登录失败,很抱歉,再来一次", Toast.LENGTH_SHORT).show();
    }
}
