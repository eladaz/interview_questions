package interview;

//0,1,1,2,3,5,8,13...
public class Fibonacci {

	static public int fib(int index) {
		if (index <= 1) return index;
		return fib(index-1) + fib(index-2);
	}
}
/*
	fib(4)
		fib(3) + fib(2)
			fib(2) + fib(1) + fib(1) + fib(0)
				fib(1) + fib(0) + fib(1) + fib(1) + fib(0)
					1 + 0 + 1 + 1 + 0 = 3

 */