package com.xyz.download.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;

import java.io.File;
import java.util.concurrent.Callable;

/**
 * Future 有回调的下载线程
 */
public class FutureDownloadThread implements Callable<String> {
    String filePath;
    String fileUrl;

    public FutureDownloadThread(String filePath, String fileUrl) {
        this.filePath = filePath;
        this.fileUrl = fileUrl;
    }

    /**
     * 下载完成后回调
     */
    @Override
    public String call() throws Exception {
        String fileName = FileNameUtil.getName(FileUtil.file(fileUrl));
        HttpResponse response = HttpRequest.get(fileUrl).execute();
        response.writeBody(new File(filePath + File.separator + fileName));
        return "1";
    }
}
