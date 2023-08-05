package com.sp.hiltdemo.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.lifecycle.ViewModelProvider;

import com.sp.hiltdemo.R;
import com.sp.hiltdemo.network.ApiResponse;
import com.sp.hiltdemo.ui.model.MainViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    AppCompatTextView result;
    ProgressBar progressBar;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressIndicator);
        result = findViewById(R.id.result);
        observeData();
        callAPI();
    }

    private void callAPI() {
        viewModel.getDataList();
    }

    private void observeData() {
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.getResponse().observe(this, new androidx.lifecycle.Observer<ApiResponse>() {
            @Override
            public void onChanged(ApiResponse apiResponse) {
                consumeResponse(apiResponse, "list");
            }
        });
    }


    private void consumeResponse(ApiResponse apiResponse, String tag) {
        switch (apiResponse.status) {
            case LOADING:
                progressBar.setVisibility(View.VISIBLE);
                result.setText("");

                break;

            case SUCCESS:
                progressBar.setVisibility(View.GONE);
                if (!apiResponse.data.isJsonNull()) {
                    Log.e("response:", apiResponse.data.toString());
                    if (tag.equalsIgnoreCase("list")) {
                        result.setText("Result: " + apiResponse.data.toString());
//                        GetSavedLocationResponse responseModel = new Gson().fromJson(apiResponse.data.toString(), GetSavedLocationResponse.class);
                    }
                }

                break;
            case ERROR:
                result.setText("");

                progressBar.setVisibility(View.GONE);
                break;
            default:
                progressBar.setVisibility(View.GONE);
                result.setText("");

                break;
        }
    }

}