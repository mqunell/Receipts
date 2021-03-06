package com.mattqunell.receipts;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

public class ReceiptActivity extends SingleFragmentActivity {

    ReceiptFragment mReceiptFragment;

    // Tag for Intent extra
    private static final String EXTRA_RECEIPT_ID = "com.mattqunell.receipts";

    // Encapsulates the implementation details of ReceiptActivity's returned Intent
    public static Intent newIntent(Context context, UUID receiptId) {
        Intent intent = new Intent(context, ReceiptActivity.class);
        intent.putExtra(EXTRA_RECEIPT_ID, receiptId);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        UUID receiptId = (UUID) getIntent().getSerializableExtra(EXTRA_RECEIPT_ID);
        mReceiptFragment = ReceiptFragment.newInstance(receiptId);
        return mReceiptFragment;
    }

    // Gives onBackPressed functionality to ReceiptFragment
    @Override
    public void onBackPressed() {
        if (mReceiptFragment != null) {
            mReceiptFragment.onBackPressed();
        }
        else {
            super.onBackPressed();
        }
    }
}
