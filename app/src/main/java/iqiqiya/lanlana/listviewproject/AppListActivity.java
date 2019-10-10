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

        /**appNames.add("QQ");
        appNames.add("微信");
        appNames.add("微博");
        appNames.add("钉钉");
        appNames.add("钉钉");
        appNames.add("钉钉");
        appNames.add("钉钉");*/

        appListView.setAdapter(new AppListAdapter(getAppInfos()));

        // 第二种方式添加应用点击效果
        final List<ResolveInfo> appInfos = getAppInfos();
        appListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String packageName = appInfos.get(position).activityInfo.packageName;

                String className = appInfos.get(position).activityInfo.name;

                ComponentName componentName = new ComponentName(packageName, className);

                final Intent intent = new Intent();
                intent.setComponent(componentName);
                startActivity(intent);
            }
        });
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

            LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = layoutInflater.inflate(R.layout.item_app_list_view,null);

            ImageView appIconImageView = convertView.findViewById(R.id.app_icon_image_view);
            TextView appNameTextView = convertView.findViewById(R.id.app_name_text_view);

            appNameTextView.setText(mAppInfos.get(position).activityInfo.loadLabel(getPackageManager()));
            appIconImageView.setImageDrawable(mAppInfos.get(position).activityInfo.loadIcon(getPackageManager()));

            return convertView;
        }
    }
}
