package com.dovantuan.tuandvph31763_mob3041_ass.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.dovantuan.tuandvph31763_mob3041_ass.DataBase.DbHelper;
import com.dovantuan.tuandvph31763_mob3041_ass.Model.ThuThu;

import java.util.ArrayList;
import java.util.List;

public class ThuThuDAO {
    DbHelper dbHelper;
    SQLiteDatabase db;

    public ThuThuDAO(Context context) {
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(ThuThu obj) {
        ContentValues values = new ContentValues();
        values.put("maTT", obj.getMaTT());
        values.put("hoTen", obj.getHoTen());
        values.put("matKhau", obj.getMatKhau());
        return db.insert("ThuThu", null, values);
    }

    public int updatePass(ThuThu obj) {
        ContentValues values = new ContentValues();
        values.put("hoTen", obj.getHoTen());
        values.put("matKhau", obj.getMatKhau());
        return db.update("ThuThu", values, "maTT=?", new String[]{String.valueOf(obj.getMaTT())});
    }

    public int delete(String id) {
        return db.delete("ThuThu", "maTT=?", new String[]{id});
    }

    @SuppressLint("Range")
    public List<ThuThu> getData(String sql, String... selectionArgs) {
        List<ThuThu> list = new ArrayList<ThuThu>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()) {
            ThuThu obj = new ThuThu();
            obj.setMaTT(c.getString(c.getColumnIndex("maTT")));
            obj.setHoTen(c.getString(c.getColumnIndex("hoTen")));
            obj.setMatKhau(c.getString(c.getColumnIndex("matKhau")));
            list.add(obj);
        }
        return list;
    }

    public List<ThuThu> getAll() {
        String sql = "SELECT  * FROM ThuThu";
        return getData(sql);
    }

    public ThuThu getID(String id) {
        String sql = "SELECT * FROM ThuThu WHERE maTT=?";
        List<ThuThu> list = getData(sql, id);
        return list.get(0);
    }

    // đăng nhập
    public boolean checkLogin (String maTT, String matKhau) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM ThuThu WHERE maTT = ? AND matKhau = ?", new String[]{maTT, matKhau});
        if (cursor.getCount() != 0) {
            return true;
        } else {
            return  false;
        }
    }
    //check Login

}
