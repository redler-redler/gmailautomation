import org.testng.TestNG;

import java.util.Collections;

public class Runner {
    private TestNG testng = new TestNG();

    private void run() {
        testng.setTestSuites(Collections.singletonList(System.getProperty("suite")));
        testng.run();
        System.exit(testng.getStatus());
    }

    public static void main(String[] args) {
        new Runner().run();
    }
}
