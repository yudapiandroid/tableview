package com.suse.dapi.tableview;

import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.suse.dapi.tableview.core.prefab.DrawBitmapLayer;
import com.suse.dapi.tableview.core.view.CombainTableView;
import com.suse.dapi.tableview.core.view.HScrollTableView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView content;
    private CombainTableView tableView;

    private List<Object> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tableView = (CombainTableView) findViewById(R.id.table);
        data = new ArrayList<>();
        for (int i=0;i < 5;i++){
            data.add(new Object());
        }
        tableView.addDrawLayer(new DrawBitmapLayer(
                BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher),
                2
        ));
        tableView.setData(data);

        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.add(new Object());
                tableView.notifyDataSetChange();
            }
        });

        findViewById(R.id.remove).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (data.size() == 0){
                    return;
                }
                data.remove(data.size()-1);
                tableView.notifyDataSetChange();
            }
        });
    }
}

class DemoAdapter extends RecyclerView.Adapter{

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        DemoViewHolder holder = new DemoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.demo_item,null));
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 100;
    }

    class DemoViewHolder extends RecyclerView.ViewHolder{

        public DemoViewHolder(View itemView) {
            super(itemView);
            /*setTableView((HScrollTableView) itemView.findViewById(R.id.table_1));
            setTableView((HScrollTableView) itemView.findViewById(R.id.table_2));
            setTableView((HScrollTableView) itemView.findViewById(R.id.table_3));
            setTableView((HScrollTableView) itemView.findViewById(R.id.table_4));
            setTableView((HScrollTableView) itemView.findViewById(R.id.table_5));*/
        }

        public void setTableView(HScrollTableView tableView){
            List<Object> data = new ArrayList<>();
            for (int i=0;i < 4000;i++){
                data.add(new Object());
            }
        }
    }
}
