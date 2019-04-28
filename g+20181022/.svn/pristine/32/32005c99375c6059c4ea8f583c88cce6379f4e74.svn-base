package com.code.clkj.menggong.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.code.clkj.menggong.R;
import com.code.clkj.menggong.base.config.BaseUriConfig;
import com.code.clkj.menggong.response.RespQueryMallGoodsDetail;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by 92573 on 2018/1/5.
 */

/**
 * 商品详情--评论图片adapter
 */
public class GoodsDetailCommentImgAdapter extends ListBaseAdapter<RespQueryMallGoodsDetail.ResultBean.CommentBeanX.CommentBean.ImgListBean> {
    public GoodsDetailCommentImgAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.goods_detail_img_item;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        RespQueryMallGoodsDetail.ResultBean.CommentBeanX.CommentBean.ImgListBean data = getDataList().get(position);
        ImageView goods_detail_comment_img = holder.getView(R.id.goods_detail_comment_img);
        ImageLoader.getInstance().displayImage(BaseUriConfig.makeImageUrl(data.getImg()),goods_detail_comment_img);

    }
}
