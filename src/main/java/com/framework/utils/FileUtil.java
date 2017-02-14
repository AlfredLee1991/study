package com.framework.utils;

import java.io.File;

/**
 * 功能描述：文件操作工具.<br/>
 * 
 * #date： 2016年3月26日 上午11:12:13<br/>
 * #author lixu<br/>
 * #since 1.0.0<br/>
 */
public class FileUtil{

    private FileUtil() {
    }

    /**
     * 方法描述：删除指定文件 <br/>
     *
     * #author lixu<br/>
     * #date 2016年3月26日 上午11:12:33<br/>
     * #since 1.0.0<br/>
     * 
     * @param path
     */
    public static void deleteFiles(String path) {
        File file = new File(path);
        // 1級文件刪除
        if (!file.isDirectory()) {
            file.delete();
        } else if (file.isDirectory()) {
            // 2級文件列表
            String[] filelist = file.list();
            // 获取新的2级路径
            for (int j = 0; j < filelist.length; j++) {
                File filessFile = new File(path + "\\" + filelist[j]);
                if (!filessFile.isDirectory()) {
                    filessFile.delete();
                } else if (filessFile.isDirectory()) {
                    // 递归调用
                    deleteFiles(path + "\\" + filelist[j]);
                }
            }
            file.delete();
        }
    }
}
