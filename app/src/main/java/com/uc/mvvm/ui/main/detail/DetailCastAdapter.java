package com.uc.mvvm.ui.main.detail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.uc.mvvm.R;
import com.uc.mvvm.model.Cast;
import com.uc.mvvm.util.Constants;

import java.util.List;

public class DetailCastAdapter extends RecyclerView.Adapter<DetailCastAdapter.ViewHolder> {

    private Context context;
    private List<Cast> castData;

    public DetailCastAdapter(Context context) {
        this.context = context;
    }

    public void setCastData(List<Cast> castData) {
        this.castData = castData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cast_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Cast cast = castData.get(position);
        Glide.with(context)
                .load(Constants.BASE_IMAGE_URL + cast.getImg_url())
                .into(holder.cast_img);
        holder.cast_name.setText(cast.getName());
        holder.cast_role.setText(cast.getRole());
    }

    @Override
    public int getItemCount() {
        return castData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView cast_name, cast_role;
        ImageView cast_img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cast_img = itemView.findViewById(R.id.img_cast);
            cast_name = itemView.findViewById(R.id.lbl_name);
            cast_role = itemView.findViewById(R.id.lbl_role);
        }
    }
}