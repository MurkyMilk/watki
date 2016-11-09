
import java.util.stream.IntStream;

class watki {

    public static final int FIBONACCI_NUM_TO_CALCULATE = 30;
    public static final int IERATIONS_NUM = 15;

    public static long fibonacci(int n) {
        if (n <= 1) return n;
        else return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static void main(String[] args) throws InterruptedException {

        long startTime = System.currentTimeMillis();
        run_multithread_test(FIBONACCI_NUM_TO_CALCULATE,  IERATIONS_NUM);
        System.out.println(" MULTI TIME: " + (System.currentTimeMillis() - startTime));

        startTime = System.currentTimeMillis();
        run_single_thread_test(FIBONACCI_NUM_TO_CALCULATE, IERATIONS_NUM);
        System.out.println("SINGLE TIME: " + (System.currentTimeMillis() - startTime));


    }

    private static void run_single_thread_test(int fibonacci_num_to_calculate, int num_of_iterations) {
        IntStream.range(0, num_of_iterations).forEach(n ->
            System.out.println(fibonacci(fibonacci_num_to_calculate+n))
        );
    }

    private static void run_multithread_test(int fibonacci_num_to_calculate, int num_of_iterations) {
        IntStream.range(0, num_of_iterations).parallel().forEach(n ->
                System.out.println(fibonacci(fibonacci_num_to_calculate + n))
        );

    }
}



