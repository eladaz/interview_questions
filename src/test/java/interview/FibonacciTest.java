package interview;

import org.testng.annotations.Test;

import static interview.Fibonacci.fib;
import static org.testng.Assert.*;

public class FibonacciTest {

	@Test
	public void testFib () {
		int N = 40;
		for (int i=0; i<N; i++) {
			System.out.println(fib(i) + " ");
		}
	}
}