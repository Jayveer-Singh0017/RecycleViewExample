package com.example.recycleviewexample;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerContactAdapter extends RecyclerView.Adapter<RecyclerContactAdapter.ViewHolder> {

    Context context;
    ArrayList<ContactModel> arrContact;
    RecyclerContactAdapter(Context context, ArrayList<ContactModel> arrContact){
        this.context = context;
        this.arrContact = arrContact;
    }

    private  int lastPosition = -1;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.contact_row,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

       holder.imgContact.setImageResource(arrContact.get(position).img);
       holder.txtName.setText(arrContact.get(position).name);
       holder.txtNumber.setText(arrContact.get(position).number);

       setAnimation(holder.itemView, position);


        holder.llRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(context);

                dialog.setContentView(R.layout.add_dialog_box);


                EditText edtName = dialog.findViewById(R.id.edtTxtName);
                EditText edtNumber = dialog.findViewById(R.id.edtTxtNumber);
                Button btnAction = dialog.findViewById(R.id.btnAction);

                TextView txtTitle = dialog.findViewById(R.id.txtTitle);
                txtTitle.setText("Update Contact");
                btnAction.setText("Update");

                edtName.setText(arrContact.get(position).name);
                edtNumber.setText(arrContact.get(position).number);

                btnAction.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String name = "", number = "";
                        if(!edtName.getText().toString().equals("")){
                            name = edtName.getText().toString();
                        }else{
                            Toast.makeText(context, "Please Enter the Contact Name!", Toast.LENGTH_SHORT).show();
                        }

                        if (!edtNumber.getText().toString().equals("")){
                            number = edtNumber.getText().toString();
                        }else{
                            Toast.makeText(context, "Please Enter the Contact Number!", Toast.LENGTH_SHORT).show();
                        }

                        arrContact.set(position,new ContactModel(arrContact.get(position).img,name,number));
                        notifyItemChanged(position);

                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        holder.llRow.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Delete Contact")
                        .setMessage("Are you sure want to delete ?")
                        .setIcon(R.drawable.baseline_delete_24)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                               arrContact.remove(position);
                               notifyItemRemoved(position);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                builder.show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrContact.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtNumber;
        ImageView imgContact;
        LinearLayout llRow;

        public ViewHolder(View itemView){
            super(itemView);

            txtName = itemView.findViewById(R.id.txtName);
            txtNumber = itemView.findViewById(R.id.txtNumber);
            imgContact = itemView.findViewById(R.id.imgContact);
            llRow = itemView.findViewById(R.id.ll_row);

        }

    }

    private void setAnimation(View viewToAnimate, int position){

        if (position > lastPosition) {
            Animation slideIn = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(slideIn);
            lastPosition = position;
        }
    }
}
