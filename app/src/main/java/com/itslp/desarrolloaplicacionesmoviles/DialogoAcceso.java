package com.itslp.desarrolloaplicacionesmoviles;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class DialogoAcceso extends DialogFragment {
    OnSimpleDialogListener listener;
    public interface OnSimpleDialogListener {
        void clickBotonOK();
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
                        EditText etnombre = getDialog().findViewById(R.id.etNombre);
                        EditText etPassword = getDialog().findViewById(R.id.etPassword);

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
                                    listener.clickBotonOK();
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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        listener = (OnSimpleDialogListener) context;
    }
}