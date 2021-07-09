package com.xyz.download.controller;

import com.xyz.download.service.CompletableFutureDownloadService;
import com.xyz.download.service.CountDownloadService;
import com.xyz.download.service.AsyncDownloadService;
import com.xyz.download.service.FutureDownloadService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * 下载
 */
@RestController
@RequestMapping("/download")
public class DownloadController {
    @Resource
    private AsyncDownloadService asyncDownloadService;
    @Resource
    private CountDownloadService countDownloadService;
    @Resource
    private FutureDownloadService futureDownloadService;
    @Resource
    private CompletableFutureDownloadService completableFutureDownloadService;

    List<String> fileUrls = new ArrayList<>();

    {
        fileUrls.add("http://img.keaitupian.cn/uploads/2021/06/29/eqrkkwq225c.jpg");
        fileUrls.add("http://img.keaitupian.cn/uploads/2021/06/29/kgebbqaneap.jpg");
        fileUrls.add("http://img.keaitupian.cn/uploads/2021/06/29/ywmgk45lipb.jpg");
        fileUrls.add("http://img.keaitupian.cn/uploads/2021/06/29/hroxnckwtxd.jpg");
        fileUrls.add("http://img.keaitupian.cn/uploads/2021/06/29/wxhfxvjlkb4.jpg");
        fileUrls.add("http://img.keaitupian.cn/uploads/2021/06/29/wpwunojj3xo.jpg");
        fileUrls.add("http://img.keaitupian.cn/uploads/2021/06/29/gvedifxsq5x.jpg");
        fileUrls.add("http://img.keaitupian.cn/uploads/2021/05/24/142c7416903de2665191f2620d09604b.jpg");
        fileUrls.add("http://img.keaitupian.cn/newupload/07/1625797130669358.jpg");
        fileUrls.add("http://img.keaitupian.cn/newupload/07/1625735656688841.jpg");
        fileUrls.add("http://img.keaitupian.cn/uploads/2021/06/29/1231yqjmncp.jpg");
        fileUrls.add("http://img.keaitupian.cn/uploads/2021/06/29/flqou12pb3s.jpg");
        fileUrls.add("http://img.keaitupian.cn/uploads/2021/06/29/tf2leplbbr3.jpg");
        fileUrls.add("http://img.keaitupian.cn/uploads/2021/06/29/uu2hk4lwaaj.jpg");
        fileUrls.add("http://img.keaitupian.cn/uploads/2021/06/29/2dmy1laf1yv.jpg");
        fileUrls.add("http://img.keaitupian.cn/uploads/2021/06/03/n3pvzc2szlv.jpg");
        fileUrls.add("http://img.keaitupian.cn/uploads/2021/06/03/3inlombbo30.jpg");
        fileUrls.add("http://img.keaitupian.cn/newupload/07/1625715501455183.jpg");
    }

    /**
     * 批量下载报名表
     */
    @GetMapping("/asyncDown")
    public void asyncDown() {
        long startTime = System.currentTimeMillis();
        for (String fileUrl : fileUrls) {
            asyncDownloadService.download("E:\\test", fileUrl);
        }
        System.out.println("花费 = " + (System.currentTimeMillis() - startTime));
    }

    /**
     * 批量下载报名表
     */
    @GetMapping("/countDown")
    public void countDown() throws InterruptedException {
        countDownloadService.download("E:\\test", fileUrls);
    }

    /**
     * 批量下载报名表
     */
    @GetMapping("/futureDown")
    public void futureDown() throws InterruptedException, ExecutionException {
        futureDownloadService.download("E:\\test", fileUrls);
    }

    /**
     * 批量下载报名表
     */
    @GetMapping("/completableFutureDown")
    public void completableFutureDown() {
        completableFutureDownloadService.download("E:\\test", fileUrls);
    }
}
