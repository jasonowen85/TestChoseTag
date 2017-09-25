package com.small.tag.common;

/**
 * 环境路径配置
 */
public class ServiceConfig {

    /** release模式是否输出日记 */
    public static boolean RELEASE_DEBUG = true;

    public static final String  RELEASE_ROOT_URL= "http://p2p9.ys.xf.cc";//演示环境根路径
    //http://192.168.8.98:9000   原来   http://p2p-10.test3.wangdai.me
    public static final String TEST_ROOT_URL = "http://p2p-4.test3.wangdai.me";//测试环境根路径
//    public static final String TEST_ROOT_URL = "http://www.niumail.com.cn";//测试环境根路径
    public static final String DEBUG_ROOT_URL = "http://p2p-4.test3.wangdai.me";//本地环境根路径
    public static final String APP_SERVICES = "/app/services";

    public enum Mode {
        DEBUG, TEST, RELEASE
    }

    public static Mode SERVICE_MODE = Mode.DEBUG;

//    /**
//     * 3DES数据字段KEY
//     **/
//    public static final String DES_KEY = "uIvirxevYAIOPK5H";
//    /**
//     * MD5KEY
//     **/
//    public static final String MD5_KEY = "YfpRNFQaWKJPDXS6";

    /**
     * 标准版
     * 3DES数据字段KEY
     **/
    public static final String DES_KEY = "cc3I5YKs7ZPfv7BR";
    /**
     * 标准版
     * MD5KEY
     **/
    public static final String MD5_KEY = "9I4NG7pX7EUki6OE";

    /**
     * 上传头像路径
     */
    public static final String UPDATE_HEAD_URL = "/common/appImagesUpload";
    /**
     * 版本更新路径
     **/
    public static final String UPGRADE_ROOT_URL = "http://pre-d.eims.com.cn/download/sp2p";
    public static final String UPGRADE_TEST = "/sp2p9-test.apk";
    public static final String UPGRADE_RELEASE = "/sp2p9.apk";

    static {
//        SERVICE_MODE = Enum.valueOf(Mode.class,
//                AppUtils.getMetaValue(App.getInstance(), "server_mode"));
//        if (RELEASE_DEBUG) L.IS_DEBUG = true;
//        else L.IS_DEBUG = SERVICE_MODE != Mode.RELEASE; //release模式默认不输出日记
    }

    public static String getServicesRootUrl() {
        return getRootUrl() + APP_SERVICES;
    }

    public static String getRootUrl() {
        switch (SERVICE_MODE) {
            case DEBUG:
                return DEBUG_ROOT_URL;
            case TEST:
                return TEST_ROOT_URL;
            case RELEASE:
                return RELEASE_ROOT_URL;
            default:
                break;
        }
        return null;
    }

    /**
     * 获取下载路径
     * @return
     */
    public static String getUpgradeUrl(){
        switch (SERVICE_MODE) {
            case DEBUG:
                return UPGRADE_ROOT_URL+UPGRADE_TEST;
            case TEST:
                return UPGRADE_ROOT_URL+UPGRADE_TEST;
            case RELEASE:
                return UPGRADE_ROOT_URL+UPGRADE_RELEASE;
            default:
                break;
        }
        return null;
    }


    //是否为debug模式
    public static boolean isDebugMode() {
        switch (SERVICE_MODE) {
            case DEBUG:
                return true;
            case TEST:
                return false;
            case RELEASE:
                return false;
        }
        return false;
    }
}
