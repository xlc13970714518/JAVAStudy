package com.xlc.demo.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class CompletableFutureTest {
    ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 5,
            60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
    void test1() {
        List<Integer> ids = new ArrayList<>();
        List<CompletableFuture<Map<String, Integer>>> futures = new ArrayList<>();

        for (Integer id : ids) {
            CompletableFuture<Map<String, Integer>> future = CompletableFuture.supplyAsync(() -> {
                // 模拟查询es数据
                Map<String, Integer> resultMap = new HashMap<>();
                resultMap.put("feture" , id);
                return resultMap;
            }, executor);
            futures.add(future);
        }

        // 使用 CompletableFuture.allOf 来等待所有任务完成
        CompletableFuture<Void> allRep = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));

        // 如果需要收集所有结果，可以使用以下方法
        CompletableFuture<List<Map<String, Integer>>> allResults = allRep.thenApply(v ->
            futures.stream()
                   .map(CompletableFuture::join)
                   .collect(Collectors.toList())
        );

        try {
            List<Map<String, Integer>> dateList = allResults.get(6000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }


    }

}
