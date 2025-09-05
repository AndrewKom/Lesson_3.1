package com.mirea.komkovaa.ls3_kr.ui.slideshow;

import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.mirea.komkovaa.ls3_kr.R;
import com.mirea.komkovaa.ls3_kr.databinding.FragmentSlideshowBinding;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class SlideshowFragment extends Fragment {

    private EditText NameFile;
    private EditText Citata;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_slideshow, container, false);


        NameFile = view.findViewById(R.id.namef);
        Citata =  view.findViewById(R.id.citata);
        Button buttonSave =  view.findViewById(R.id.buttonsave);
        Button buttonLoad =  view.findViewById(R.id.buttonload);

        buttonSave.setOnClickListener(v -> saveFile());
        buttonLoad.setOnClickListener(v -> loadFile());
        return view;
    }

    private void saveFile() {
        String fileName = NameFile.getText().toString();
        String content = Citata.getText().toString();

        if (fileName.isEmpty()) {
            showToast("Введите название файла");
            return;
        }

        try {
            File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
            if (!path.exists()) path.mkdirs();

            File file = new File(path, fileName);

            try (FileOutputStream fos = new FileOutputStream(file, false)) {
                fos.write(content.getBytes());
                showToast("Файл сохранён: " + file.getAbsolutePath());
            }
        } catch (Exception e) {
            showToast("Ошибка сохранения: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void loadFile() {
        String fileName = NameFile.getText().toString();

        if (fileName.isEmpty()) {
            showToast("Введите название файла");
            return;
        }

        try {
            File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
            File file = new File(path, fileName);

            if (!file.exists()) {
                showToast("Файл не найден");
                return;
            }

            StringBuilder content = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(file), "UTF-8"))) {

                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }
            }

            Citata.setText(content.toString().trim());
            showToast("Данные успешно загружены");

        } catch (Exception e) {
            showToast("Ошибка загрузки: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }
}