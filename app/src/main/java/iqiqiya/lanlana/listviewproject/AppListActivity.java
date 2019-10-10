package iqiqiya.lanlana.listviewproject;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

        // 添加头图
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View headerView = layoutInflater.inflate(R.layout.header_list_view,null);

        appListView.addHeaderView(headerView);

        appListView.setAdapter(new AppListAdapter(getAppInfos()));
    }

    /**
     * 获取已安装应用信息
     * @return
     */
    private List<ResolveInfo> getAppInfos(){
        Intent intent = new Intent(Intent.ACTION_MAIN,null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        return getPackageManager().queryIntentActivities(intent,0);
    }


    public class AppListAdapter extends BaseAdapter {

        List<ResolveInfo> mAppInfos;

        // 构造器
        public AppListAdapter(List<ResolveInfo> appNames){
            mAppInfos = appNames;
        }


        // 将数据与视图进行适配
        @Override
        public int getCount() {
            // 有多少数据
            return mAppInfos.size();
        }

        @Override
        public Object getItem(int position) {
            // 获取当前position位置的这一条
            return mAppInfos.get(position);
        }

        @Override
        public long getItemId(int position) {
            // 获取当前position位置的这一条ID
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            // 处理View -- data 填充数据的一个过程


            ViewHolder viewHolder = new ViewHolder();

            if (convertView == null){
                LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = layoutInflater.inflate(R.layout.item_app_list_view,null);

                viewHolder.mAppIconImageView = convertView.findViewById(R.id.app_icon_image_view);
                viewHolder.mAppNameTextView = convertView.findViewById(R.id.app_name_text_view);

                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.mAppNameTextView.setText(mAppInfos.get(position).activityInfo.loadLabel(getPackageManager()));
            viewHolder.mAppIconImageView.setImageDrawable(mAppInfos.get(position).activityInfo.loadIcon(getPackageManager()));


            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ResolveInfo info = mAppInfos.get(position);

                    //该应用的包名
                    String pkg = info.activityInfo.packageName;
                    //应用的主activity类
                    String cls = info.activityInfo.name;

                    ComponentName componet = new ComponentName(pkg, cls);

                    Intent intent = new Intent();
                    intent.setComponent(componet);
                    startActivity(intent);
                }
            });
            return convertView;
        }

        public class ViewHolder{
            public ImageView mAppIconImageView;
            public TextView mAppNameTextView;
        }
    }
}
