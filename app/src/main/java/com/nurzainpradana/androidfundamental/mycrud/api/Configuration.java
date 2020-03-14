package com.nurzainpradana.androidfundamental.mycrud.api;

public class Configuration {
    /* dibawah ini merupakan pengalamatan dimana lokasi script CRUD PHP
    di simpan

    pada tutorial kali ini, karena kita membuat localhost, maka alamatnya tertuju ke IP Komputer
    dimana file PHP tersebut berada

    PENTING jangan lupa ganti IP Sesuai dengan IP Komputer dimana data PHP berada
     */

    public static final String URL_ADD = "http://192.168.43.53/android/pegawai/tambahPgw.php";
    public static final String URL_GET_ALL = "http://192.168.43.53/android/pegawai/tampilSemuaPgw.php";
    public static final String URL_GET_EMP = "http://192.168.43.53/android/pegawai/tampilPgw.php?id=";
    public static final String URL_UPDATE_EMP = "http://192.168.43.53/android/pegawai/updatePgw.php";
    public static final String URL_DELETE_EMP = "http://192.168.43.53/android/pegawai/deletePgw.php?id=";

    //Dibawah ini merupakan kunci yang akan digunakan untuk mengirim permintaan ke script PHP
    public static final String KEY_EMP_ID = "id";
    public static final String KEY_EMP_NAMA = "name";
    public static final String KEY_EMP_POSISI = "desg"; //desg itu variabel untuk posisi
    public static final String KEY_EMP_GAJI = "salary"; //salary itu variabel untuk gaji

    //JSON Tags
    public static final String TAG_JSON_ARRAY = "result";
    public static final String TAG_ID = "id";
    public static final String TAG_NAMA = "name";
    public static final String TAG_POSISI = "desg";
    public static final String TAG_GAJI = "salary";

    //ID Karyawan
    //emp itu singkatan dari employee
    public static final String EMP_ID = "emp_id";
}
