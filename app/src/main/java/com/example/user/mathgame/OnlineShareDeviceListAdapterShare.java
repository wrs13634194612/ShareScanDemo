package com.example.user.mathgame;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import java.util.HashMap;
import java.util.List;

/**
 * online devices
 * Created by Administrator on 2016/10/25.
 */
public class OnlineShareDeviceListAdapterShare extends BaseRecyclerShareViewAdapter<OnlineShareDeviceListAdapterShare.ViewHolder> {
    private List<Device> mDevices;
    private Context mContext;
    private HashMap<String,Integer> map;

    public OnlineShareDeviceListAdapterShare(Context context, List<Device> devices, HashMap<String,Integer> map) {
        this.mContext = context;
        this.mDevices = devices;
        this.map = map;
    }

    public void resetDevices(List<Device>devices) {
        this.mDevices = devices;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_online_share_device, null, false);
        ViewHolder holder = new ViewHolder(itemView);
        holder.tv_name = itemView.findViewById(R.id.tv_name);
        holder.img_icon = itemView.findViewById(R.id.img_icon);
        holder.cb_client = itemView.findViewById(R.id.cb_client);
        return holder;
    }

    @Override
    public int getItemCount() {
        return mDevices == null ? 0 : mDevices.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);
        holder.tv_name.setText(mDevices.get(position).getEquipmentNote());
        holder.img_icon.setImageResource(map.get(mDevices.get(position).getProductId()));
        // holder.cb_client.setChecked(group.selected);
        // holder.cb_client.setEnabled(enable);
        holder.cb_client.setChecked(mDevices.get(position).isAdapterChecked());
        Log.e("TAG","if_adapter:"+position+"\t"+mDevices.get(position).isAdapterChecked());
        holder.cb_client.setEnabled(true);
        holder.cb_client.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //  listener.itemOnClick(103,position);
                onViewClickListener.onViewClick(view, position);
            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder {


        public ImageView img_icon;
        public TextView tv_name;
        public CheckBox cb_client;
        public RelativeLayout rl_share_all;
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
