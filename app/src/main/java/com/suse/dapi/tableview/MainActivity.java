package com.suse.dapi.tableview;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.suse.dapi.tableview.core.view.CombainTableView;
import com.suse.dapi.tableview.core.view.interfaces.DrawLayer;
import com.suse.dapi.tableview.entrys.FourPerCellModel;
import com.suse.dapi.tableview.entrys.TypeBitmapModel;
import com.suse.dapi.tableview.layer.TypeBitmapLayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MainActivity extends AppCompatActivity {


    private CombainTableView tableView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
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
        for(int i=0;i < 500;i++){
            TypeBitmapModel model = new TypeBitmapModel();
            model.setCurrentType(types[random.nextInt(types.length)]);
            models.add(model);
        }
        tableView.addDrawLayer(new TypeBitmapLayer(1,this));
        tableView.setData(models);
        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i=0;i < 3000;i++){
                    TypeBitmapModel model = new TypeBitmapModel();
                    model.setCurrentType(types[random.nextInt(types.length)]);
                    models.add(model);
                }
                tableView.notifyDataSetChange();
            }
        });

        findViewById(R.id.remove).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i=1;i<2000;i++){
                    models.remove(models.size() - i);
                }
                tableView.notifyDataSetChange();
            }
        });
    }


    private void initView() {
        tableView = (CombainTableView) findViewById(R.id.table_view);
    }// end m

}
