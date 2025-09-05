package com.mirea.komkovaa.ls3_kr.ui.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.mirea.komkovaa.ls3_kr.R;
import com.mirea.komkovaa.ls3_kr.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

        private EditText EditTextGroup;
        private EditText EditTextNumberOfList;
        private EditText EditTextCinema;
        private Button buttonsave;

        private static final String PREFS_NAME = "KomkovData";
        private static final String KEY_GROUP = "group_number";
        private static final String KEY_LIST_NUMBER = "list_number";
        private static final String KEY_CINEMA = "favorite_cinema";
        private static final String KEY_LAUNCH_COUNT = "launch_count";

        private SharedPreferences sharedPref;

        private int launchCount;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_home, container, false);

            EditTextGroup = view.findViewById(R.id.edittext_group);
            EditTextNumberOfList = view.findViewById(R.id.edittext_number_of_list);
            EditTextCinema = view.findViewById(R.id.edittext_cinema);
            buttonsave = view.findViewById(R.id.buttonsave);

            sharedPref = this.getActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);


            launchCount = sharedPref.getInt(KEY_LAUNCH_COUNT, 0);
            launchCount++;
            sharedPref.edit().putInt(KEY_LAUNCH_COUNT, launchCount).apply();
            if (launchCount > 1) {
                loadPreferences();
            }

            buttonsave.setOnClickListener(v -> {
                savePreferences();
            });
        return view;
    }

        @Override
        public void onStop() {
            super.onStop();

        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            sharedPref.edit().putInt(KEY_LAUNCH_COUNT, 0).apply();
        }

        private void savePreferences() {
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString(KEY_GROUP, EditTextGroup.getText().toString());
            editor.putString(KEY_LIST_NUMBER, EditTextNumberOfList.getText().toString());
            editor.putString(KEY_CINEMA, EditTextCinema.getText().toString());
            editor.apply();
        }

        private void loadPreferences() {
            EditTextGroup.setText(sharedPref.getString(KEY_GROUP, ""));
            EditTextNumberOfList.setText(sharedPref.getString(KEY_LIST_NUMBER, ""));
            EditTextCinema.setText(sharedPref.getString(KEY_CINEMA, ""));
        }
    }