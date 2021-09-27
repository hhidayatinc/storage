package com.example.storage;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Environment;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class InternalActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String FILENAME = "percobaan.txt";
    Button buatFile, ubahFile, bacaFile, deleteFile;
    EditText textBaca;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal);
        buatFile = findViewById(R.id.btnBuatFile);
        ubahFile = findViewById(R.id.btnUbahFile);
        bacaFile = findViewById(R.id.btnBacaFile);
        deleteFile = findViewById(R.id.btnHapusFile);
        textBaca = findViewById(R.id.textView);

        buatFile.setOnClickListener(this);
        ubahFile.setOnClickListener(this);
        bacaFile.setOnClickListener(this);
        deleteFile.setOnClickListener(this);
    }

    void buatFile() {
        String isiFile = textBaca.getText().toString();
        File file = new File(getFilesDir(), FILENAME);
        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file, true);
            outputStream.write(isiFile.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void ubahFile() {
        String ubah = textBaca.getText().toString();
        File file = new File(getFilesDir(), FILENAME);
        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file, false);
            outputStream.write(ubah.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Toast.makeText(view.getContext(),"File berhasil diubah",Toast.LENGTH_LONG).show();
    }

    void bacaFile() {
        File sdcard = getFilesDir();
        File file = new File(sdcard, FILENAME);
        if (file.exists()) {
            StringBuilder text = new StringBuilder();
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line = br.readLine();
                while (line != null) {
                    text.append(line);
                    line = br.readLine();
                }
                br.close();
            } catch (IOException e) {
                System.out.println("Error" + e.getMessage());
            }
            textBaca.setText(text.toString());

        }
    }

    void hapusFile() {
        File file = new File(getFilesDir(), FILENAME);
        if (file.exists()) {
            file.delete();
        }
    }

    @Override
    public void onClick(View view) {
        jalankanPerintah(view.getId());
    }

    public void jalankanPerintah(int id) {
        switch (id) {
            case R.id.btnBuatFile:
                buatFile();
                break;
            case R.id.btnBacaFile:
                bacaFile();
                break;
            case R.id.btnUbahFile:
                ubahFile();
                break;
            case R.id.btnHapusFile:
                hapusFile();
                break;
        }
    }
}