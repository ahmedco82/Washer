package com.ahmedibrahim.waher.models;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by cca on 06/11/2018.
 */

public class SetterGetterProducts {

    public int sounds_id;
    public int bground_id;
    public int levels_id;
    public boolean switch_id;
    public String db_name;

    public int P1;
    public int P2;
    public int P3;
    public int P4;
    public int P5;
    public int P6;
    public int P7;

    SharedPreferences SetterGetterProducts;
    SharedPreferences.Editor prefEditor;
    Context context;

    public SetterGetterProducts(Context context){
        this.context = context;
        SetterGetterProducts = context.getSharedPreferences("appname",0);
    }
    public void setP1(int p1){
        this.P1 = p1;
        prefEditor = SetterGetterProducts.edit();
        prefEditor.putInt("setP1", p1);
        prefEditor.commit();
    }
    public void setP2(int p2) {
        this.P2 = p2;
        prefEditor = SetterGetterProducts.edit();
        prefEditor.putInt("setP2", p2);
        prefEditor.commit();
    }
    public void setP3(int p3) {
        this.P3 = p3;
        prefEditor = SetterGetterProducts.edit();
        prefEditor.putInt("setP3", p3);
        prefEditor.commit();
    }


    public boolean saveArray(String[] array, String arrayName, Context mContext) {

        SharedPreferences prefs =  context.getSharedPreferences("appname",0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(arrayName,array.length);
        for(int i=0;i<array.length;i++)
         editor.putString(arrayName + "" +i,array[i]);
        return editor.commit();
    }



    public String[] loadArray(String arrayName, Context mContext) {
        SharedPreferences prefs =  context.getSharedPreferences("appname",0);
        int size = prefs.getInt(arrayName + "size", 0);
        String array[] = new String[size];
        for(int i=0;i<size;i++)
        array[i] = String.valueOf(prefs.getInt(arrayName,0));
        return array;
    }




    public void setP4(int p4) {
        this.P4 = p4;
        prefEditor = SetterGetterProducts.edit();
        prefEditor.putInt("setP4", p4);
        prefEditor.commit();
    }
    public void setP5(int p5) {
        this.P5 = p5;
        prefEditor = SetterGetterProducts.edit();
        prefEditor.putInt("setP5", p5);
        prefEditor.commit();
    }
    public void setP6(int p6) {
        this.P6 = p6;
        prefEditor = SetterGetterProducts.edit();
        prefEditor.putInt("setP6", p6);
        prefEditor.commit();
    }
    public void setP7(int p7) {
        this.P7 = p7;
        prefEditor = SetterGetterProducts.edit();
        prefEditor.putInt("setP7", p7);
        prefEditor.commit();
    }
    public int getP1(){
        return SetterGetterProducts.getInt("setP1",0);
    }
    public int getP2(){
        return SetterGetterProducts.getInt("setP2",0);
    }
    public int getP3(){
        return SetterGetterProducts.getInt("setP3",0);
    }
    public int getP4(){
        return SetterGetterProducts.getInt("setP4",0);
    }
    public int getP5(){
        return SetterGetterProducts.getInt("setP5",0);
    }
    public int getP6(){
        return SetterGetterProducts.getInt("setP6",0);
    }
    public int getP7(){
        return SetterGetterProducts.getInt("setP7",0);
    }
}



    /*
    public void setSounds_id(int sounds_id){
        this.sounds_id = sounds_id;
        prefEditor = SetterGetterPrefs.edit();
        prefEditor.putInt("setSounds", sounds_id);
        prefEditor.commit();
    }
    public void setBground_id(int bground_id) {
        this.bground_id = bground_id;
        prefEditor = SetterGetterPrefs.edit();
        prefEditor.putInt("setBground", bground_id);
        prefEditor.commit();
    }
    public void setLevels_id(int levels_id) {
        this.levels_id = levels_id;
        prefEditor = SetterGetterPrefs.edit();
        prefEditor.putInt("setLevels", levels_id);
        prefEditor.commit();
    }
    public void setSwitch_id(boolean switch_id) {
        this.switch_id = switch_id;
        prefEditor = SetterGetterPrefs.edit();
        prefEditor.putBoolean("setSwitch", switch_id);
        prefEditor.commit();
    }
    public void setDb_name(String db_name) {
        this.db_name = db_name;
        prefEditor = SetterGetterPrefs.edit();
        prefEditor.putString("db_Name", db_name);
        prefEditor.commit();
    }
*/
