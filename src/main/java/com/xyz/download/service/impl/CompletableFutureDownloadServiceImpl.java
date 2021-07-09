package com.xyz.download.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.core.util.ZipUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.xyz.download.service.CompletableFutureDownloadService;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

/**
 * CompletableFuture 下载接口
 */
@Service
public class CompletableFutureDownloadServiceImpl implements CompletableFutureDownloadService {
    @Resource(name = "taskExecutor")
    private ThreadPoolTaskExecutor taskExecutor;

    /**
     * 下载
     *
     * @param filePath 文件存储路径
     * @param fileUrls 文件url
     * @return void
     */
    @Override
    public void download(String filePath, List<String> fileUrls) {
        long startTime = System.currentTimeMillis();
        Stream<CompletableFuture<String>> completableFutureStream = fileUrls.stream().map((fileUrl) -> CompletableFuture.supplyAsync(() -> {
            String fileName = FileNameUtil.getName(FileUtil.file(fileUrl));
            HttpResponse response = HttpRequest.get(fileUrl).execute();
            response.writeBody(new File(filePath + File.separator + fileName));
            return "1";
        }, taskExecutor));
        CompletableFuture.allOf(completableFutureStream.toArray(CompletableFuture[]::new))
                .whenComplete((v, th) -> {
                    ZipUtil.zip(filePath, "E:\\test.zip");
                    System.out.println("花费 = " + (System.currentTimeMillis() - startTime));
                }).join();
    }
}
