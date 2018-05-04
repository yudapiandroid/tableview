package com.suse.dapi.tableview;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.suse.dapi.tableview.core.view.CombainTableView;
import com.suse.dapi.tableview.entrys.TypeBitmapModel;
import com.suse.dapi.tableview.layer.TypeBitmapLayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {


    private CombainTableView tableView;

    private RecyclerView recyclerView;
    private List<Object> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private static final int[] types = {
            TypeBitmapModel.TYPE_01,
            TypeBitmapModel.TYPE_02,
            TypeBitmapModel.TYPE_03,
            TypeBitmapModel.TYPE_04
    };

    private void initData() {
        final List<Object> models = new ArrayList<>();
        final Random random = new Random();
        for(int i=0;i < 5;i++){
            TypeBitmapModel model = new TypeBitmapModel();
            model.setCurrentType(types[random.nextInt(types.length)]);
            models.add(model);
        }
        tableView.addDrawLayer(new TypeBitmapLayer(1,this));
        tableView.setData(models);
    }


    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.rv_content);
        GridLayoutManager manager = new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(new DemoAdapter(data));
    }// end m

}


class DemoAdapter extends RecyclerView.Adapter{

    private List<Object> data;

    public DemoAdapter(List<Object> data) {
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        DemoViewHolder demoViewHolder = new DemoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.demo_item,null),parent.getContext());
        return demoViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof DemoViewHolder){
            DemoViewHolder viewHolder = (DemoViewHolder) holder;
            viewHolder.setData();
        }
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class DemoViewHolder extends RecyclerView.ViewHolder{


        private CombainTableView left;
        private CombainTableView right;
        private CombainTableView left1;
        private CombainTableView right1;
        private Context context;

        public DemoViewHolder(View itemView,Context context) {
            super(itemView);
            this.context = context;
            left = (CombainTableView) itemView.findViewById(R.id.left_view);
            right = (CombainTableView) itemView.findViewById(R.id.right_view);
            left1 = (CombainTableView) itemView.findViewById(R.id.left_view_1);
            right1 = (CombainTableView) itemView.findViewById(R.id.right_view_2);
            initData();
        }

        public void setData(){
            left.setData(models);
            right.setData(models);
            left1.setData(models);
            right1.setData(models);
        }


        private final int[] types = {
                TypeBitmapModel.TYPE_01,
                TypeBitmapModel.TYPE_02,
                TypeBitmapModel.TYPE_03,
                TypeBitmapModel.TYPE_04
        };

        List<Object> models;
        private void initData() {
            models = new ArrayList<>();
            final Random random = new Random();
            for(int i=0;i < 5000;i++){
                TypeBitmapModel model = new TypeBitmapModel();
                model.setCurrentType(types[random.nextInt(types.length)]);
                models.add(model);
            }
            left.addDrawLayer(new TypeBitmapLayer(1,context));
            right.addDrawLayer(new TypeBitmapLayer(1,context));
            left1.addDrawLayer(new TypeBitmapLayer(1,context));
            right1.addDrawLayer(new TypeBitmapLayer(1,context));
        }

    }

}
