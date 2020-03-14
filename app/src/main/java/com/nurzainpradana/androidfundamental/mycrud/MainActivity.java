package com.nurzainpradana.androidfundamental.mycrud;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nurzainpradana.androidfundamental.mycrud.api.Configuration;
import com.nurzainpradana.androidfundamental.mycrud.network.RequestHandler;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //dibawah ini merupakan perintah untuk mendefinisikan View
    private EditText edtName, edtDesg, edtSal;
    private Button btnAdd, btnView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inisialisasi dari View
        edtName = findViewById(R.id.edt_name);
        edtDesg = findViewById(R.id.edt_desg);
        edtSal = findViewById(R.id.edt_salary);

        btnAdd = findViewById(R.id.btn_add);
        btnView = findViewById(R.id.btn_view);

        //setting listener to button
        btnAdd.setOnClickListener(this);
        btnView.setOnClickListener(this);
    }

    //dibawah ini merupakan perintah untuk menambahkan pegawai (CREATE)
    private void addEmployee() {
        final String name = edtName.getText().toString().trim();
        final String desg = edtDesg.getText().toString().trim();
        final String sal = edtSal.getText().toString().trim();

        class AddEmployee extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MainActivity.this, "Menambahkan ...", "Tunggu", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String, String> params = new HashMap<>();
                params.put(Configuration.KEY_EMP_NAMA, name);
                params.put(Configuration.KEY_EMP_POSISI, desg);
                params.put(Configuration.KEY_EMP_GAJI, sal);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Configuration.URL_ADD, params);
                return res;

            }
        }

        AddEmployee ae = new AddEmployee();
        ae.execute();
    }

    @Override
    public void onClick(View v) {
        if (v == btnAdd) {
            addEmployee();
        } else if (v == btnView) {
            startActivity(new Intent(this, TampilSemuaPgw.class));
        }
    }
}
