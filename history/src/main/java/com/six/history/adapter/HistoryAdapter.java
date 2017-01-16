package com.six.history.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.six.history.DetailsActivity;
import com.six.history.R;
import com.six.history.bean.HistoryStatus;

import java.util.List;

/**
 * Created by Administrator on 2016/12/17/017.
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> implements View.OnClickListener
{

    private Context context;
    private List<HistoryStatus.ResultBean> result;

    private AnimalAdapter.OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public HistoryAdapter(Context context, List<HistoryStatus.ResultBean> result) {
        this.context = context;
        this.result = result;
    }
    /**
     * 设置监听
     */
    public interface OnItemClickLitener{
        void onItemClick(View view, int positon);
        void onItemLongClick(View view, int position);
    }

    private OnItemClickLitener onItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener onItemClickLitener){
        this.onItemClickLitener=onItemClickLitener;
    }





    @Override
    public HistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {

        View view= LayoutInflater.from(context).inflate(R.layout.history_item,parent,false);
       HistoryViewHolder historyViewHolder=new HistoryViewHolder(view);

        //将创建的View注册点击事件
        view.setOnClickListener(this);
        return historyViewHolder;
    }

    @Override
    public void onBindViewHolder(final HistoryViewHolder holder, int position)
    {
        holder.itemView.setTag(result.get(position).getE_id());


        //如果设置回调，则设置点击事件
        if(onItemClickLitener!=null){
            //设置点击
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos=holder.getLayoutPosition();
                    onItemClickLitener.onItemClick(holder.itemView,pos);
                }
            });
            //长按事件
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener()
            {

                @Override
                public boolean onLongClick(View view) {
                    int pos = holder.getLayoutPosition();
                    onItemClickLitener.onItemLongClick(holder.itemView, pos);
                    return false;
                }
            });
        }



        holder.tvTime.setText(result.get(position).getDate());
        holder.tvContent.setText(result.get(position).getTitle());

        //将数据保存在itemView的Tag中，以便点击时进行获取
//        holder.itemView.setTag("http://ww3.sinaimg.cn/large/610dc034jw1fasakfvqe1j20u00mhgn2.jpg");
    }

    @Override
    public int getItemCount()
    {
        return result.size();
    }



    class HistoryViewHolder extends RecyclerView.ViewHolder
    {
        private TextView tvTime,tvContent;
        CardView cardView;
        public HistoryViewHolder(View itemView)
        {


            super(itemView);


            tvTime= (TextView) itemView.findViewById(R.id.tv_time);
            tvContent= (TextView) itemView.findViewById(R.id.tv_content);


        }
    }

    @Override
    public void onClick(View view) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取数据
            mOnItemClickListener.onItemClick(view,view.getTag().toString());
        }

    }

}
