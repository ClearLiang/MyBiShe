package test.com.livetest.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import test.com.livetest.R;
import test.com.livetest.entity.MyMessage;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> implements View.OnClickListener{
    public List<MyMessage> dataList = null;

    public MyAdapter(List<MyMessage> dataList) {
        this.dataList = dataList;
    }

    /**
     * RecyclerView没有ListView的item点击事件
     * 模拟setItemOnClickListener()
     * */
    private OnItemClickListener mOnItemClickListener = null;
    public void setOnItemClickListener(OnItemClickListener listener){
        this.mOnItemClickListener = listener;
    }
    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }
    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            mOnItemClickListener.onItemClick(v,(int)v.getTag());
        }
    }
    /**
     * 根据viewType的不同，选择不同的布局
     * 聊天界面左、右气泡选择
     * */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case MyMessage.TYPE_SEND:
                View viewLeft = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_left,parent,false);
                ViewHolder vhSend = new ViewHolder(viewLeft);
                viewLeft.setOnClickListener(this);
                return vhSend;
            case MyMessage.TYPE_RECEIVE:
                View viewRight = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_right,parent,false);
                ViewHolder vhReceive = new ViewHolder(viewRight);
                viewRight.setOnClickListener(this);
                return vhReceive;
        }
        return null;
    }
    /**
     * 根据消息的接收还是发送，返回不同的viewType
     * */
    @Override
    public int getItemViewType(int position) {
        switch (dataList.get(position).getType()){
            case MyMessage.TYPE_SEND:
                return MyMessage.TYPE_SEND;
            case MyMessage.TYPE_RECEIVE:
                return MyMessage.TYPE_RECEIVE;
            default:
                return MyMessage.TYPE_SEND;
        }
    }

    /**
    * 将数据与界面进行绑定的操作
    * @param holder 获取的item的句柄
    * @param position item的位置
    * */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText(dataList.get(position).getContent());
        holder.mImageView.setImageResource(R.drawable.icon_warning);
        holder.itemView.setTag(position);
    }

    /**
     * 获取数据的数量
     * */
    @Override
    public int getItemCount() {
        return dataList.size();
    }

    /**
     * 自定义的ViewHolder，持有每个Item的的所有界面元素
     * */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;
        ImageView mImageView;
        ViewHolder(View view){
            super(view);
            mTextView = (TextView) view.findViewById(R.id.tv_im_message);
            mImageView = (ImageView) view.findViewById(R.id.iv_im_icon);
        }
    }



}
