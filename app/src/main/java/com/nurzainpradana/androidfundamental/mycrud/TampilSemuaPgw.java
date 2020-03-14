package com.nurzainpradana.androidfundamental.mycrud;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.nurzainpradana.androidfundamental.mycrud.api.Configuration;
import com.nurzainpradana.androidfundamental.mycrud.network.RequestHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class TampilSemuaPgw extends AppCompatActivity implements ListView.OnItemClickListener{
    private ListView lvEmp;
    private String JSON_STRING;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_semua_pgw);

        lvEmp = findViewById(R.id.lv_emp);
        lvEmp.setOnItemClickListener(this);
        getJSON();
    }

    private void showEmployee() {
        JSONObject jsonObject = null;
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Configuration.TAG_JSON_ARRAY);

            for (int i = 0; i < result.length(); i++) {
                JSONObject jo = result.getJSONObject(i);
                String id = jo.getString(Configuration.TAG_ID);
                String name = jo.getString(Configuration.TAG_NAMA);

                HashMap<String, String> employees = new HashMap<>();
                employees.put(Configuration.TAG_ID, id);
                employees.put(Configuration.TAG_NAMA, name);
                list.add(employees);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(TampilSemuaPgw.this, list, R.layout.item_list, new String[] {Configuration.TAG_ID, Configuration.TAG_NAMA}, new int[]{R.id.tv_id, R.id.tv_name});
        lvEmp.setAdapter(adapter);
    }

    private void getJSON() {
        class GetJSON extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TampilSemuaPgw.this, "Mengambil Data ...", "Mohon Tunggu ...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showEmployee();
            }

            @Override
            protected String doInBackground(Void... voids) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(Configuration.URL_GET_ALL);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, TampilPgw.class);
        HashMap<String, String> map = (HashMap) parent.getItemAtPosition(position);
        String empId = map.get(Configuration.TAG_ID).toString();
        intent.putExtra(Configuration.EMP_ID, empId);
        startActivity(intent);
    }
}
