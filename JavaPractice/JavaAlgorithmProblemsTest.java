import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.Assert;

class JavaAlgorithmProblemsTest {

    @Test
    void test_foo() {
        JavaAlgorithmProblems algosProblem = new JavaAlgorithmProblems();
        algosProblem.foo();
        Assert.assertEquals(algosProblem.foo(), true);
    }

    @Test
    void test_bar() {
        JavaAlgorithmProblems algosProblem = new JavaAlgorithmProblems();
        Assert.assertEquals(algosProblem.bar(), 5);
        Assert.assertEquals(algosProblem.bar(), 3);
    }


    @Test
    void test_abc() {
        JavaAlgorithmProblems algosProblem = new JavaAlgorithmProblems();
        Assert.assertEquals(algosProblem.abc(), "hello");
    }

}
