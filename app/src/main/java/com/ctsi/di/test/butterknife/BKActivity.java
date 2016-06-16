package com.ctsi.di.test.butterknife;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import com.ctsi.di.test.R;

import butterknife.BindColor;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import butterknife.OnItemClick;
import butterknife.OnItemLongClick;
import butterknife.OnItemSelected;

/**
 * Created by doulala on 16/5/4.
 */
public class BKActivity extends Activity {


    @BindView(R.id.txv)
    TextView txv;

    @BindView(R.id.btn)
    Button btn;

    @BindColor(R.color.colorPrimary)
    int color;

    @BindString(R.string.app_name)
    String app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bk);
        ButterKnife.bind(this);
        txv.setText(app);
        txv.setTextColor(color);

    }


}
