
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

class watki {

    public static final int FIBONACCI_NUM_TO_CALCULATE = 40;
    public static final int IERATIONS_NUM = 8;

    public static long fibonacci(int n) {
        if (n <= 1) return n;
        else return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static void main(String[] args) throws InterruptedException {

        long startTime = System.currentTimeMillis();
        multi_test(FIBONACCI_NUM_TO_CALCULATE,  IERATIONS_NUM);
        System.out.println("TIME: " + (System.currentTimeMillis() - startTime));

        startTime = System.currentTimeMillis();
        single_test(FIBONACCI_NUM_TO_CALCULATE, IERATIONS_NUM);
        System.out.println("TIME: " + (System.currentTimeMillis() - startTime));

    }

    private static void single_test(int fibonacci_num_to_calculate, int num_of_iterations) {
        IntStream.range(0, num_of_iterations).forEachOrdered(n ->
            System.out.println(fibonacci(fibonacci_num_to_calculate+n))
        );
    }

    private static void multi_test(int fibonacci_num_to_calculate, int num_of_iterations) throws InterruptedException {
        ExecutorService executor = Executors.newWorkStealingPool();
        List<Callable<Long>> methods_to_call = new LinkedList<>();

        IntStream.range(0, num_of_iterations).forEachOrdered(n ->
        methods_to_call.add(()->fibonacci(fibonacci_num_to_calculate+n))
        );

        executor.invokeAll(methods_to_call)
                .stream()
                .map(future -> {
                    try {
                        return future.get();
                    }
                    catch (Exception e) {
                        throw new IllegalStateException(e);
                    }
                })
                .forEach(System.out::println);
    }

}



