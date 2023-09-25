package com.dovantuan.tuandvph31763_mob3041_ass.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.SimpleDateFormat;
import android.util.Log;

import com.dovantuan.tuandvph31763_mob3041_ass.DataBase.DbHelper;
import com.dovantuan.tuandvph31763_mob3041_ass.Model.PhieuMuon;
import com.dovantuan.tuandvph31763_mob3041_ass.Model.Sach;
import com.dovantuan.tuandvph31763_mob3041_ass.Model.Top;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.SimpleTimeZone;

public class PhieuMuonDAO {
    DbHelper dbHelper;
    SQLiteDatabase db;
    private Context context;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public PhieuMuonDAO(Context context) {
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(PhieuMuon obj) {
        ContentValues values = new ContentValues();
//        maPM integer primary key autoincrement, maTV integer references ThanhVien(maTV), maTT text references ThuThu(maTT), maSach integer references Sach(maSach),ngay date not null,traSach integer not null, tienThue integer not null
        values.put("maTV", obj.getMaTV());
        values.put("maTT", obj.getMaTT());
        values.put("maSach", obj.getMaSach());
        values.put("Ngay", obj.getNgay());
        values.put("traSach", obj.getTraSach());
        values.put("tienThue", obj.getTienThue());

        return db.insert("PhieuMuon", null, values);
    }

    public int updatePM(PhieuMuon obj) {
        ContentValues values = new ContentValues();

        values.put("maTV", obj.getMaTV());
        values.put("maTT", obj.getMaTT());
        values.put("maSach", obj.getMaSach());
        values.put("Ngay", obj.getNgay());
        values.put("traSach", obj.getTraSach());
        values.put("tienThue", obj.getTienThue());

        return db.update("PhieuMuon", values, "maPM=?", new String[]{String.valueOf(obj.getMaPM())});
    }

    public int delete(String id) {
        return db.delete("PhieuMuon", "maPM=?", new String[]{id});
    }

    @SuppressLint("Range")
    public List<PhieuMuon> getData(String sql, String... selectionArgs) {
        List<PhieuMuon> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()) {
            PhieuMuon obj = new PhieuMuon();
            obj.setMaPM(Integer.parseInt(c.getString(c.getColumnIndex("maPM"))));
            obj.setMaTT(c.getString(c.getColumnIndex("maTV")));
            obj.setMaTV(Integer.parseInt(c.getString(c.getColumnIndex("maTT"))));
            obj.setMaSach(Integer.parseInt(c.getString(c.getColumnIndex("maSach"))));
            try {
                obj.setNgay(String.valueOf(sdf.parse(c.getString(c.getColumnIndex("Ngay")))));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            obj.setTraSach(Integer.parseInt(c.getString(c.getColumnIndex("traSach"))));
            obj.setTienThue(Integer.parseInt(c.getString(c.getColumnIndex("tienThue"))));

            list.add(obj);
        }
        return list;
    }

    public List<PhieuMuon> getAll() {
        String sql = "SELECT  * FROM PhieuMuon";
        return getData(sql);
    }

    public PhieuMuon getID(String id) {
        String sql = "SELECT * FROM PhieuMuon WHERE maPM=?";
        List<PhieuMuon> list = getData(sql, id);
        return list.get(0);
    }

    //thong ke top 10
    @SuppressLint("Range")
    public List<Top> getTop() {
        String sqlTop = "SELECT maSach, count (maSach) as soLuong FROM PhieuMuon GROUP BY maSach ORDER BY soLuong DESC LIMIT 10";
        List<Top> list = new ArrayList<Top>();
        SachDAO sachDAO = new SachDAO(context);
        Cursor c = db.rawQuery(sqlTop, null);
        while (c.moveToNext()) {
            Top top = new Top();
            Sach sach = sachDAO.getID(c.getString(c.getColumnIndex("maSach")));
            top.setTenSach(sach.getTenSach());
            top.setSoLuong(Integer.parseInt(c.getString(c.getColumnIndex("soluong"))));
            list.add(top);
        }
        return list;
    }

    @SuppressLint("Range")
    public int getDoanhThu (String tuNgay, String denNgay) {
        String sqlDoanhThu = "SELECT SUM(tienThue) as doanhThu FROM PhieuMuon WHERE Ngay BETWEEN ? AND ?";
        List<Integer> list = new ArrayList<Integer>();
        Cursor c = db.rawQuery(sqlDoanhThu, new String[]{tuNgay, denNgay});
        while (c.moveToNext()) {
            try {
                list.add(Integer.parseInt(c.getString(c.getColumnIndex("doanhThu"))));
            }catch (Exception e) {
                list.add(0);
            }
        }
        return list.get(0);
    }
}
