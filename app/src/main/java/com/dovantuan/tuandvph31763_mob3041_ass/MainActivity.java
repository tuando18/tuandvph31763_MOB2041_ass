package com.dovantuan.tuandvph31763_mob3041_ass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.dovantuan.tuandvph31763_mob3041_ass.DAO.ThuThuDAO;
import com.dovantuan.tuandvph31763_mob3041_ass.Fragment.AddUserFragment;
import com.dovantuan.tuandvph31763_mob3041_ass.Fragment.DoanhThuFragment;
import com.dovantuan.tuandvph31763_mob3041_ass.Fragment.DoiPassFragment;
import com.dovantuan.tuandvph31763_mob3041_ass.Fragment.LoaiSachFragment;
import com.dovantuan.tuandvph31763_mob3041_ass.Fragment.PhieuMuonFragment;
import com.dovantuan.tuandvph31763_mob3041_ass.Fragment.ThanhVienFragment;
import com.dovantuan.tuandvph31763_mob3041_ass.Fragment.SachFragment;
import com.dovantuan.tuandvph31763_mob3041_ass.Fragment.TopFragment;
import com.dovantuan.tuandvph31763_mob3041_ass.Model.ThuThu;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView nav;
    TextView edtUser;
    View mheaderView;
    ThuThuDAO thuThuDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.navigationDrawerA);
        toolbar = findViewById(R.id.toolbar);
        nav = findViewById(R.id.nav_view);

        setSupportActionBar(toolbar);// gán toolbar
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        nav.setItemIconTintList(null);
        replaceFragment(new PhieuMuonFragment());

        mheaderView = nav.getHeaderView(0);
        edtUser = mheaderView.findViewById(R.id.txtUser);

        SharedPreferences sharedPreferences = getSharedPreferences("ThongTin", MODE_PRIVATE);
        String maTT = sharedPreferences.getString("maTT", "");

        thuThuDAO = new ThuThuDAO(this);
        ThuThu thuThu = thuThuDAO.getID(maTT);
        String username = thuThu.getHoTen();
        edtUser.setText("Welcome " +username+ " !");

        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.nav_PhieuMuon) {
                    PhieuMuonFragment phieuMuonFragment = new PhieuMuonFragment();
                    replaceFrg(phieuMuonFragment);
                } else if (item.getItemId() == R.id.nav_LoaiSach) {
                    LoaiSachFragment loaiSachFragment = new LoaiSachFragment();
                    replaceFrg(loaiSachFragment);
                } else if (item.getItemId() == R.id.nav_Sach) {
                    SachFragment sachFragment = new SachFragment();
                    replaceFrg(sachFragment);
                } else if (item.getItemId() == R.id.nav_TopMuon) {
                    TopFragment top10Fragment = new TopFragment();
                    replaceFrg(top10Fragment);
                } else if (item.getItemId() == R.id.nav_DoanhThu) {
                    DoanhThuFragment doanhThuFragment = new DoanhThuFragment();
                    replaceFrg(doanhThuFragment);
                } else if (item.getItemId() == R.id.nav_ThemThanhVien) {
                    AddUserFragment addUserFragment = new AddUserFragment();
                    replaceFrg(addUserFragment);
                } else if (item.getItemId() == R.id.nav_DoiMatKhau) {
                    DoiPassFragment changePassFragment = new DoiPassFragment();
                    replaceFrg(changePassFragment);
                } else if (item.getItemId() == R.id.nav_ThanhVien) {
                    ThanhVienFragment thanhVienFragment = new ThanhVienFragment();
                    replaceFrg(thanhVienFragment);
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Cảnh báo");
                    builder.setIcon(R.drawable.thongbao);
                    builder.setMessage("Bạn có muốn đăng xuất không?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(MainActivity.this, "Đăng xuất thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.show();
                }
                getSupportActionBar().setTitle(item.getTitle()); //khi click vào item hiển thị tieu de lên toolbar
                drawerLayout.close(); //đóng nav
                return true;
            }
        });

        // hiện thị chwusc năng admin
        SharedPreferences sharedPreferences1 = getSharedPreferences("ThongTin", MODE_PRIVATE);
        String loaiTK = sharedPreferences1.getString("loaiTaiKhoan", "");
        int loaiTKInt = Integer.parseInt(loaiTK);

        if (loaiTKInt != 0) {
            Menu menu = nav.getMenu();
            menu.findItem(R.id.nav_ThemThanhVien).setVisible(false);
        }

    }

    public void replaceFrg(Fragment frg) {
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.frag_content, frg).commit();
    }
    void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frag_content, fragment);
        setTitle("Quản lý phiếu mượn");
        transaction.commit();
    }
}