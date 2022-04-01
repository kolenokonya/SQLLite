package com.example.sqllite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name, phone, dateOfBirth;
    Button insert, select, update, delete;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.txtName);
        phone = findViewById(R.id.txtNumber);
        dateOfBirth = findViewById(R.id.txtDate);
        insert = findViewById(R.id.btnInsert);
        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDelete);
        select = findViewById(R.id.btnSelect);
        databaseHelper = new DatabaseHelper(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean checkInsertData = databaseHelper
                        .insert(name.getText().toString().trim(), phone.getText().toString().trim(), dateOfBirth.getText().toString().trim());
                if(checkInsertData) Toast.makeText(getApplicationContext(), "Данные добавлены", Toast.LENGTH_SHORT).show();
                else Toast.makeText(getApplicationContext(), "Ошибка", Toast.LENGTH_SHORT).show();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean checkInsertData = databaseHelper
                        .update(name.getText().toString().trim(), phone.getText().toString().trim(), dateOfBirth.getText().toString().trim());
                if(checkInsertData) Toast.makeText(getApplicationContext(), "Данные изменены", Toast.LENGTH_SHORT).show();
                else Toast.makeText(getApplicationContext(), "Ошибка", Toast.LENGTH_SHORT).show();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean checkInsertData = databaseHelper
                        .delete(name.getText().toString().trim());
                if(checkInsertData) Toast.makeText(getApplicationContext(), "Данные удалены", Toast.LENGTH_SHORT).show();
                else Toast.makeText(getApplicationContext(), "Ошибка", Toast.LENGTH_SHORT).show();
            }
        });

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = databaseHelper.getData();
                if(res.getCount()==0) {
                    Toast.makeText(MainActivity.this, "Нет данных", Toast.LENGTH_SHORT).show();
                    return;
                }

                StringBuilder builder = new StringBuilder();
                while (res.moveToNext()){
                    builder.append("Имя: ").append(res.getString(0)).append("\n");
                    builder.append("Тел. номер: ").append(res.getString(1)).append("\n");
                    builder.append("Дата рождения: ").append(res.getString(2)).append("\n\n");
                }

                AlertDialog.Builder builder1 =new AlertDialog.Builder(MainActivity.this);
                builder1.setCancelable(true);
                builder1.setTitle("Данные пользователей");
                builder1.setMessage(builder.toString());
                builder1.show();
            }
        });
    }
}
