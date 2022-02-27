package com.example.demo.controller;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class ParallelHandle {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            System.out.println("From Block 1");
            return "Result of Future 1";
        });


        CompletableFuture<Void> callbackFuture1 = future1.thenApply(name -> {
             System.out.println("Saved in the DB of " + name);
            return null;
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            System.out.println("From Block 2");
            return "Result of Future 2";
        });

        CompletableFuture<Void> callbackFuture2 = future2.thenApply(name -> {
            System.out.println("Saved in the DB of " + name);
            return null;
        });

        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            System.out.println("From Block 3");
            return "Result of Future 3";
        });

        CompletableFuture<Void> callbackFuture3 = future3.thenApply(name -> {
            System.out.println("Saved in the DB of " + name);
            return null;
        });

        CompletableFuture<Object> anyOfFuture = CompletableFuture.anyOf(future1, future2, future3);

        System.out.println(anyOfFuture.get()); // Result of Future 2

        TimeUnit.SECONDS.sleep(10);

    }
}
