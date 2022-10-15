package Controller;



import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.ibo.sqliterecyclerviewmy.MainActivity;
import com.ibo.sqliterecyclerviewmy.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import Model.Data;
import View.EditData;

public class MAdapter extends RecyclerView.Adapter<MAdapter.MyViewHolder> {

    private List<Data>list;
    private Context context;
    private DatabaseHalper databaseHalper;

    public MAdapter( Context context,List<Data> list) {
        this.list = list;
        this.context = context;
    }

    public MAdapter(Context context,List<Data> list,  DatabaseHalper databaseHalper) {
        this.list = list;
        this.context = context;
        this.databaseHalper = databaseHalper;
    }

    @Override
    public MyViewHolder onCreateViewHolder(  ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_first,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MAdapter.MyViewHolder myViewHolder, @SuppressLint("RecyclerView")  int i) {
        Data dataa=list.get(i);
        myViewHolder.data.setText(dataa.getName());
        myViewHolder.timestamp.setText(formatDate(dataa.getTimeStamp()));
        myViewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletedate(i);
            }
        });
        myViewHolder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, EditData.class);
                intent.putExtra("position",String.valueOf(dataa.getId()));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView data,timestamp;
        public ImageView edit,delete;
        public MyViewHolder(View itemView) {
            super(itemView);

            data=itemView.findViewById(R.id.data);
            timestamp=itemView.findViewById(R.id.timestamp);
            edit=itemView.findViewById(R.id.editID);
            delete=itemView.findViewById(R.id.deleteID);
        }
    }


    private String formatDate(String dateStr)  {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date= null;
        try {
            date = simpleDateFormat.parse(dateStr);
        } catch (ParseException e) {
           // e.printStackTrace();
            Log.e("error",e.getMessage());
        }
        SimpleDateFormat dateFormat=new SimpleDateFormat("MMM d");

        return dateFormat.format(date);
    }


    private void deletedate(int position){
        databaseHalper.deleteData(list.get(position));
        list.remove(position);
        MainActivity.notifyAdapter();
    }





















}
