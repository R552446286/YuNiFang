package com.baway.yunifang.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.baway.yunifang.R;
import com.baway.yunifang.activity.ClassifyNoTabActivity;
import com.baway.yunifang.activity.ClassifyTabActivity;
import com.baway.yunifang.activity.GoodsContentActivity;
import com.baway.yunifang.bean.ClassifyBean;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 任珏
 * @类的用途
 * @date 2017/4/17 19:06
 */
public class ClassifyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private Context context;
    private ClassifyBean.Data data;
    private ArrayList<String> gx_ids;
    private ArrayList<String> gx_tabs;
    private ArrayList<String> fz_ids;
    private ArrayList<String> fz_tabs;

    public ClassifyAdapter(Context context, ClassifyBean.Data data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1) {
            return new MyViewHolder1(View.inflate(context, R.layout.classify_recycler_item1, null));
        } else if (viewType == 2) {
            return new MyViewHolder2(View.inflate(context, R.layout.classify_recycler_item2, null));
        } else if (viewType == 3) {
            return new MyViewHolder3(View.inflate(context, R.layout.classify_recycler_item3, null));
        } else if (viewType == 4) {
            return new MyViewHolder4(View.inflate(context, R.layout.classify_recycler_item4, null));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder1) {
            ((MyViewHolder1) holder).item1_mianmo.setOnClickListener(this);
            ((MyViewHolder1) holder).item1_runfushui.setOnClickListener(this);
            ((MyViewHolder1) holder).item1_runfuru.setOnClickListener(this);
            ((MyViewHolder1) holder).item1_jiemianru.setOnClickListener(this);
            ((MyViewHolder1) holder).item1_qita.setOnClickListener(this);
            ((MyViewHolder1) holder).item1_shihui.setOnClickListener(this);
            ((MyViewHolder1) holder).item1_nanshi.setOnClickListener(this);
        } else if (holder instanceof MyViewHolder2) {
            gx_ids = new ArrayList<>();
            gx_ids.add(data.category.get(0).children.get(0).id);
            gx_ids.add(data.category.get(0).children.get(1).id);
            gx_ids.add(data.category.get(0).children.get(2).id);
            gx_ids.add(data.category.get(0).children.get(3).id);
            gx_ids.add(data.category.get(0).children.get(4).id);
            gx_tabs = new ArrayList<>();
            gx_tabs.add(data.category.get(0).children.get(0).cat_name);
            gx_tabs.add(data.category.get(0).children.get(1).cat_name);
            gx_tabs.add(data.category.get(0).children.get(2).cat_name);
            gx_tabs.add(data.category.get(0).children.get(3).cat_name);
            gx_tabs.add(data.category.get(0).children.get(4).cat_name);
            ((MyViewHolder2) holder).item2_cat_name.setText("-" + data.category.get(0).cat_name + "-");
            ((MyViewHolder2) holder).item2_bushui.setOnClickListener(this);
            ((MyViewHolder2) holder).item2_xiufu.setOnClickListener(this);
            ((MyViewHolder2) holder).item2_kongyou.setOnClickListener(this);
            ((MyViewHolder2) holder).item2_meibai.setOnClickListener(this);
            ((MyViewHolder2) holder).item2_kangzhou.setOnClickListener(this);
        } else if (holder instanceof MyViewHolder3) {
            fz_ids = new ArrayList<>();
            fz_ids.add(data.category.get(2).children.get(0).id);
            fz_ids.add(data.category.get(2).children.get(1).id);
            fz_ids.add(data.category.get(2).children.get(2).id);
            fz_ids.add(data.category.get(2).children.get(3).id);
            fz_ids.add(data.category.get(2).children.get(4).id);
            fz_ids.add(data.category.get(2).children.get(5).id);
            fz_tabs = new ArrayList<>();
            fz_tabs.add(data.category.get(2).children.get(0).cat_name);
            fz_tabs.add(data.category.get(2).children.get(1).cat_name);
            fz_tabs.add(data.category.get(2).children.get(2).cat_name);
            fz_tabs.add(data.category.get(2).children.get(3).cat_name);
            fz_tabs.add(data.category.get(2).children.get(4).cat_name);
            fz_tabs.add(data.category.get(2).children.get(5).cat_name);
            ((MyViewHolder3) holder).item3_cat_name.setText("-" + data.category.get(2).cat_name + "-");
            ((MyViewHolder3) holder).item3_hunhe.setText("#" + data.category.get(2).children.get(0).cat_name + "#");
            ((MyViewHolder3) holder).item3_zhongxing.setText("#" + data.category.get(2).children.get(1).cat_name + "#");
            ((MyViewHolder3) holder).item3_ganxing.setText("#" + data.category.get(2).children.get(2).cat_name + "#");
            ((MyViewHolder3) holder).item3_youxing.setText("#" + data.category.get(2).children.get(3).cat_name + "#");
            ((MyViewHolder3) holder).item3_doudou.setText("#" + data.category.get(2).children.get(4).cat_name + "#");
            ((MyViewHolder3) holder).item3_minggan.setText("#" + data.category.get(2).children.get(5).cat_name + "#");
            ((MyViewHolder3) holder).item3_hunhe.setOnClickListener(this);
            ((MyViewHolder3) holder).item3_zhongxing.setOnClickListener(this);
            ((MyViewHolder3) holder).item3_ganxing.setOnClickListener(this);
            ((MyViewHolder3) holder).item3_youxing.setOnClickListener(this);
            ((MyViewHolder3) holder).item3_doudou.setOnClickListener(this);
            ((MyViewHolder3) holder).item3_minggan.setOnClickListener(this);
        }else if (holder instanceof MyViewHolder4){
            GridLayoutManager gridLayoutManager=new GridLayoutManager(context,2);
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (position==10){
                        return 2;
                    }
                    return 1;
                }
            });
            ((MyViewHolder4) holder).item4_classify_recycler.setLayoutManager(gridLayoutManager);
            final List<ClassifyBean.Data.GoodsBrief> goodsBrief = data.goodsBrief;
            ClassifyItem4Adapter adapter = new ClassifyItem4Adapter(context, goodsBrief);
            ((MyViewHolder4) holder).item4_classify_recycler.setAdapter(adapter);
            adapter.setOnRecyclerItemClickListener(new ClassifyItem4Adapter.OnRecyclerItemClickListener() {
                @Override
                public void onRecyclerItemClick(View view) {
                    int position1 = ((MyViewHolder4) holder).item4_classify_recycler.getChildAdapterPosition(view);
                    if (position1<goodsBrief.size()){
                        Intent intent=new Intent(context, GoodsContentActivity.class);
                        intent.putExtra("id",goodsBrief.get(position1).id);
                        context.startActivity(intent);
                    }
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return 1;
            case 1:
                return 2;
            case 2:
                return 3;
            case 3:
                return 4;
        }
        return 0;
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.item1_mianmo:
                Intent intent=new Intent(context, ClassifyTabActivity.class);
                intent.putExtra("title",data.category.get(1).children.get(0).cat_name);
                ArrayList<String> ids=new ArrayList<>();
                ids.add(data.category.get(1).children.get(6).id);
                ids.add(data.category.get(1).children.get(7).id);
                ids.add(data.category.get(1).children.get(8).id);
                ArrayList<String> tabs=new ArrayList<>();
                tabs.add(data.category.get(1).children.get(6).cat_name);
                tabs.add(data.category.get(1).children.get(7).cat_name);
                tabs.add(data.category.get(1).children.get(8).cat_name);
                intent.putStringArrayListExtra("tabs",tabs);
                intent.putStringArrayListExtra("ids", ids);
                context.startActivity(intent);
                break;
            case R.id.item1_runfushui:
                intentActivity(1);
                break;
            case R.id.item1_runfuru:
                intentActivity(2);
                break;
            case R.id.item1_jiemianru:
                intentActivity(3);
                break;
            case R.id.item1_qita:
                intentActivity(4);
                break;
            case R.id.item1_shihui:
                intentActivity(5);
                break;
            case R.id.item1_nanshi:
                intentActivity(9);
                break;
            case R.id.item2_bushui:
                intentTabActivity(gx_tabs,gx_ids,0);
                break;
            case R.id.item2_xiufu:
                intentTabActivity(gx_tabs,gx_ids,1);
                break;
            case R.id.item2_kongyou:
                intentTabActivity(gx_tabs,gx_ids,2);
                break;
            case R.id.item2_meibai:
                intentTabActivity(gx_tabs,gx_ids,3);
                break;
            case R.id.item2_kangzhou:
                intentTabActivity(gx_tabs,gx_ids,4);
                break;
            case R.id.item3_hunhe:
                intentTabActivity(fz_tabs,fz_ids,0);
                break;
            case R.id.item3_zhongxing:
                intentTabActivity(fz_tabs,fz_ids,1);
                break;
            case R.id.item3_ganxing:
                intentTabActivity(fz_tabs,fz_ids,2);
                break;
            case R.id.item3_youxing:
                intentTabActivity(fz_tabs,fz_ids,3);
                break;
            case R.id.item3_doudou:
                intentTabActivity(fz_tabs,fz_ids,4);
                break;
            case R.id.item3_minggan:
                intentTabActivity(fz_tabs,fz_ids,5);
                break;
        }
    }

    private void intentTabActivity(ArrayList<String> list1,ArrayList<String> list2,int position) {
        Intent intent1=new Intent(context,ClassifyTabActivity.class);
        intent1.putExtra("title",data.category.get(0).cat_name);
        intent1.putStringArrayListExtra("tabs",list1);
        intent1.putStringArrayListExtra("ids",list2);
        intent1.putExtra("position",position);
        context.startActivity(intent1);
    }

    private void intentActivity(int position) {
        Intent intent1=new Intent(context, ClassifyNoTabActivity.class);
        intent1.putExtra("title",data.category.get(1).children.get(position).cat_name);
        intent1.putExtra("id",data.category.get(1).children.get(position).id);
        context.startActivity(intent1);
    }

    public static class MyViewHolder1 extends RecyclerView.ViewHolder {

        private final ImageView item1_mianmo;
        private final ImageView item1_runfushui;
        private final ImageView item1_runfuru;
        private final ImageView item1_jiemianru;
        private final ImageView item1_qita;
        private final ImageView item1_shihui;
        private final ImageView item1_nanshi;

        public MyViewHolder1(View itemView) {
            super(itemView);
            item1_mianmo = (ImageView) itemView.findViewById(R.id.item1_mianmo);
            item1_runfushui = (ImageView) itemView.findViewById(R.id.item1_runfushui);
            item1_runfuru = (ImageView) itemView.findViewById(R.id.item1_runfuru);
            item1_jiemianru = (ImageView) itemView.findViewById(R.id.item1_jiemianru);
            item1_qita = (ImageView) itemView.findViewById(R.id.item1_qita);
            item1_shihui = (ImageView) itemView.findViewById(R.id.item1_shihui);
            item1_nanshi = (ImageView) itemView.findViewById(R.id.item1_nanshi);
        }
    }

    public static class MyViewHolder2 extends RecyclerView.ViewHolder {

        private final TextView item2_cat_name;
        private final ImageView item2_bushui;
        private final ImageView item2_xiufu;
        private final ImageView item2_kongyou;
        private final ImageView item2_meibai;
        private final ImageView item2_kangzhou;

        public MyViewHolder2(View itemView) {
            super(itemView);
            item2_cat_name = (TextView) itemView.findViewById(R.id.item2_cat_name);
            item2_bushui = (ImageView) itemView.findViewById(R.id.item2_bushui);
            item2_xiufu = (ImageView) itemView.findViewById(R.id.item2_xiufu);
            item2_kongyou = (ImageView) itemView.findViewById(R.id.item2_kongyou);
            item2_meibai = (ImageView) itemView.findViewById(R.id.item2_meibai);
            item2_kangzhou = (ImageView) itemView.findViewById(R.id.item2_kangzhou);
        }
    }

    public static class MyViewHolder3 extends RecyclerView.ViewHolder {

        private final TextView item3_cat_name;
        private final TextView item3_hunhe;
        private final TextView item3_zhongxing;
        private final TextView item3_ganxing;
        private final TextView item3_youxing;
        private final TextView item3_doudou;
        private final TextView item3_minggan;

        public MyViewHolder3(View itemView) {
            super(itemView);
            item3_cat_name = (TextView) itemView.findViewById(R.id.item3_cat_name);
            item3_hunhe = (TextView) itemView.findViewById(R.id.item3_hunhe);
            item3_zhongxing = (TextView) itemView.findViewById(R.id.item3_zhongxing);
            item3_ganxing = (TextView) itemView.findViewById(R.id.item3_ganxing);
            item3_youxing = (TextView) itemView.findViewById(R.id.item3_youxing);
            item3_doudou = (TextView) itemView.findViewById(R.id.item3_doudou);
            item3_minggan = (TextView) itemView.findViewById(R.id.item3_minggan);
        }
    }

    public static class MyViewHolder4 extends RecyclerView.ViewHolder {

        private final RecyclerView item4_classify_recycler;

        public MyViewHolder4(View itemView) {
            super(itemView);
            item4_classify_recycler = (RecyclerView) itemView.findViewById(R.id.item4_classify_recycler);
        }
    }
}
