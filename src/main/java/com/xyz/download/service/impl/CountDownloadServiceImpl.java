package com.xyz.download.service.impl;

import cn.hutool.core.util.ZipUtil;
import com.xyz.download.service.CountDownloadService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 下载
 */
@Service
public class CountDownloadServiceImpl implements CountDownloadService {
    @Resource(name = "taskExecutor")
    private ThreadPoolTaskExecutor taskExecutor;
    /**
     * 下载
     *
     * @param filePath 文件存储路径
     * @param fileUrls  文件url
     * @return void
     */
    @Async("taskExecutor")
    @Override
    public void download(String filePath, List<String> fileUrls) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(fileUrls.size());
        long startTime = System.currentTimeMillis();
        for (String fileUrl : fileUrls) {
            taskExecutor.execute(new DownloadThread(filePath,fileUrl,countDownLatch));
        }
        countDownLatch.await();
        ZipUtil.zip("E:\\test", "E:\\test.zip");
        System.out.println("花费 = " + (System.currentTimeMillis() - startTime));
    }
}
