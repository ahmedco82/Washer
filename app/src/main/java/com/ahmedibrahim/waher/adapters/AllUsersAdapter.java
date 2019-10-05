package com.ahmedibrahim.waher.adapters;

/**
 * Created by cca on 11/06/2018.
 */
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahmedibrahim.waher.R;
import com.ahmedibrahim.waher.models.ProductRecyclerViewItem;

import java.util.ArrayList;

public class AllUsersAdapter extends RecyclerView.Adapter {

    public static AllUsersAdapter instance;

    private Context mContext;
    public int totP[];
    public static int[]add_count;

     ArrayList<ProductRecyclerViewItem> userList;

    public AllUsersAdapter(Context mContext, ArrayList<ProductRecyclerViewItem>userList){
        this.mContext = mContext;
        this.userList = userList;
        this.instance = this;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.single_user_view, null);
        UserViewHolder viewHolder = new UserViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder,final int i) {
        final UserViewHolder holder = (UserViewHolder) viewHolder;
        holder.ivProfile.setImageResource(userList.get(i).getProductImge());
        holder.tvEmail.setText(userList.get(i).getProductName());
        holder.tvCreatedDate.setText(userList.get(i).getProductInstantcName());
        add_count = new int[7];
        // saveArray(null,"array",mContext);
        totP =loadArray("array",mContext);

        if(totP.length>0){
          holder.tvRes.setText(""+totP[i]);
        }else{
          holder.tvRes.setText("0");
        }
        holder.butnRemove.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                add_count[i]=add_count[i]+1;
                holder.tvRes.setText(""+add_count[i]);
                saveArray(add_count,"array",mContext);
                //Toast.makeText(mContext,"Clicked : " +add_count[i][0],Toast.LENGTH_SHORT).show();
            }
        });
        holder.butnAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(add_count[i]!=0) {
                    add_count[i] = add_count[i]-1;
                    holder.tvRes.setText(""+add_count[i]);
                    saveArray(add_count,"array",mContext);
                  //  Toast.makeText(mContext, "Clicked : " + add_count[i][0], Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    public int getItemCount(){

        return userList.size();
    }


    public  boolean therIs_thereIsNot(){
        boolean test = false;
        int totP []= loadArray("array",mContext);
        for (int i =0; i<7; i++){
           if (add_count[i]>0 || totP !=null){
               test = true ;
               break;
           }else {
               test = false ;
           }
        }
        return test;
    }

    public boolean saveArray(int[] array, String arrayName, Context mContext) {
        SharedPreferences prefs = mContext.getSharedPreferences("preferencename", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(arrayName, array.length);
        for(int i=0;i<array.length;i++)
         editor.putInt(arrayName+i,array[i]);
        return editor.commit();
    }


    public int[] loadArray(String arrayName, Context mContext) {
        SharedPreferences prefs = mContext.getSharedPreferences("preferencename", 0);
        int size = prefs.getInt(arrayName,0);
        int array[] = new int[size];
        for(int i=0;i<size;i++)
            array[i] = prefs.getInt(arrayName+i,0);
        return array;
    }




    public static void ClearArray(Context mContext) {
        SharedPreferences settings = mContext.getSharedPreferences("PreferencesName", Context.MODE_PRIVATE);
        //settings.edit().clear().commit();
       mContext.getSharedPreferences("preferencename", 0).edit().clear().commit();
    }



    public static class UserViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivProfile;
        private ImageView ivProfile2;
        private ImageView ivProfile3;
        private TextView tvEmail;
        private TextView tvCreatedDate;
        private TextView tvRes;
        private Button butnAdd;
        private Button butnRemove;
        //private Button b_next;
        public UserViewHolder(final View itemView){
            super(itemView);
            ivProfile = (ImageView) itemView.findViewById(R.id.ivProfile);
             // tvName = (TextView) itemView.findViewById(R.id.tvName);
             // tvMobile = (TextView) itemView.findViewById(R.id.tvMobile);
             // ivProfile2 = (ImageView) itemView.findViewById(R.id.appSelect);
            tvRes = (TextView) itemView.findViewById(R.id.tvresult);
            butnAdd = (Button)itemView.findViewById(R.id.appSelect2);
            butnRemove = (Button)itemView.findViewById(R.id.appSelect);
            //b_next =(Button)itemView.findViewById(R.id.btn_next);
            //ivProfile3= (ImageView) itemView.findViewById(R.id.appSelect2);
            tvEmail = (TextView) itemView.findViewById(R.id.tvEmail);
            tvCreatedDate = (TextView) itemView.findViewById(R.id.tvCreatedDate);
            //b_next.setClickable(true);
            //itemView.setFocusableInTouchMode(true);
            /*
            b_next.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                 //tvRes.
                 //tvRes = (TextView) ((TextView) itemView.findViewById(R.id.tvresult)).getText();
                 //String title1 = ((TextView) itemView.findViewById(R.id.title)).getText().toString();
                // Toast.makeText(itemView.getContext(),"Next", Toast.LENGTH_LONG).show();
                }
            });
            */
            butnAdd.setOnClickListener(new View.OnClickListener(){
             @Override
             public void onClick(View v) {
              //tvRes.
               //tvRes = (TextView) ((TextView) itemView.findViewById(R.id.tvresult)).getText();
                //String title1 = ((TextView) itemView.findViewById(R.id.title)).getText().toString();
                 //Toast.makeText(itemView.getContext(),"Pos:XXX:"+, Toast.LENGTH_LONG).show();
                }
            });
            butnRemove.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {

                  //  Toast.makeText(itemView.getContext(),"Pos:TTT:"+Integer.toString(getAdapterPosition()), Toast.LENGTH_LONG).show();
                }
            });

            /*
            b_next.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {

                    Toast.makeText(itemView.getContext(),"Next", Toast.LENGTH_LONG).show();
                }
            });
          */
        }
        /*
        public void onClickBtn(View v)
        {
            Toast.makeText(this, "Clicked on Button", Toast.LENGTH_LONG).show();
        }
        */
        //  changeActivity

    }
}



    /*
            butnAdd.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {

                    Toast.makeText(itemView.getContext(), "Position: " + Integer.toString(getAdapterPosition()), Toast.LENGTH_LONG).show();
                }
            });
            */