package com.example.recycleviewexample;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerContactAdapter adapter;
    ArrayList<ContactModel> arrContact = new ArrayList<>();
    FloatingActionButton btnOpenDialog;
    LinearLayout llRow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerContact);
        btnOpenDialog = findViewById(R.id.btnOpenDialog);

        btnOpenDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.add_dialog_box);

                EditText edtName = dialog.findViewById(R.id.edtTxtName);
                EditText edtNumber = dialog.findViewById(R.id.edtTxtNumber);
                Button btnAction = dialog.findViewById(R.id.btnAction);

                btnAction.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String name = "", number = "";
                        if(!edtName.getText().toString().equals("")){
                            name = edtName.getText().toString();
                        }else{
                            Toast.makeText(MainActivity.this, "Please Enter the Contact Name!", Toast.LENGTH_SHORT).show();
                        }

                        if (!edtNumber.getText().toString().equals("")){
                            number = edtNumber.getText().toString();
                        }else{
                            Toast.makeText(MainActivity.this, "Please Enter the Contact Number!", Toast.LENGTH_SHORT).show();
                        }


                        arrContact.add(new ContactModel(R.drawable.c,name,number));
                        adapter.notifyItemInserted(arrContact.size()-1);

                        recyclerView.scrollToPosition(arrContact.size()-1);

                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));// used to set the layout to recycle view

//        ContactModel model = new ContactModel(R.drawable.a, "Aditya","7906288429");
//        arrContact.add(model);


        arrContact.add(new ContactModel(R.drawable.a, "A","7453972132"));
        arrContact.add(new ContactModel(R.drawable.c, "B","7453972132"));
        arrContact.add(new ContactModel(R.drawable.d, "C","7451972132"));
        arrContact.add(new ContactModel(R.drawable.e, "D","7451972132"));
        arrContact.add(new ContactModel(R.drawable.f, "E","7453972132"));
        arrContact.add(new ContactModel(R.drawable.g, "F","7453972132"));
        arrContact.add(new ContactModel(R.drawable.h, "G","7453972132"));
        arrContact.add(new ContactModel(R.drawable.i, "H","7453972132"));
        arrContact.add(new ContactModel(R.drawable.a, "I","7453972132"));
        arrContact.add(new ContactModel(R.drawable.a, "J","7453972132"));
        arrContact.add(new ContactModel(R.drawable.c, "K","7453972132"));
        arrContact.add(new ContactModel(R.drawable.d, "L","7451972132"));
        arrContact.add(new ContactModel(R.drawable.e, "M","7451972132"));
        arrContact.add(new ContactModel(R.drawable.f, "N","7453972132"));
        arrContact.add(new ContactModel(R.drawable.g, "O","7453972132"));
        arrContact.add(new ContactModel(R.drawable.h, "P","7453972132"));
        arrContact.add(new ContactModel(R.drawable.i, "Q","7453972132"));
        arrContact.add(new ContactModel(R.drawable.a, "R","7453972132"));

       adapter = new RecyclerContactAdapter(this,arrContact);
        recyclerView.setAdapter(adapter);

    }
}