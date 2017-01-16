package history.six.com.litepalsqltest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.litepal.tablemanager.Connector;

import history.six.com.litepalsqltest.bean.Book;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn= (Button) findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book book=new Book();
                book.setAuthor("武帅");
                book.setName("我的litepal");
                book.setPages(232);
                book.setPrice(23.12);

                book.save();

                Connector.getDatabase();
            }
        });
    }
}
