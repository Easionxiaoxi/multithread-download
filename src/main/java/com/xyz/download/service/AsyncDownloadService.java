package com.xyz.download.service;

/**
 * 下载服务接口
 */
public interface AsyncDownloadService {
    /**
     * 下载
     * @param filePath 文件存储路径
     * @param fileUrl 文件url
     * @return void
     */
    void download(String filePath,String fileUrl);
}
