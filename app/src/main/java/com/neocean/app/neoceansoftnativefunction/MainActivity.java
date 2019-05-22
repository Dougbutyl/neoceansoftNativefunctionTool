package com.neocean.app.neoceansoftnativefunction;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.neocean.app.neoceansoftnativefunctionutils.NativeFunctionUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RcAdapter.OnItemClick {
    RecyclerView mRecyclerView;
    GridLayoutManager mGridLayoutManager;
    RcAdapter mRcAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }

    void init() {
        mRecyclerView = (RecyclerView) findViewById(R.id.rc);
        mGridLayoutManager = new GridLayoutManager(this, 3);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mRcAdapter = new RcAdapter(this);
        mRecyclerView.setAdapter(mRcAdapter);
    }

    @Override
    public void onItemClik(int position) {
        switch (position) {
            case 0:
                NativeFunctionUtils.sendMessage(MainActivity.this, "10086", "这是发送短信内容");
                break;
            case 1:
                NativeFunctionUtils.toDial(MainActivity.this, "10086");
                break;
            case 2:
                NativeFunctionUtils.toTakePicture(10001, MainActivity.this);
                break;
            case 3://此处只做文字内容分享，图片分享参照方法参数说明
                NativeFunctionUtils.shareText(MainActivity.this, "此处为分享标题", "此处为分享内容");
                break;
            case 4:
                NativeFunctionUtils.toWebView(MainActivity.this, "https://github.com/Dougbutyl/neoceansoftNativefunctionTool");
                break;
        }
    }
}
