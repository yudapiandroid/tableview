package com.suse.dapi.tableview;

import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;

import com.suse.dapi.tableview.core.prefab.DrawBitmapLayer;
import com.suse.dapi.tableview.core.view.HScrollTableView;
import com.suse.dapi.tableview.core.view.TableView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView content;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*content = (RecyclerView) findViewById(R.id.rv_content);
        GridLayoutManager manager = new GridLayoutManager(this,1);
        content.setLayoutManager(manager);
        content.setAdapter(new DemoAdapter());*/
        HScrollTableView tableView = (HScrollTableView) findViewById(R.id.table);
        List<Object> data = new ArrayList<>();
        tableView.setData(data);
        tableView.addDrawLayer(new DrawBitmapLayer(
                BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher),
                2
        ));
        tableView.notifyDataSetChange();
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
            setTableView((HScrollTableView) itemView.findViewById(R.id.table_1));
            setTableView((HScrollTableView) itemView.findViewById(R.id.table_2));
            setTableView((HScrollTableView) itemView.findViewById(R.id.table_3));
            setTableView((HScrollTableView) itemView.findViewById(R.id.table_4));
            setTableView((HScrollTableView) itemView.findViewById(R.id.table_5));
        }

        public void setTableView(HScrollTableView tableView){
            List<Object> data = new ArrayList<>();
            for (int i=0;i < 4000;i++){
                data.add(new Object());
            }
            tableView.setData(data);
            tableView.addDrawLayer(new DrawBitmapLayer(
                    BitmapFactory.decodeResource(tableView.getResources(),R.mipmap.ic_launcher),2
            ));
        }
    }
}
