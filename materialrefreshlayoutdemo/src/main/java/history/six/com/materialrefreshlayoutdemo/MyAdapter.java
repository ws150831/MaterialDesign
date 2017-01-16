package history.six.com.materialrefreshlayoutdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by 若兰 on 2016/1/26.
 * 一个懂得了编程乐趣的小白，希望自己
 * 能够在这个道路上走的很远，也希望自己学习到的
 * 知识可以帮助更多的人,分享就是学习的一种乐趣
 * QQ:1069584784
 * csdn:http://blog.csdn.net/wuyinlei
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    List<Status.ResultsBean> list;


    /**
     * 加载布局的
     */
    private LayoutInflater mInflater;
    //private List<String> mLists;
    private Status status;
    private Context context;

//    public MyAdapter(Status status, Context context) {
//        this.status = status;
//        this.context = context;
//    }
    public MyAdapter( List<Status.ResultsBean> list,Context context){
        this.context = context;
        this.list = list;
    }
//    public List<String> getLists() {
//        return mLists;
//    }

//    public void addLists(List<String> lists) {
//        addLists(0, lists);
//    }
//
//
//    /**
//     * 添加数据
//     * @param position
//     * @param lists
//     */
//    public void addLists(int position, List<String> lists) {
//        //mLists = lists;
//        if (lists != null && lists.size() > 0) {
//            mLists.addAll(lists);
//            notifyItemRangeChanged(position, mLists.size());
//        }
//    }


//    public MyAdapter(List<String> items) {
//        mLists = items;
//    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mInflater = LayoutInflater.from(parent.getContext());
        View view = mInflater.inflate(R.layout.list_item, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position)
    {
        Glide.with(context).load(list.get(position).getUrl()).into(holder.iv_cardview);
    }

    @Override
    public int getItemCount() {
        return list.size();
//        return status.getResults().size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv_cardview;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv_cardview = (ImageView) itemView.findViewById(R.id.recycler_image);



        }
    }


}
