package history.six.com.wushuaimvplogindemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import history.six.com.wushuaimvplogindemo.bean.User;
import history.six.com.wushuaimvplogindemo.presenter.UserLoginPresenter;
import history.six.com.wushuaimvplogindemo.view.IUserLoginView;

public class MainActivity extends AppCompatActivity implements IUserLoginView
{

    //声明EditText
    private EditText etUserName,etUserPassword;
    //声明Button
    private Button btnLogin,btnClear;
    //声明加载按钮
    private ProgressBar progressBar;

    private UserLoginPresenter userLoginPresenter=new UserLoginPresenter(this);
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //初始化控件
        initViews();

    }

    private void initViews()
    {



        etUserName= (EditText) findViewById(R.id.et_username);
        etUserPassword= (EditText) findViewById(R.id.et_password);

        btnLogin= (Button) findViewById(R.id.btn_login);
        btnClear= (Button) findViewById(R.id.btn_clear);

        progressBar= (ProgressBar) findViewById(R.id.progressBar);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userLoginPresenter.login();
            }
        });


        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLoginPresenter.clear();
            }
        });
    }

    @Override
    public String getUsername() {
        return etUserName.getText().toString();
    }

    @Override
    public String getUserPassword() {
        return etUserPassword.getText().toString();
    }

    @Override
    public void clearUserName() {

        etUserName.setText("");
    }

    @Override
    public void clearUserPassword() {

        etUserPassword.setText("");
    }

    @Override
    public void showLoading() {

        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {

        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void toMainActivity(User user) {

        Toast.makeText(this, "登陆成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFailedError() {

        Toast.makeText(this, "失败了，下次继续努力啊!", Toast.LENGTH_SHORT).show();
    }
}
