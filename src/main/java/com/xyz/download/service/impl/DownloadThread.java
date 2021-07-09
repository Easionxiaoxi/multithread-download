package com.xyz.download.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;

import java.io.File;
import java.util.concurrent.CountDownLatch;

/**
 * 下载线程
 */
public class DownloadThread implements Runnable {
    String filePath;
    String fileUrl;
    CountDownLatch countDownLatch;

    public DownloadThread(String filePath, String fileUrl, CountDownLatch countDownLatch) {
        this.filePath = filePath;
        this.fileUrl = fileUrl;
        this.countDownLatch = countDownLatch;
    }

    /**
     * 下载
     */
    @Override
    public void run() {
        String fileName = FileNameUtil.getName(FileUtil.file(fileUrl));
        HttpResponse response = HttpRequest.get(fileUrl).execute();
        response.writeBody(new File(filePath + File.separator + fileName));
        // 单次任务结束，计数器减1
        countDownLatch.countDown();
    }
}
