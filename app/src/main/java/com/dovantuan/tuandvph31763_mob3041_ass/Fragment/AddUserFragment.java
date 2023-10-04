package com.dovantuan.tuandvph31763_mob3041_ass.Fragment;

import static android.app.ProgressDialog.show;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.dovantuan.tuandvph31763_mob3041_ass.DAO.ThuThuDAO;
import com.dovantuan.tuandvph31763_mob3041_ass.Model.ThuThu;
import com.dovantuan.tuandvph31763_mob3041_ass.R;
import com.google.android.material.textfield.TextInputEditText;

public class AddUserFragment extends Fragment {
    TextInputEditText edUser, edHoTen, edPass, edRePass;
    Button btnSave, btnCancel;
    ThuThuDAO dao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_user, container, false);

        dao = new ThuThuDAO(getActivity());
        edUser = v.findViewById(R.id.edTenDangNhap);
        edHoTen = v.findViewById(R.id.edHoVaTen);
        edPass = v.findViewById(R.id.edMatKhau);
        edRePass = v.findViewById(R.id.edRepassMatKhau);
        btnSave = v.findViewById(R.id.btnLuu);
        btnCancel = v.findViewById(R.id.btnHuy);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edUser.setText("");
                edHoTen.setText("");
                edPass.setText("");
                edRePass.setText("");
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ThuThu thuThu = new ThuThu();
                thuThu.setMaTT(edUser.getText().toString());
                thuThu.setHoTen(edHoTen.getText().toString());
                thuThu.setMatKhau(edPass.getText().toString());
                if (validate() > 0) {
                    if (dao.insert(thuThu) > 0) {
                        Toast.makeText(getActivity(), "Lưu thành công", Toast.LENGTH_SHORT).show();
                        edUser.setText("");
                        edHoTen.setText("");
                        edPass.setText("");
                        edRePass.setText("");
                    } else {
                        Toast.makeText(getActivity(), "Lưu thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        return v;
    }

    public int validate() {
        int check = 1;
        if (edUser.getText().length() == 0 || edHoTen.getText().length() == 0 || edPass.getText().length() == 0
                || edRePass.getText().length() == 0) {
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông ", Toast.LENGTH_SHORT).show();
            check = -1;
        } else {
            String pass = edPass.getText().toString();
            String rePass = edRePass.getText().toString();
            if (!pass.equals(rePass)) {
                Toast.makeText(getContext(), "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }
        return check;
    }
}