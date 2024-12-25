package com.xlc.demo.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class CompletableFutureTest {
    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 5,
            60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
    void test1() {
        List<Integer> ids = new ArrayList<>();
        List<CompletableFuture<Map<String, Integer>>> futures = new ArrayList<>();

        for (Integer id : ids) {
            CompletableFuture<Map<String, Integer>> future = CompletableFuture.supplyAsync(() -> {
                // 模拟查询es数据
                Map<String, Integer> resultMap = new HashMap<>();
                resultMap.put("feture", id);
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
        public static void main(String[] args) throws ExecutionException, InterruptedException {
        /*使用thenApply方法时子任务与父任务使用的是同一个线程，而thenApplyAsync在子任务中是另起一个线程执行任务，
        并且thenApplyAsync可以自定义线程池，默认的使用ForkJoinPool.commonPool()线程池。*/
        // testThenApplyAsync();
        testThenApplyAsync();
        // testThenApply();
        testThenApply();
        }

        static void testThenApplyAsync() throws ExecutionException, InterruptedException{
            System.out.println(Thread.currentThread().getName());
            CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(() -> {
                System.out.println(Thread.currentThread().getName() + " cf1 do something....");
                return 1;
            },executor);

            CompletableFuture<Integer> cf2 = cf1.thenApplyAsync((result) -> {
                System.out.println(Thread.currentThread().getName() + " cf2 do something....");
                result += 2;
                return result;
            },executor);
            //等待任务1执行完成
            System.out.println("cf1结果->" + cf1.get());
            //等待任务2执行完成
            System.out.println("cf2结果->" + cf2.get());
        }

        public static void testThenApply() throws ExecutionException, InterruptedException {
            CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(() -> {
                System.out.println(Thread.currentThread() + " cf1 do something....");
                return 1;
            },executor);

            CompletableFuture<Integer> cf2 = cf1.thenApply((result) -> {
                System.out.println(Thread.currentThread() + " cf2 do something....");
                result += 2;
                return result;
            });
            //等待任务1执行完成
            System.out.println("cf1结果->" + cf1.get());
            //等待任务2执行完成
            System.out.println("cf2结果->" + cf2.get());
        }
}
