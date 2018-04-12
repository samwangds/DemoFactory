package demo.com.sam.demofactory.activity.launchmode;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sam.lib.app.BaseActivity;

import demo.com.sam.demofactory.R;

/**
 * Created by SamWang(199004) on 2018/4/12.
 */
public class RecyclerViewTest extends BaseActivity {
    private RecyclerView recyclerView;
    
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_recyclerview;
    }

    @Override
    protected void initView() {
        super.initView();
        recyclerView = (RecyclerView) findViewById(R.id.rv);
        recyclerView.setAdapter(new MyAdapter());
    }

    int countCreate = 0;
    int countBind = 0;
    class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Log.e("Sam", "MyAdapter onCreateViewHolder " + (++countCreate));

            return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, null));
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            Log.e("Sam", "MyAdapter onBindViewHolder "+ position +" , "  + (++countBind));
            holder.tv.setText(String.valueOf(position));
            holder.tv.setBackgroundColor(position % 2 == 0 ? Color.GRAY : Color.BLUE);
        }

        @Override
        public int getItemCount() {
            return 100;//测试100个count 调用几次onCreateViewHolder onBindViewHolder
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView;
        }
    }
}

