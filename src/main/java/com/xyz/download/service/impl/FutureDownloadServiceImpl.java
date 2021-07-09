package com.xyz.download.service.impl;

import cn.hutool.core.util.ZipUtil;
import com.xyz.download.service.FutureDownloadService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * Future下载
 */
@Service
public class FutureDownloadServiceImpl implements FutureDownloadService {
    @Resource(name = "taskExecutor")
    private ThreadPoolTaskExecutor taskExecutor;
    //private static final ThreadPoolExecutor taskExecutor = new ThreadPoolExecutor(5, 10, 10L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(1000));

    /**
     * 下载
     *
     * @param filePath 文件存储路径
     * @param fileUrls 文件url
     * @return void
     */
    @Async("taskExecutor")
    @Override
    public void download(String filePath, List<String> fileUrls) throws ExecutionException, InterruptedException {
        List<Future<String>> results = new ArrayList<>();
        //使用submit提交异步任务，并且获取返回值为future
        long startTime = System.currentTimeMillis();
        for (String fileUrl : fileUrls) {
            results.add(taskExecutor.submit(new FutureDownloadThread(filePath, fileUrl)));
        }
        while (true){
            List<Future<String>> doneFutures = results.stream().filter(Future::isDone).collect(Collectors.toList());
            if (doneFutures.size() == fileUrls.size()){
                ZipUtil.zip("E:\\test", "E:\\test.zip");
                System.out.println("花费 = " + (System.currentTimeMillis() - startTime));
                break;
            }
        }
    }
}
