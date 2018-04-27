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

import com.suse.dapi.tableview.core.utils.Log;
import com.suse.dapi.tableview.core.view.CombainTableView;
import com.suse.dapi.tableview.core.view.DrawLayer;
import com.suse.dapi.tableview.core.view.HScrollTableView;
import com.suse.dapi.tableview.entrys.FourPerCellModel;
import com.suse.dapi.tableview.entrys.TypeBitmapModel;
import com.suse.dapi.tableview.layer.CenterBottomLayer;
import com.suse.dapi.tableview.layer.LeftBottomLayer;
import com.suse.dapi.tableview.layer.RightBottomLayer;
import com.suse.dapi.tableview.layer.TypeBitmapLayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private CombainTableView leftTopView;
    private CombainTableView rightTopView;
    private CombainTableView leftBottomView;
    private CombainTableView centerBottomView;
    private CombainTableView rightBottomView;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        //initData();
    }

    private void initData() {
        initLeftTop();
        initRightTop();
        initBottom(leftBottomView,new LeftBottomLayer());
        initBottom(centerBottomView,new CenterBottomLayer());
        initBottom(rightBottomView,new RightBottomLayer());

    }

    private void initBottom(CombainTableView view, DrawLayer drawLayer) {

        List<Object> data = new ArrayList<>();
        Random random = new Random();
        int color[] = new int[]{Color.RED,Color.GREEN};
        for (int i=0;i < 1024;i++){
            FourPerCellModel m = new FourPerCellModel();
            m.setColorLB(color[random.nextInt(2)]);
            m.setColorLT(color[random.nextInt(2)]);
            m.setColorRB(color[random.nextInt(2)]);
            m.setColorRT(color[random.nextInt(2)]);
            m.setDrawLB(random.nextBoolean());
            m.setDrawLT(random.nextBoolean());
            m.setDrawRB(random.nextBoolean());
            m.setDrawRT(random.nextBoolean());
            data.add(m);
        }
        view.addDrawLayer(drawLayer);
        view.setData(data);
    }


    /**
     *
     * 右上角
     *
     */
    private void initRightTop() {
        List<Object> data = new ArrayList<>();
        Random random = new Random();
        for(int i = 0;i < 1024;i++){
            TypeBitmapModel model = new TypeBitmapModel();
            int r = random.nextInt(4);
            if(r == 0){
                model.setCurrentType(TypeBitmapModel.TYPE_01);
            }
            if(r == 1){
                model.setCurrentType(TypeBitmapModel.TYPE_02);
            }
            if(r == 2){
                model.setCurrentType(TypeBitmapModel.TYPE_03);
            }
            if(r == 3){
                model.setCurrentType(TypeBitmapModel.TYPE_04);
            }
            data.add(model);
        }
        Map<Integer,Integer> bitmapRef = new HashMap<>();
        bitmapRef.put(TypeBitmapModel.TYPE_01,R.mipmap.icon_4);
        bitmapRef.put(TypeBitmapModel.TYPE_02,R.mipmap.icon_5);
        bitmapRef.put(TypeBitmapModel.TYPE_03,R.mipmap.icon_6);
        TypeBitmapLayer layer = new TypeBitmapLayer(bitmapRef,this);
        rightTopView.addDrawLayer(layer);
        rightTopView.setData(data);
    }


    /**
     *
     * 左上角
     *
     */
    private void initLeftTop() {
        List<Object> data = new ArrayList<>();
        Random random = new Random();
        for(int i = 0;i < 1024;i++){
            TypeBitmapModel model = new TypeBitmapModel();
            int r = random.nextInt(4);
            if(r == 0){
                model.setCurrentType(TypeBitmapModel.TYPE_01);
            }
            if(r == 1){
               model.setCurrentType(TypeBitmapModel.TYPE_02);
            }
            if(r == 2){
                model.setCurrentType(TypeBitmapModel.TYPE_03);
            }
            if(r == 3){
                model.setCurrentType(TypeBitmapModel.TYPE_04);
            }
            data.add(model);
        }
        Map<Integer,Integer> bitmapRef = new HashMap<>();
        bitmapRef.put(TypeBitmapModel.TYPE_01,R.mipmap.icon_01);
        bitmapRef.put(TypeBitmapModel.TYPE_02,R.mipmap.icon_02);
        bitmapRef.put(TypeBitmapModel.TYPE_03,R.mipmap.icon_03);
        TypeBitmapLayer layer = new TypeBitmapLayer(bitmapRef,this);
        leftTopView.addDrawLayer(layer);
        leftTopView.setData(data);
    }

    private void initView() {


        recyclerView = (RecyclerView) findViewById(R.id.rv_content);
        GridLayoutManager manager = new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(new DemoAdapter(this));
    }// end m

}




class DemoAdapter extends RecyclerView.Adapter{

    private LeftBottomLayer lbLayer = new LeftBottomLayer();
    private CenterBottomLayer cbLayer = new CenterBottomLayer();
    private RightBottomLayer rbLayer = new RightBottomLayer();
    private TypeBitmapLayer ltLayer;
    private TypeBitmapLayer rtLayer;

    private Context context;

    public DemoAdapter(Context context) {
        this.context = context;
        initLayerLT();
        initLayerRT();
    }

    private void initLayerLT(){
        Map<Integer,Integer> bitmapRef = new HashMap<>();
        bitmapRef.put(TypeBitmapModel.TYPE_01,R.mipmap.icon_4);
        bitmapRef.put(TypeBitmapModel.TYPE_02,R.mipmap.icon_5);
        bitmapRef.put(TypeBitmapModel.TYPE_03,R.mipmap.icon_6);
        ltLayer = new TypeBitmapLayer(bitmapRef,context);
    }

    private void initLayerRT(){
        Map<Integer,Integer> bitmapRef = new HashMap<>();
        bitmapRef.put(TypeBitmapModel.TYPE_01,R.mipmap.icon_01);
        bitmapRef.put(TypeBitmapModel.TYPE_02,R.mipmap.icon_02);
        bitmapRef.put(TypeBitmapModel.TYPE_03,R.mipmap.icon_03);
        rtLayer = new TypeBitmapLayer(bitmapRef,context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        DemoViewHolder holder = new DemoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.demo_item,null));
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DemoViewHolder h = (DemoViewHolder) holder;
        h.setData();
    }

    @Override
    public int getItemCount() {
        return 100;
    }

    class DemoViewHolder extends RecyclerView.ViewHolder{

        private CombainTableView leftTopView;
        private CombainTableView rightTopView;
        private CombainTableView leftBottomView;
        private CombainTableView centerBottomView;
        private CombainTableView rightBottomView;

        public DemoViewHolder(View itemView) {
            super(itemView);
            leftTopView = (CombainTableView) itemView.findViewById(R.id.table_left_top);
            rightTopView = (CombainTableView) itemView.findViewById(R.id.table_right_top);
            leftBottomView = (CombainTableView) itemView.findViewById(R.id.table_left_bottom);
            centerBottomView = (CombainTableView) itemView.findViewById(R.id.table_center_bottom);
            rightBottomView = (CombainTableView) itemView.findViewById(R.id.table_right_bottom);
        }

        public void setData(){
            initData();
        }




        private void initData() {
            initLeftTop();
            initRightTop();
            initBottom(leftBottomView,lbLayer);
            initBottom(centerBottomView,cbLayer);
            initBottom(rightBottomView,rbLayer);
        }


        private void initBottom(CombainTableView view, DrawLayer drawLayer) {

            List<Object> data = new ArrayList<>();
            Random random = new Random();
            int color[] = new int[]{Color.RED,Color.GREEN};
            for (int i=0;i < 1024;i++){
                FourPerCellModel m = new FourPerCellModel();
                m.setColorLB(color[random.nextInt(2)]);
                m.setColorLT(color[random.nextInt(2)]);
                m.setColorRB(color[random.nextInt(2)]);
                m.setColorRT(color[random.nextInt(2)]);
                m.setDrawLB(random.nextBoolean());
                m.setDrawLT(random.nextBoolean());
                m.setDrawRB(random.nextBoolean());
                m.setDrawRT(random.nextBoolean());
                data.add(m);
            }
            view.addDrawLayer(drawLayer);
            view.setData(data);
        }


        /**
         *
         * 右上角
         *
         */
        private void initRightTop() {
            List<Object> data = new ArrayList<>();
            Random random = new Random();
            for(int i = 0;i < 1024;i++){
                TypeBitmapModel model = new TypeBitmapModel();
                int r = random.nextInt(4);
                if(r == 0){
                    model.setCurrentType(TypeBitmapModel.TYPE_01);
                }
                if(r == 1){
                    model.setCurrentType(TypeBitmapModel.TYPE_02);
                }
                if(r == 2){
                    model.setCurrentType(TypeBitmapModel.TYPE_03);
                }
                if(r == 3){
                    model.setCurrentType(TypeBitmapModel.TYPE_04);
                }
                data.add(model);
            }
            rightTopView.addDrawLayer(ltLayer);
            rightTopView.setData(data);
        }


        /**
         *
         * 左上角
         *
         */
        private void initLeftTop() {
            List<Object> data = new ArrayList<>();
            Random random = new Random();
            for(int i = 0;i < 10;i++){
                TypeBitmapModel model = new TypeBitmapModel();
                int r = random.nextInt(4);
                if(r == 0){
                    model.setCurrentType(TypeBitmapModel.TYPE_01);
                }
                if(r == 1){
                    model.setCurrentType(TypeBitmapModel.TYPE_02);
                }
                if(r == 2){
                    model.setCurrentType(TypeBitmapModel.TYPE_03);
                }
                if(r == 3){
                    model.setCurrentType(TypeBitmapModel.TYPE_04);
                }
                data.add(model);
            }
            leftTopView.addDrawLayer(rtLayer);
            leftTopView.setData(data);
        }

    }


}
