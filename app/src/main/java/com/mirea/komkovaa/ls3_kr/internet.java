package com.mirea.komkovaa.ls3_kr;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.mirea.komkovaa.ls3_kr.MyWorker;
import com.mirea.komkovaa.ls3_kr.R;

public class internet extends Fragment {

    public internet() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.internet_time, container, false);

        Button button = view.findViewById(R.id.button);
        EditText editText = view.findViewById(R.id.editTextText);

        button.setOnClickListener(v -> {
            if (isInternetAvailable()) {
                editText.setText("Интернет есть!");
                startMyWorker();
            } else {
                editText.setText("Нет интернета!");
            }
        });

        return view;
    }

    private boolean isInternetAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    private void startMyWorker() {
        WorkRequest myWorkRequest = new OneTimeWorkRequest.Builder(MyWorker.class).build();
        WorkManager.getInstance(requireContext()).enqueue(myWorkRequest);
    }
}