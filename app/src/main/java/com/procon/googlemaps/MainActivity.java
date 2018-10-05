package com.procon.googlemaps;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.et_address)
    EditText mETAddress;
    @BindView(R.id.bt_search)
    Button mBTSearch;
    @BindView(R.id.tv_result)
    TextView mTVResult;
    @BindView(R.id.pb_status)
    ProgressBar mPBStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mBTSearch.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_search:
                onSearchButtonClick(mETAddress.getText().toString());
                break;
        }
    }

    private void onSearchButtonClick(String address) {
        mPBStatus.setVisibility(View.VISIBLE);
        mTVResult.setText("");
        Utility.getAddressResponseModel(this, address, new IGetAddressInfo() {
            @Override
            public void onResponse(double latitude, double longitude) {
                mPBStatus.setVisibility(View.GONE);
                mTVResult.setText("Latitude and longitude is "+latitude+", "+longitude);
            }
        });
    }
}
