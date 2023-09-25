package com.dovantuan.tuandvph31763_mob3041_ass.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dovantuan.tuandvph31763_mob3041_ass.Adapter.PhieuMuonAdapter;
import com.dovantuan.tuandvph31763_mob3041_ass.DAO.PhieuMuonDAO;
import com.dovantuan.tuandvph31763_mob3041_ass.Model.PhieuMuon;
import com.dovantuan.tuandvph31763_mob3041_ass.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Frag_QLPhieuMuon extends Fragment {
    FloatingActionButton fab;
    RecyclerView rc_cv;
    PhieuMuonDAO phieuMuonDAO;
    PhieuMuonAdapter phieuMuonAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_qlphieumuon, container, false);

        fab = view.findViewById(R.id.floatAdd);
        rc_cv = view.findViewById(R.id.rc_cv);

        phieuMuonDAO = new PhieuMuonDAO(getContext());
//        ArrayList<PhieuMuon> list = phieuMuonDAO.getAll();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rc_cv.setLayoutManager(layoutManager);
//        phieuMuonAdapter = new PhieuMuonAdapter(list, getContext());
        rc_cv.setAdapter(phieuMuonAdapter);

        return view;
    }
}
