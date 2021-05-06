package com.limadawai.bukutelpon;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class InsertUpdate extends AppCompatActivity {

    EditText nama, telpon, email;
    ImageView img;
    Button btnSimpan;
    String nm, tlp, em;
    DatabaseHelper dbh = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_update);

        nama = findViewById(R.id.etNama);
        telpon = findViewById(R.id.etTelpon);
        email = findViewById(R.id.etEmail);
        img = findViewById(R.id.foto);
        btnSimpan = findViewById(R.id.btnSubmit);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
            }
        });

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nm = nama.getText().toString();
                tlp = telpon.getText().toString();
                em = email.getText().toString();

                Bitmap bm = ((BitmapDrawable) img.getDrawable()).getBitmap();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] b = baos.toByteArray();
                String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);

                boolean hasil = dbh.insertKontak(encodedImage, nm, tlp, em);
                if (hasil) {
                    System.out.println("Berhasil");
                    Toast.makeText(InsertUpdate.this, "Insert data sukses!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(InsertUpdate.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(InsertUpdate.this, "Insert data gagal!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && null != data)
            switch (requestCode) {
                case 1:
                    Uri uri = data.getData();
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(
                                InsertUpdate.this.getContentResolver(), uri);
                        img.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
            }

    }

}