package history.six.com.tablayouttest;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private String[] mTitles = new String[] {"  三   行  ","  私   语  ","  私   语  ","  私   语  ","  私   语  ","  私   语  "
            ,"  私   语  "
            ,"  私   语  "};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        initViews();
    }

    private void initViews() {
        mTabLayout = (TabLayout)findViewById(R.id.id_tablayout);
        mViewPager = (ViewPager)findViewById(R.id.id_viewpager);

        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return new FestivalFragment();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mTitles[position];
            }

            @Override
            public int getCount() {
                return mTitles.length;
            }
        });
        mTabLayout.setupWithViewPager(mViewPager);

    }
}
