package com.xyz.download.service;

import java.util.List;

/**
 * 下载接口
 */
public interface CountDownloadService {
    /**
     * 下载
     * @param filePath 文件存储路径
     * @param fileUrls 文件url
     * @return void
     */
    void download(String filePath, List<String> fileUrls) throws InterruptedException;
}
