package com.ahmedibrahim.waher.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmedibrahim.waher.R;
import com.ahmedibrahim.waher.activities.HomeActivity;
import com.ahmedibrahim.waher.activities.LoginActivity;
import com.ahmedibrahim.waher.fragments.Page_6Fragment;
import com.ahmedibrahim.waher.models.Customer;
import com.ahmedibrahim.waher.utils.Session;

import java.util.List;

/**
 * Created by cca on 23/12/2018.
 */

public abstract class CustomerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


   // private OnListItemClick onClickListener;

    public final int TYPE_MOVIE = 0;
    public final int TYPE_LOAD = 1;
    public Page_6Fragment page_6Fragment;
    static Context context;
    List<Customer>customers;
    public static int currentItem;
    public static int currentIdUser;
    OnLoadMoreListener loadMoreListener;
   // MyInterface listener;
    boolean isLoading = false, isMoreDataAvailable = true;




    public CustomerAdapter(Page_6Fragment page_6Fragment, List<Customer> customers) {
        this.page_6Fragment = page_6Fragment;
        this.context =  page_6Fragment.getContext();
        this.customers = customers;

        this.currentIdUser = Session.getInstance().getUser().id;
        //Log.i("Is_Manegar00","trace_00: "+  currentIdUser);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        if(viewType==TYPE_MOVIE){
            return new CustomerHolder(inflater.inflate(R.layout.row_movie,parent,false));
        }else{
            return new LoadHolder(inflater.inflate(R.layout.row_load,parent,false));
        }
    }


    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if(position>=getItemCount()-1 && isMoreDataAvailable && !isLoading && loadMoreListener!=null){
            isLoading = true;
            loadMoreListener.onLoadMore();
        }
        if(getItemViewType(position)==TYPE_MOVIE ){
            ((CustomerHolder)holder).bindData(customers.get(position));
             //https://stackoverflow.com/questions/15805578/will-two-strings-with-same-content-be-stored-in-the-same-memory-location
            if(((HomeActivity)context).s_name.equals("ahmed")){
                //Log.i("Is_Manegar 00","yes "+((HomeActivity)context).s_name);
                ((CustomerHolder)holder).showButtons(customers.get(position));

            }else{
               // Log.i("Is_Manegar 11","No "+((HomeActivity)context).s_name);
                ((CustomerHolder)holder).hideButtons(customers.get(position));
            }
                // ((CustomerHolder)holder).buttonViewOption.
             //   Log.i("Is_Manegar Now","Yes "+((HomeActivity)context).s_name);

          //   ((CustomerHolder)holder).showButtons(customers.get(position));

          //  }else {


               // ((CustomerHolder)holder).hideButtons(customers.get(position));
         //   }

            //int intValue = Integer.parseInt(String.valueOf(((LoginActivity)context).Is_Manegar));
            //currentUser = Session.getInstance().getUser().id;
            //Log.i("Is_Manegar11","trace_11 "+  currentIdUser);
            if(((CustomerHolder)holder).buttonViewOption != null)((CustomerHolder)holder).buttonViewOption.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
              //  function call a volly function .. //..

             /*
              if(customers.get(position).user_id !=1 ){

             }


            */
             //if(customers.get(position).user_id==2)((CustomerHolder)holder).setVisibility(View.VISIBLE);
                    /*
                    if(((HomeActivity)holder).my){
                        //Log.i("Emailis1",""+Session.getInstance().getUser().getEmail());
                        buttonViewOption.setVisibility(View.GONE);
                    }else {
                        //Log.i("Emailis0",""+Session.getInstance().getUser().getEmail());
                        buttonViewOption.setVisibility(View.INVISIBLE);

                    }
                    */
              ((CustomerHolder)holder).buttonViewOption.getRootView().toString();
               currentItem = position;
               buttonClickEvent(position);

                 /*
                    if(customers.get(position).status ==1|| customers.get(position).status ==2){
                      Toast.makeText(context,"هذا العميل تم اعلامة سابقا ! ", Toast.LENGTH_SHORT).show();
                   }else{
                        Toast.makeText(context,"نعم ", Toast.LENGTH_SHORT).show();
                      page_6Fragment.showDialog(context,customers.get(position).user_id , Integer.parseInt(customers.get(position).id));
                    }
                */

                     //Page_6Fragment.newInstance().showDialog(context,customers.get(position).user_id , Integer.parseInt(customers.get(position).id));
                     //((CustomerHolder)holder).buttonViewOption.setOnClickListener(new View.OnClickListener() {

                     ((CustomerHolder)holder).setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View view){
                         // buttonClickEvent(position);
                         // buttonClickEvent(position);
                        }
                    });
                }
            });
        }
       // abstract buttonClickEvent(position);
    }



    public abstract void buttonClickEvent(int position);


    @Override
    public int getItemViewType(int position) {

        if(customers.get(position).type.equals("movie")){

            return TYPE_MOVIE;
        }else{
            return TYPE_LOAD;
        }
    }


    @Override
    public int getItemCount(){

       return customers.size();
    }

/*
    public void deleteAtIndex(Context context ,int index,List<Customer>cust){
        cust.remove(index);
        notifyDataSetChanged();
    }
*/

    /* VIEW HOLDERS */

    static class CustomerHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvRating;
        ImageView imgLock;
        //  int usrid;
        Button buttonViewOption;


        public CustomerHolder(View itemView){
            super(itemView);
            tvTitle=(TextView)itemView.findViewById(R.id.title);
            tvRating=(TextView)itemView.findViewById(R.id.rating);
            imgLock=(ImageView)itemView.findViewById(R.id.img_lock);
            buttonViewOption = (Button) itemView.findViewById(R.id.textViewOptions);
           //  itemView.setOnClickListener(this);
        }


        void hideButtons(Customer cust) {
            buttonViewOption.setVisibility(View.INVISIBLE);
        }

        void showButtons(Customer cust) {
            buttonViewOption.setVisibility(View.VISIBLE);
        }

        void bindData(Customer cust){
           tvTitle.setText(cust.name);
           tvRating.setText(cust.title);

            //  if(((HomeActivity)geta).
            /*
            if(((HomeActivity)context).s_name !="ahmed"){
                // ((CustomerHolder)holder).buttonViewOption.
                buttonViewOption.setVisibility(View.GONE);
            }else {
                buttonViewOption.setVisibility(View.INVISIBLE);
            }
            */
            // int index= cust.user_id;
           // ((LoginActivity)context).Is_Manegar
            //if(cust.user_id != currentIdUser)itemView.setVisibility(View.INVISIBLE);
            /*
            if(Session.getInstance().getUser().getName() =="ahmed"){
               //Log.i("Emailis1",""+Session.getInstance().getUser().getEmail());
                buttonViewOption.setVisibility(View.GONE);
                   buttonViewOption.setVisibility(View.INVISIBLE);
            }else {
              //Log.i("Emailis0",""+Session.getInstance().getUser().getEmail());
                buttonViewOption.setVisibility(View.INVISIBLE);

            }
            */
            /*
            if(Session.getInstance().getUser().getEmail() !="ah@yahoo.com"){
                buttonViewOption.setVisibility(View.INVISIBLE);
               // buttonViewOption.setVisibility(View.GONE);
            }else{
                buttonViewOption.setVisibility(View.GONE);
                if(cust.user_id== currentIdUser){
                    buttonViewOption.setVisibility(View.GONE);
                }else {
                    buttonViewOption.setVisibility(View.INVISIBLE);
                }
            }
            */
            //


             // if(cust.user_id==1)buttonViewOption.setVisibility(View.INVISIBLE);
            if(cust.status==1)buttonViewOption.setBackground(context.getResources().getDrawable(R.drawable.lock_));
            if(cust.status==2)buttonViewOption.setBackground(context.getResources().getDrawable(R.drawable.block_));
            // if(cust.user_id==2)imgLock.setVisibility(View.GONE);
            // boolean IsManegar = true;
            /*
            if(!IsManegar){
               if(cust.user_id !=2 )itemView.setVisibility(View.INVISIBLE);
                else
                buttonViewOption.setVisibility(View.INVISIBLE);
            }else {
                if(cust.status!=0)buttonViewOption.setBackground(context.getResources().getDrawable(R.drawable.lock));
            }
            */
            //if(cust.status!=0)outtonViewOption..setBackground(context.getResources().getDrawable(R.drawable.lock));
            //  imgLock.setVisibility(View.VISIBLE);
        }




        public void setOnClickListener(View.OnClickListener onClickListener) {

           // Toast.makeText(context,"نعم ", Toast.LENGTH_SHORT).show();

        }

    }





    static class LoadHolder extends RecyclerView.ViewHolder{

        public LoadHolder(View itemView){
            super(itemView);
        }
    }

    public void setMoreDataAvailable(boolean moreDataAvailable) {
        isMoreDataAvailable = moreDataAvailable;
    }

    /* notifyDataSetChanged is final method so we can't override it
         call adapter.notifyDataChanged(); after update the list
    */

    public void notifyDataChanged(){
        notifyDataSetChanged();
        isLoading = false;
    }
    public interface OnLoadMoreListener{
        void onLoadMore();
    }


    public void setLoadMoreListener(OnLoadMoreListener loadMoreListener){
        this.loadMoreListener = loadMoreListener;
    }





}
