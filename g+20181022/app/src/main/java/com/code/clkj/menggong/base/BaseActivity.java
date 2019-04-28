package com.code.clkj.menggong.base;


import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.code.clkj.menggong.R;
import com.code.clkj.menggong.util.TempDataUtils;
import com.lf.tempcore.tempActivity.TempActivity;

import butterknife.Bind;

/**
 * Created by caoyang on 2017/9/4.
 */

public abstract class BaseActivity extends TempActivity {

    @Bind(R.id.toolbar_search_address_iv)
    ImageView toolbar_search_address_iv;//消息图片
    @Bind(R.id.toolbar_search_address_tv)
    TextView toolbar_search_address_tv;//定位图片
    @Bind(R.id.toolbar_search_ed)
    EditText toolbar_search_ed;//搜索编辑
    @Bind(R.id.toolbar_search_address_gr)
    ImageView toolbar_search_address_gr;//首页右方组群
    @Bind(R.id.toolbar_search_layout)
    LinearLayout toolbar_search_layout;//搜索
    @Bind(R.id.toolbar_mess_iv)
    ImageView toolbar_mess_iv;//消息图片
    @Bind(R.id.toolbar_mess_num)
    TextView toolbar_mess_num;//消息数量
    @Bind(R.id.toolbar_mess_layout)
    RelativeLayout toolbar_mess_layout;//消息视图
    @Bind(R.id.toolbar_back)
    ImageView toolbar_back;//返回
    @Bind(R.id.toolbar_title)
    TextView toolbar_title;//标题
    @Bind(R.id.toolbar_right_fj)
    ImageView toolbar_right_fj;//附近图标
    @Bind(R.id.toolbar_right_setting)
    ImageView toolbar_right_setting;//设置图标
    @Bind(R.id.toolbar_normal_w)
    FrameLayout toolbar_normal_w;//通用 返回+标题
    @Bind(R.id.toolbar_top)
    Toolbar toolbar_top;//整个toolbar

    private int toobar_status = ToolbarStatus.FRAGE_NORMAL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * @param toobar_status ToolbarStatus.NORMAL
     */
    protected void setToolbar(int toobar_status, String title) {
        if (this.toobar_status == toobar_status||this.toolbar_top==null) {
            return;
        }
        this.toobar_status = toobar_status;
        initToolbar();
        switch (toobar_status) {
            case ToolbarStatus.FRAGE_NORMAL:
                toolbar_title.setVisibility(View.VISIBLE);
                break;
            case ToolbarStatus.FRAGE_HOME_SREARCH:
                toolbar_search_address_gr.setVisibility(View.VISIBLE);
                toolbar_search_address_iv.setVisibility(View.VISIBLE);
                toolbar_search_address_tv.setVisibility(View.VISIBLE);
                toolbar_right_setting.setVisibility(View.VISIBLE);
                break;
            case ToolbarStatus.FRAGE_NORMAL_NEARBY:
                toolbar_back.setVisibility(View.VISIBLE);
                toolbar_right_fj.setVisibility(View.VISIBLE);
                toolbar_title.setVisibility(View.VISIBLE);
                toolbar_title.setText(title);
                break;
            case ToolbarStatus.FRAGE_NORMAL_SETTING:
                toolbar_right_setting.setVisibility(View.VISIBLE);
                toolbar_mess_layout.setVisibility(View.VISIBLE);
                toolbar_mess_num.setVisibility(View.GONE);
                toolbar_title.setVisibility(View.VISIBLE);
                toolbar_title.setText(title);
                break;


        }
    }

    /**
     * @param toobar_status ToolbarStatus.NORMAL
     */
    protected void setToolbar(int toobar_status) {
        setToolbar(toobar_status, "");
    }

    protected void initToolbar() {

        if (toobar_status <= ToolbarStatus.FRAGE_NORMAL_SETTING) {
            toolbar_normal_w.setVisibility(View.VISIBLE);
            toolbar_search_layout.setVisibility(View.GONE);
            for (int i = 0; i < toolbar_normal_w.getChildCount(); i++) {
                View view = toolbar_normal_w.getChildAt(i);
                view.setVisibility(View.GONE);
            }

        } else {
            toolbar_normal_w.setVisibility(View.GONE);
            toolbar_search_layout.setVisibility(View.VISIBLE);
        }
    }

    public static class ToolbarStatus {

        public static final int FRAGE_NORMAL = 1;
        public static final int FRAGE_NORMAL_NEARBY = 3;
        public static final int FRAGE_NORMAL_SETTING = 4;
        public static final int FRAGE_HOME_SREARCH = 5;//首页直播

    }

    /**
     * 设置消息数量
     *
     * @param messNum
     */
    public void setMessageNum(String messNum) {
        int num = TempDataUtils.string2Int(messNum);
        if (num == 0) {
            toolbar_mess_num.setVisibility(View.GONE);
        } else {
            toolbar_mess_num.setVisibility(View.VISIBLE);
            toolbar_mess_num.setText(messNum);
        }

    }
}
