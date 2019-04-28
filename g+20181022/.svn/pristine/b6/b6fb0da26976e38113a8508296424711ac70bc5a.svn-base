package com.code.clkj.menggong.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by caoyang on 2017/7/20.
 */

public class NetworkImageHolderView implements Holder<Integer> {
    private SimpleDraweeView imageView;
    @Override
    public View createView(Context context) {
        imageView = new SimpleDraweeView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }

    @Override
    public void UpdateUI(Context context, int position, Integer data) {
        imageView.setImageResource(data);
    }

   /* @Override
    public void UpdateUI(Context context, int position, ResponseUserSideIndex.ResultEntity.BannerEntity data) {
        imageView.setImageURI(BaseUriConfig.makeImageUrl(BaseUriConfig.BASE_IMG_URL+data.getLikjImage()));
    }*/
}
