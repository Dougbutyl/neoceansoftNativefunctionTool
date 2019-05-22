package com.neocean.app.neoceansoftnativefunction;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * User weixn
 * Date 2019/5/22
 */
public class RcAdapter extends RecyclerView.Adapter<RcAdapter.RcViewHolder> {
    int[] titles = {R.string.app_sms, R.string.app_dial, R.string.app_picture, R.string.app_share, R.string.app_web};
    int[] imgs = {R.mipmap.sms, R.mipmap.dial, R.mipmap.picture, R.mipmap.share, R.mipmap.web};
    Context mContext;
    OnItemClick mOnItemClick;

    public RcAdapter(OnItemClick onItemClick) {
        mOnItemClick = onItemClick;
    }

    @Override
    public RcViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.rcadapter_item, parent, false);
        return new RcViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RcViewHolder holder, final int position) {
        holder.mTextView.setText(mContext.getResources().getString(titles[position]));
        Drawable mDrawable = mContext.getResources().getDrawable(imgs[position]);
        mDrawable.setBounds(0, 0, mDrawable.getMinimumWidth(), mDrawable.getMinimumHeight());
        holder.mTextView.setCompoundDrawables(null, mDrawable, null, null);
        holder.mRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnItemClick!=null)
                    mOnItemClick.onItemClik(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return titles.length;
    }

    class RcViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout mRelativeLayout;
        TextView mTextView;

        public RcViewHolder(View itemView) {
            super(itemView);
            mRelativeLayout = (RelativeLayout) itemView.findViewById(R.id.rl);
            mTextView = (TextView) itemView.findViewById(R.id.tv);
        }
    }

    public interface OnItemClick{
        void onItemClik(int position);
    }
}
