package com.xyz.download.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.xyz.download.service.AsyncDownloadService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * 异步下载服务
 */
@Service
public class AsyncDownloadServiceImpl implements AsyncDownloadService {
    /**
     * 下载
     *
     * @param filePath 文件存储路径
     * @param fileUrl  文件url
     * @return void
     */
    @Async("taskExecutor")
    @Override
    public void download(String filePath, String fileUrl) {
        String fileName = FileNameUtil.getName(FileUtil.file(fileUrl));
        HttpResponse response = HttpRequest.get(fileUrl).execute();
        response.writeBody(new File(filePath + File.separator + fileName));
    }
}
