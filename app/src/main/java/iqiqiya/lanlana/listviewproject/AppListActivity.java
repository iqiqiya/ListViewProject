package iqiqiya.lanlana.listviewproject;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Author: iqiqiya
 * Date: 2019/9/27
 * Time: 13:47
 * Blog: blog.77sec.cn
 * Github: github.com/iqiqiya
 */
public class AppListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_list);

        ListView appListView = findViewById(R.id.app_list_view);

        List<String> appNames = new ArrayList<>();

        appNames.add("QQ");
        appNames.add("微信");
        appNames.add("微博");
        appNames.add("钉钉");
        appNames.add("钉钉");
        appNames.add("钉钉");
        appNames.add("钉钉");

        appListView.setAdapter(new AppListAdapter(appNames));
    }
    public class AppListAdapter extends BaseAdapter {

        List<String> mAppNames;

        // 构造器
        public AppListAdapter(List<String> appNames){
            mAppNames = appNames;
        }


        // 将数据与视图进行适配
        @Override
        public int getCount() {
            // 有多少数据
            return mAppNames.size();
        }

        @Override
        public Object getItem(int position) {
            // 获取当前position位置的这一条
            return mAppNames.get(position);
        }

        @Override
        public long getItemId(int position) {
            // 获取当前position位置的这一条ID
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // 处理View -- data 填充数据的一个过程

            LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = layoutInflater.inflate(R.layout.item_app_list_view,null);

            ImageView appIconImageView = convertView.findViewById(R.id.app_icon_image_view);
            TextView appNameTextView = convertView.findViewById(R.id.app_name_text_view);

            appNameTextView.setText(mAppNames.get(position));

            return convertView;
        }
    }
}
