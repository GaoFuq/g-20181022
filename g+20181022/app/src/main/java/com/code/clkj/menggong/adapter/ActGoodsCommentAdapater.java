package com.code.clkj.menggong.adapter;

/**
 * Created by 92573 on 2018/1/6.
 */

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.code.clkj.menggong.R;
import com.code.clkj.menggong.base.config.BaseUriConfig;
import com.code.clkj.menggong.response.RespQueryMgooCommentPage;
import com.code.clkj.menggong.util.NullUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 评论详情adapter
 */

public class ActGoodsCommentAdapater extends ListBaseAdapter<RespQueryMgooCommentPage.ResultBean.SourceBean>{
    public ActGoodsCommentAdapater(Context context) {
        super(context);
    }
    private String str;

    @Override
    public int getLayoutId() {
        return R.layout.comment_item;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        RespQueryMgooCommentPage.ResultBean.SourceBean data = getDataList().get(position);
        SimpleDraweeView comment_person_head_img = holder.getView(R.id.comment_person_head_img);
        TextView comment_person_nickname_tx = holder.getView(R.id.comment_person_nickname_tx);
        TextView comment_data_tx = holder.getView(R.id.comment_data_tx);
        TextView comment_content_tx = holder.getView(R.id.comment_content_tx);
        RecyclerView comment_image = holder.getView(R.id.comment_image);

        if (data.getMuseImage() != null) {
//            ImageLoader.getInstance().displayImage(BaseUriConfig.makeImageUrl(data.getMuseImage()),comment_person_head_img);
            comment_person_head_img.setImageURI(BaseUriConfig.makeImageUrl(data.getMuseImage()));
        }
        if (!TextUtils.isEmpty(data.getMuseNickName())) {
            comment_person_nickname_tx.setText(data.getMuseNickName());
        }
        if (!TextUtils.isEmpty(data.getMgcoContentTime())) {
            comment_data_tx.setText(data.getMgcoContentTime());
        }
        if (!TextUtils.isEmpty(data.getContent())) {
            comment_content_tx.setText(data.getContent());
        }

        str = data.getMgcoImage();

        if (NullUtils.isNotEmpty(str)) {
            String[] splits = str.split(",");
            List<String> my_image = new ArrayList<>();
            for (int i = 0; i < splits.length; i++) {
                Collections.addAll(my_image, splits[i]);
            }
            //动态图片
            comment_image.setLayoutManager(new GridLayoutManager(mContext, 3));
            ListBaseAdapter<String> mImageAdapter = new ListBaseAdapter<String>(mContext) {
                @Override
                public int getLayoutId() {
                    return R.layout.myhome_image_item;
                }

                @Override
                public void onBindItemHolder(SuperViewHolder holder, int position) {
                    String iamge_str = getDataList().get(position);
                    ImageView imageView = holder.getView(R.id.my_home_image);
                    Glide.with(mContext).load(BaseUriConfig.makeImageUrl(iamge_str)).into(imageView);
                }
            };
            if (my_image != null && my_image.size() > 0) {
                mImageAdapter.setDataList(my_image);
            } else {
                comment_image.setVisibility(View.GONE);
            }
            comment_image.setAdapter(mImageAdapter);
        }
    }
}
