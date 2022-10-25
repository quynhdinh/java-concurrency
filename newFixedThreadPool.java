package com.example;

import java.text.MessageFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class ZCC {
    private static final int NUMBER_OF_THREADS = 3;

    public static void main(String[] args) {
        final ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
        for (int i = 0; i < 10; i++) {
            executorService.execute(createWorkItemWithOrderNumber(i));
        }
    }

    static final AtomicInteger concurrentExecutions = new AtomicInteger(0);

    private static Runnable createWorkItemWithOrderNumber(final int orderNumber) {
        return () -> {
            int numberOfConcurrentExecutions = concurrentExecutions.incrementAndGet();

            System.out.println(MessageFormat.format("This is the {0}th work item running in thread {1}, # of concurrent executions {2}",
                    orderNumber, Thread.currentThread().getName(), numberOfConcurrentExecutions));
            concurrentExecutions.decrementAndGet();
        };
    }
}