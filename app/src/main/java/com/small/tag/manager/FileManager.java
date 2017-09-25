package com.small.tag.manager;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 文件操作工具类
 *
 * @author weiyunchao
 */
public class FileManager {

    public static String TAG = "FileUtils";


    public static File makeDir(String name){
        String savePath = Environment.getExternalStorageDirectory()
                .getAbsolutePath()+File.separator;
        File savedir = new File(savePath, name);
        if (!savedir.exists()) {// 不能自动创建目录
            savedir.mkdirs();
        }

        return savedir;
    }

    public static Bitmap getHttpBitmap(String url)
    {
        Bitmap bitmap = null;
        try
        {
            URL pictureUrl = new URL(url);
            InputStream in = pictureUrl.openStream();
            bitmap = BitmapFactory.decodeStream(in);
            in.close();
        } catch (MalformedURLException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return bitmap;
    }
    /**
     * 保存bitmap到SDka
     */
    public static void saveBitmap(File file, Bitmap bitmap) {
        try {
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 从SD卡读取图片
     *
     * @return
     */
    public static Bitmap readerBitmap(File file) {
        Bitmap bm = null;
        if (file.exists()) {
            bm = BitmapFactory.decodeFile(file.getAbsolutePath());
        }
        return bm;
    }

    /**
     * 修改文件的最后修改时间
     *
     * @param dir
     * @param fileName
     */
    public void updateFileTime(String dir, String fileName) {
        File file = new File(dir, fileName);
        long newModifiedTime = System.currentTimeMillis();
        file.setLastModified(newModifiedTime);
    }

}
