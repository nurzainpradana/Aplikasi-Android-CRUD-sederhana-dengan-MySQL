package com.nurzainpradana.androidfundamental.mycrud;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.opengl.ETC1;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nurzainpradana.androidfundamental.mycrud.api.Configuration;
import com.nurzainpradana.androidfundamental.mycrud.network.RequestHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class TampilPgw extends AppCompatActivity implements View.OnClickListener{

    private EditText edtId, edtName, edtDesg, edtSalary;
    private Button btnUpdate, btnDelete;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_pgw);

        Intent intent = getIntent();

        id = intent.getStringExtra(Configuration.EMP_ID);

        edtId = findViewById(R.id.edt_tampil_id);
        edtName = findViewById(R.id.edt_tampil_name);
        edtDesg = findViewById(R.id.edt_tampil_desg);
        edtSalary = findViewById(R.id.edt_tampil_salary);

        btnUpdate = findViewById(R.id.btn_tampil_update);
        btnDelete = findViewById(R.id.btn_tampil_delete);

        btnUpdate.setOnClickListener(this);
        btnDelete.setOnClickListener(this) ;

        edtId.setText(id);

        getEmployee();
    }

    private void getEmployee() {
        class GetEmployee extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TampilPgw.this, "Fetching ...", "Wait ...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showEmployee(s);
            }

            @Override
            protected String doInBackground(Void... voids) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Configuration.URL_GET_EMP, id);
                return s;
            }
        }
        GetEmployee ge = new GetEmployee();
        ge.execute();
    }

    private void showEmployee(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Configuration.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String name = c.getString(Configuration.TAG_NAMA);
            String desg = c.getString(Configuration.TAG_POSISI);
            String sal = c.getString(Configuration.TAG_GAJI);

            edtName.setText(name);
            edtDesg.setText(desg);
            edtSalary.setText(sal);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void updateEmployee() {
        final String name = edtName.getText().toString().trim();
        final String desg = edtDesg.getText().toString().trim();
        final String salary = edtSalary.getText().toString().trim();

        class UpdateEmployee extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TampilPgw.this, "Updating ...", "Wait ...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(TampilPgw.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put(Configuration.KEY_EMP_ID, id);
                hashMap.put(Configuration.KEY_EMP_NAMA, name);
                hashMap.put(Configuration.KEY_EMP_POSISI, desg);
                hashMap.put(Configuration.KEY_EMP_GAJI, salary);

                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(Configuration.URL_UPDATE_EMP, hashMap);
                return s;
            }
        }

        UpdateEmployee ue = new UpdateEmployee();
        ue.execute();
    }

    private void deleteEmployee() {
        class DeleteEmployee extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TampilPgw.this, "Updating ...", "Tunggu", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(TampilPgw.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... voids) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Configuration.URL_DELETE_EMP, id);
                return s;
            }
        }

        DeleteEmployee de = new DeleteEmployee();
        de.execute();
    }

    private void confirmDeleteEmployee() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("apakah kamu yakin ingin menghapus data pegawai ini ?");

        alertDialogBuilder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteEmployee();
                startActivity(new Intent(TampilPgw.this, TampilSemuaPgw.class));
            }
        });
        alertDialogBuilder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void onClick(View v) {
        if (v == btnUpdate) {
            updateEmployee();
        }
        if (v == btnDelete) {
           confirmDeleteEmployee();
        }
    }
}
