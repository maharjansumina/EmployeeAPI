package com.softwarica.employeeapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.softwarica.employeeapi.API.EmployeeAPI;
import com.softwarica.employeeapi.model.Employee;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.softwarica.employeeapi.url.URL.base_url;

public class SearchEmployeeActivity extends AppCompatActivity {

    EditText etSearch;
    Button btnSearch;
    TextView tvOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_employee);

        etSearch = findViewById(R.id.etSearch);
        tvOutput = findViewById(R.id.tvOutput);
        btnSearch = findViewById(R.id.btnSearch);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etSearch.getText())){
                    etSearch.setError("Please enter employee id");
                  return;
                }

                loadData();
            }
        });
    }

    private void loadData() {

        Retrofit retrofit = new  Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        EmployeeAPI employeeAPI = retrofit.create(EmployeeAPI.class);

        Call<Employee> listCall = employeeAPI.getEmployeeByID(Integer.parseInt(etSearch.getText().toString()));

        listCall.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(SearchEmployeeActivity.this, "Error Code" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
               // Toast.makeText(SearchEmployeeActivity.this, response.body().toString(), Toast.LENGTH_SHORT).show();
                String context = "";
                context += "Id :" + response.body().getId() + "\n";
                context += "Name :" + response.body().getEmployee_name() + "\n";
                context += "Age :" + response.body().getEmployee_age() + "\n";
                context += "Salary :" + response.body().getEmployee_salary() + "\n";

                tvOutput.setText(context);

            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {
                Toast.makeText(SearchEmployeeActivity.this, "Error", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
