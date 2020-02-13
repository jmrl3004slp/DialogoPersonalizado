package com.itslp.desarrolloaplicacionesmoviles;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class DialogoAcceso extends DialogFragment {

    public DialogoAcceso newInstance(int title) {
        DialogoAcceso frag = new DialogoAcceso();
        Bundle args = new Bundle();
        args.putInt("title", title);
        frag.setArguments(args);
        return frag;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        //Inflar y establecer el layout para el dialogo
        builder.setView(inflater.inflate(R.layout.dialogo_login, null))
                .setPositiveButton(android.R.string.yes,  new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        TextView tv = getActivity().findViewById(R.id.txtSaludo);
                        EditText etnombre = getActivity().findViewById(R.id.etNombre);
                        EditText etPassword = getActivity().findViewById(R.id.etPassword);

                        String password = etPassword.getText().toString();
                        if (password.equals("3004")) {
                            tv.setText("======= Hola:  " + etnombre.getText());
                        }
                        else {
                            //Toast.makeText(getContext(), "Error: PASSWORD INCORRECTO", Toast.LENGTH_LONG);
                            final AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                            builder1.setTitle("AVISO");
                            builder1.setMessage("El password es incorrecto");
                            builder1.setCancelable(false);
                            builder1.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    //new DialogoAcceso().show(getFragmentManager(), "DialogoAcceso");
                                    Intent intent = new Intent(getActivity(), MainActivity.class);
                                }
                            });

                            AlertDialog alertDialog = builder1.create();
                            alertDialog.show();
                        }
                    }
                })

                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        getActivity().finish();
                    }
                });

        return builder.create();
    }
}