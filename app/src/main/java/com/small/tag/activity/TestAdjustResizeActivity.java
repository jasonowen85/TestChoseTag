package com.small.tag.activity;


import android.os.Build;
import android.os.Bundle;
import android.webkit.WebView;

import com.small.tag.BaseActivity;
import com.small.tag.R;
import com.small.tag.utils.AndroidAdjustResizeBugFix;
import com.small.tag.utils.StringUtils;
import com.small.tag.utils.WebViewUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：jason on 2017/9/23 0023 16:29
 * 邮箱：jianglu@eims.com.cn
 * 说明：
 */
public class TestAdjustResizeActivity extends BaseActivity {
    @Bind(R.id.merchandise_detail_page_view)
    WebView mWebView;
    private String rootUrl = "http://i.ifeng.com/";

    private static final String HTML_HEAD = "<!DOCTYPE html>\n" + "<html>\n" + "<head>\n" +
            "<meta charset=\"UTF-8\">\n" +"<title></title>\n" +"</head>\n" +"<body>";
    private static final String HTML_END = "</body>\n" + "</html>";

    @Override
    protected int getLayoutRes() {
//        isSetStatusBar = false;
        return R.layout.activity_test_adjustresize;
    }

    @Override
    protected void initView() {
//        AndroidAdjustResizeBugFix.assistActivity(this);
        titleManager.setLeftLayout(R.string.back, R.drawable.back);
        titleManager.setTitleTxt(R.string.select);
        String html = "原标题：“将门之后”秦天中将的新职务" +
                "近日，报道显示，北部战区陆军原领导郑家概，已接替秦天，任武警部队参谋长。" +
                "另据天津市滨海新区政府官网消息，14日，由天津市人民政府、中国航空工业集团公司、中国人民解放军陆军共同主办的第四届中国天津国际直升机博览会在滨海新区开幕。\n" +
                "天津市长王东峰出席开幕式并宣布开幕。市委常委、滨海新区区委书记张玉卓主持。中国人民解放军陆军副司令员彭勃、中航工业集团公司总经理谭瑞松、副市长何树山致辞。中国人民解放军空军副司令员张洪贺，" +
                "武警部队副司令员秦天等出席。";
        if (!StringUtils.isEmpty(html)){
            WebViewUtil.setWebView(context,mWebView);
            mWebView.loadDataWithBaseURL(rootUrl, HTML_HEAD +formatContent(html)  + HTML_END, "text/html", "UTF-8", null);
        }
    }

    private String formatContent(String content){

        StringBuilder fitContent = new StringBuilder();
        if (Build.VERSION.SDK_INT < 14)
            fitContent.append("<meta name='viewport' content = 'user-scalable=no, width=device-width, initial-scale=1' />");
        else
            fitContent.append("<meta name='viewport' content = 'user-scalable=no, " +
                    "width=device-width, initial-scale=1'/><style type=text/css>img{max-width: 100%;height:auto;}</style>");
        fitContent.append(content);
        return fitContent.toString();
    }


}
