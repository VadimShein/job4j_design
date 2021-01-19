//package ru.job4j.synch;
//
//
//public class ParallelSearch {
//    public static void main(String[] args) throws InterruptedException {
//        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<Integer>();
//
//        final Thread consumer = new Thread(
//                () -> {
//                    while (!Thread.currentThread().isInterrupted()) {
//                        try {
//                            System.out.println("strt");
//                            Integer num = queue.poll();
//
//                            Thread.sleep(10);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                            Thread.currentThread().interrupt();
//                        }
//                    }
//                }
//        );
//        consumer.start();
//
////        new Thread(
////                () -> {
////                    for (int index = 0; index != 3; index++) {
////                        queue.offer(index);
////                        try {
////                            Thread.sleep(500);
////                        } catch (InterruptedException e) {
////                            e.printStackTrace();
////
////                        }
////                    }
////
////                }
////        ).start();
//
//    }
//}
