package history.six.com.handlertest;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{

    private int count=5;
    private TextView tv;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            int number= (int) msg.obj;

            tv.setText(number+"秒后跳转");

            if(number==0)
            {
                startActivity(new Intent(MainActivity.this,Main2Activity.class));
            }

        }
    };
    private Message msg;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tv= (TextView) findViewById(R.id.textView);

        msg=Message.obtain();
        msg.obj=count;
        for(int i=0;i<4;i++)
        {

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    count--;
                    handler.sendMessage(msg);
                }
            }, 1000);
        }





    }
}
