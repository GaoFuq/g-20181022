package com.code.clkj.menggong.adapter;

import android.support.annotation.ColorInt;
import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * ViewHolder基类
 */
public class SuperViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> views;

    public SuperViewHolder(View itemView) {
        super(itemView);
        this.views = new SparseArray<>();
    }

    @SuppressWarnings("unchecked")
    public <T extends View> T getView(int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }
    public void setText(@IdRes int id,String text){
        if (id!=0&&text!=null){
            ((TextView) this.getView(id)).setText(text);
        }
    }
    public void setText(@IdRes int id,String... text){
        ((TextView) this.getView(id)).setText("");
        if (id!=0&&text!=null){
            for (String s:text) {
                if (s!=null)
                ((TextView) this.getView(id)).append(s);
            }

        }
    }
    public void setTextColor(@IdRes int id,@ColorInt int color){
        if (id!=0&&color!=0){
            ((TextView) this.getView(id)).setTextColor(color);
        }
    }

    public void setChecked(@IdRes int id, boolean isCheck) {
        if (id!=0){
            ((CheckBox) this.getView(id)).setChecked(isCheck);
        }
    }

    public void setImageResource(@IdRes int id, int imgid) {
        if (id!=0){
        ((ImageView) this.getView(id)).setImageResource(imgid);
    }

    }
}
