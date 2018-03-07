package example.com.fragmentdelayedloading;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private RadioButton mFstBtn;
    private RadioButton mSndBtn;
    private RadioButton mThdBtn;

    private ViewPager mViewPager;
    private ListFragmentPagerAdapter mPagerAdapter;
    private List<Fragment> mFragments = new ArrayList<Fragment>();

    private final int FIRST_FRAGMENT = 0;
    private final int SECOND_FRAGMENT = 1;
    private final int THIRD_FRAGMENT = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initButton();
        initViewPager();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_rb_fst:
                mViewPager.setCurrentItem(FIRST_FRAGMENT);
                break;

            case R.id.id_rb_snd:
                mViewPager.setCurrentItem(SECOND_FRAGMENT);
                break;

            case R.id.id_rb_thd:
                mViewPager.setCurrentItem(THIRD_FRAGMENT);
                break;
        }
    }

    /**
     * 初始化按钮
     */
    private void initButton() {
        mFstBtn = (RadioButton) findViewById(R.id.id_rb_fst);
        mFstBtn.setOnClickListener(this);
        mSndBtn = (RadioButton) findViewById(R.id.id_rb_snd);
        mSndBtn.setOnClickListener(this);
        mThdBtn = (RadioButton) findViewById(R.id.id_rb_thd);
        mThdBtn.setOnClickListener(this);
    }

    /**
     * 初始化ViewPager控件
     */
    private void initViewPager() {
        mViewPager = (ViewPager) findViewById(R.id.id_vp_viewpager);
        // 关闭预加载，默认一次只加载一个Fragment
        mViewPager.setOffscreenPageLimit(1);
        // 添加Fragment
        mFragments.add(new FirstFragment());
        mFragments.add(new SecondFragment());
        mFragments.add(new ThirdFragment());
        // 适配器
        mPagerAdapter = new ListFragmentPagerAdapter(
                getSupportFragmentManager(), mFragments);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setOnPageChangeListener(onPageChangeListener);
    }

    private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            // 根据用户选中的按钮修改按钮样式
            switch (position) {
                case FIRST_FRAGMENT:
                    mFstBtn.setChecked(true);
                    mSndBtn.setChecked(false);
                    mThdBtn.setChecked(false);
                    break;

                case SECOND_FRAGMENT:
                    mFstBtn.setChecked(false);
                    mSndBtn.setChecked(true);
                    mThdBtn.setChecked(false);
                    break;

                case THIRD_FRAGMENT:
                    mFstBtn.setChecked(false);
                    mSndBtn.setChecked(false);
                    mThdBtn.setChecked(true);
                    break;
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
    };


}
