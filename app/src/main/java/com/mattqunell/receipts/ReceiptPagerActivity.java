package com.mattqunell.receipts;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

public class ReceiptPagerActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private List<Receipt> mReceipts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt_pager);

        mViewPager = (ViewPager) findViewById(R.id.receipt_view_pager);
        mReceipts = ReceiptBook.get(this).getReceipts();

        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                return new ReceiptFragment();
            }

            @Override
            public int getCount() {
                return mReceipts.size();
            }
        });

        mViewPager.setCurrentItem(0);
    }
}
