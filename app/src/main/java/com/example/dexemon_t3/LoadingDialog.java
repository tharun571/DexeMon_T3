package com.example.dexemon_t3;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

public class LoadingDialog {

    Activity activity;
    AlertDialog alertDialog;


   public LoadingDialog(Activity a){
        activity=a;
    }

   public void startLoading(){


        AlertDialog.Builder builder=new AlertDialog.Builder(activity);

        LayoutInflater inflater=activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog,null));
        builder.setCancelable(false);

        alertDialog=builder.create();
        alertDialog.show();
    }


    public void stopLoading(){

        alertDialog.dismiss();


    }
}
