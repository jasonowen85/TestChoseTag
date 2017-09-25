package com.small.tag.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.small.tag.R;
import com.small.tag.main.Constant;
import com.small.tag.main.ConsultBaseFragment;
import com.small.tag.utils.SharePreferenceUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * name 不允许重复
 * list 是一个条目栏  有2个类目选择
 * 还有一个图片选取工具框
 * Created by jasonwen on 2017/3/29.
 */
public class AddFragment extends ConsultBaseFragment {

    private EditText etName;
    private EditText etList;
    private List<String> mNameList;
    private String[] itemList = new String[]{"产品", "化妆品"};
    private PopupWindow mPopupView;

    @Override
    protected void initView(View view) {
        super.initView(view);
        etName = (EditText) view.findViewById(R.id.et_name);
        etList = (EditText) view.findViewById(R.id.et_list);
        etList.setOnClickListener(this);
        //给她一个默认的选择
        etList.setText(itemList[0].toString());

    }

    @Override
    protected void initData() {
        super.initData();
        //初始化 获取本地保存的数据;
        String allName = SharePreferenceUtils.getString(Constant.ITEMMAME, null);
        mNameList = new ArrayList<>();

        if (!TextUtils.isEmpty(allName)){
            String[] AllNames = allName.split(",");
            for (String s : AllNames){
                if(!mNameList.contains(s)){
                    mNameList.add(s);
                }
            }
        }
        etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //数据改变完成之后 做一个处理
                String saleName = s.toString().trim();
                if (TextUtils.isEmpty(saleName) && !etName.isFocused()) {
                    Toast.makeText(getmContext(), "名字不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (mNameList.contains(saleName)) {
                    Toast.makeText(getmContext(), "名字重复了", Toast.LENGTH_SHORT).show();
                    //让它获取焦点
                    etName.setFocusable(true);
                }
            }
        });
    }

    /**
     * @param saleName 商品名字
     */
    private void saveSaleName(String saleName) {
        if(!mNameList.contains(saleName)){
            //只有点击提交 才保存到本地
            if (mNameList.size() !=0){
                SharePreferenceUtils.putString(Constant.ITEMMAME, saleName + ",");
            }else {
                SharePreferenceUtils.putString(Constant.ITEMMAME, saleName);
            }
            mNameList.add(saleName);
        }else {
            Toast.makeText(getmContext(), "名字重复了", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.add_fragment;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.et_list:
                showPopupWindows();
                break;
        }
    }

    //关闭弹出窗体
    private void closePopup() {
        if (mPopupView != null && mPopupView.isShowing()) {
            mPopupView.dismiss();
        }
    }


    private void showPopupWindows() {
        ListView mListView = new ListView(getmContext());
        mListView.setBackgroundColor(Color.LTGRAY);

        ArrayAdapter<String> adtapter = new ArrayAdapter<String>(getmContext(),
                R.layout.item_sale_list, itemList);
        mListView.setAdapter(adtapter);

        ScaleAnimation sa = new ScaleAnimation(1f, 1f, 0f, 1f,
                Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f);
        sa.setDuration(300);
        mListView.startAnimation(sa);

        mPopupView = new PopupWindow(mListView,etList.getWidth(), etList.getWidth());

        mPopupView.setOutsideTouchable(true);
        mPopupView.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // 设置popupWindow可以得到焦点
        mPopupView.setFocusable(true);
        //显示位置
        mPopupView.showAsDropDown(etList, 0, 0);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String switchName = itemList[position];
                String currentName = etList.getText().toString().trim();
                if (!switchName.equals(currentName)){
                    etList.setText(switchName);
                }
                closePopup();
            }
        });
    }
}
