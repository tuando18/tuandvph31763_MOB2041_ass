package com.dovantuan.tuandvph31763_mob3041_ass.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dovantuan.tuandvph31763_mob3041_ass.Model.PhieuMuon;
import com.dovantuan.tuandvph31763_mob3041_ass.R;

import java.util.ArrayList;

public class PhieuMuonAdapter extends RecyclerView.Adapter<PhieuMuonAdapter.ViewHolderCV> {

    ArrayList<PhieuMuon> list;
    Context context;

    public PhieuMuonAdapter(ArrayList<PhieuMuon> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderCV onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View v = inflater.inflate(R.layout.item_rc_phieumuon, parent, false);
        ViewHolderCV holder = new ViewHolderCV(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCV holder, int position) {
        holder.tvMaPhieu.setText("Mã phiếu: "+list.get(position).getMaPM());
        holder.tvThanhVien.setText("Thành viên: "+list.get(position).getTenTV());
        holder.tvTenSach.setText("Tên sách: "+list.get(position).getTenSach());
        holder.tvTienThue.setText("Tiền thuê: "+list.get(position).getTienThue());

        String trangThai = "";
        if (list.get(position).getTraSach() == 1) {
            trangThai = "Đã trả sách";
        } else {
            trangThai = "Chưa trả sách";
        }
        holder.tvTrangThai.setText(trangThai);
        holder.tvNgayThue.setText("Ngày: "+list.get(position).getNgay());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolderCV extends RecyclerView.ViewHolder {
        TextView tvMaPhieu, tvThanhVien, tvTenSach, tvTienThue, tvTrangThai, tvNgayThue;
        ImageView btn_sua,btn_xoa;
        LinearLayout btn_xemchitiet;

        public ViewHolderCV(@NonNull View itemView) {
            super(itemView);
            tvMaPhieu = itemView.findViewById(R.id.tvMaPhieu);
            tvThanhVien = itemView.findViewById(R.id.tvThanhVien);
            tvTenSach = itemView.findViewById(R.id.tvTenSach);
            tvTienThue = itemView.findViewById(R.id.tvTienThue);
            tvTrangThai = itemView.findViewById(R.id.tvTrangThai);
            tvNgayThue = itemView.findViewById(R.id.tvNgayThue);

            btn_xoa = itemView.findViewById(R.id.btn_remove);
            btn_xemchitiet = itemView.findViewById(R.id.linear1);
        }
    }
}
