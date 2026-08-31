package com.danil.practice;


public class JavaBasicsMain 
{
    public static void main( String[] args )
    {
        dataTypes();
        operations();
    }


    public static void dataTypes(){

         // Basic data types
        String testerName = "Danil";

        byte retryCount = 3;
        short plannedTests = 1000;
        int totalTests = 120;
        long processedRecords = 9000000000L;

        float codeCoverage = 85.5F;
        double executionTime = 14.75;

        char environmentCode = 'Q';
        boolean pipelinePassed = false;

        // Changing an existing value
        int failedTests = 7;
        failedTests = 5;

        // Calculation
        int passedTests = totalTests - failedTests;

        // Automatic conversion: int to long and double
        long totalTestsAsLong = totalTests;
        double totalTestsAsDouble = totalTests;

        // Explicit conversion: double to int
        int wholeExecutionMinutes = (int) executionTime;

        // String to numeric types
        String testsFromApi = "150";
        String timeFromApi = "18.25";

        int parsedTests = Integer.parseInt(testsFromApi);
        double parsedTime = Double.parseDouble(timeFromApi);

        // Number to String
        String passedTestsAsText = String.valueOf(passedTests);

        // Output
        System.out.println("Tester: " + testerName);
        System.out.println("Retry count: " + retryCount);
        System.out.println("Planned tests: " + plannedTests);
        System.out.println("Total tests: " + totalTests);
        System.out.println("Passed tests: " + passedTests);
        System.out.println("Failed tests: " + failedTests);
        System.out.println("Processed records: " + processedRecords);
        System.out.println("Code coverage: " + codeCoverage + "%");
        System.out.println("Execution time: " + executionTime);
        System.out.println("Environment code: " + environmentCode);
        System.out.println("Pipeline passed: " + pipelinePassed);

        System.out.println("Int converted to long: " + totalTestsAsLong);
        System.out.println("Int converted to double: " + totalTestsAsDouble);
        System.out.println("Double converted to int: " + wholeExecutionMinutes);

        System.out.println("String converted to int: " + parsedTests);
        System.out.println("String converted to double: " + parsedTime);
        System.out.println("Int converted to String: " + passedTestsAsText);

    }

    public static void operations (){

                // Arithmetic operators
        int totalTests = 120;
        int failedTests = 5;
        int blockedTests = 3;

        int passedTests = totalTests - failedTests - blockedTests;

        // Integer division and remainder
        int batchSize = 7;
        int fullBatches = totalTests / batchSize;
        int remainingTests = totalTests % batchSize;

        // Converting int to double before division
        double passRate =
                (double) passedTests / totalTests * 100;

        // Assignment operators
        double executionTime = 60.0;
        executionTime += 15.5;
        executionTime -= 5.0;

        // Increment and decrement
        int retryCount = 1;
        retryCount++;
        retryCount--;

        // Comparison operators
        boolean hasFailures = failedTests > 0;
        boolean failuresWithinLimit = failedTests <= 5;
        boolean allTestsPassed = passedTests == totalTests;
        boolean resultsAreDifferent = passedTests != totalTests;

        // Logical operators
        boolean environmentAvailable = true;
        boolean deploymentCompleted = true;
        boolean criticalBugFound = false;

        boolean canRunTests =
                environmentAvailable && deploymentCompleted;

        boolean shouldStopTesting =
                criticalBugFound || !environmentAvailable;

        boolean releaseReady =
                passRate >= 90
                && failuresWithinLimit
                && !criticalBugFound;

        // String comparison
        String expectedStatus = "PASSED";
        String actualStatus = "passed";

        boolean exactStatusMatch =
                expectedStatus.equals(actualStatus);

        boolean statusMatchIgnoringCase =
                expectedStatus.equalsIgnoreCase(actualStatus);

        // Output
        System.out.println("Total tests: " + totalTests);
        System.out.println("Passed tests: " + passedTests);
        System.out.println("Failed tests: " + failedTests);
        System.out.println("Blocked tests: " + blockedTests);

        System.out.println("Full batches: " + fullBatches);
        System.out.println("Remaining tests: " + remainingTests);
        System.out.println("Pass rate: " + passRate + "%");
        System.out.println("Execution time: " + executionTime);
        System.out.println("Retry count: " + retryCount);

        System.out.println("Has failures: " + hasFailures);
        System.out.println("Failures within limit: " + failuresWithinLimit);
        System.out.println("All tests passed: " + allTestsPassed);
        System.out.println("Results are different: " + resultsAreDifferent);

        System.out.println("Can run tests: " + canRunTests);
        System.out.println("Should stop testing: " + shouldStopTesting);
        System.out.println("Release ready: " + releaseReady);

        System.out.println("Exact status match: " + exactStatusMatch);
        System.out.println("Status match ignoring case: " + statusMatchIgnoringCase);
                // String concatenation and parentheses
        System.out.println("Without parentheses: " + 10 + 5);
        System.out.println("With parentheses: " + (10 + 5));
    
    }

        public static void ifelsestaitmant(){

        int totalTests = 150;
        int passedTests = 140;
        int failedTests = totalTests - passedTests;
        int criticalBugs = 0;

        boolean environmentAvailable = true;
        boolean deploymentCompleted = true;

        String expectedStatus = "PASSED";
        String actualStatus = "passed";

        // Formula: part / total * 100
        double passRate =
                (double) passedTests / totalTests * 100;

        boolean statusesMatch =
                expectedStatus.equalsIgnoreCase(actualStatus);

        // Simple if
        if (failedTests > 0) {
            System.out.println("Failed tests were found");
        }

        // If and else
        if (deploymentCompleted) {
            System.out.println("Deployment is completed");
        } else {
            System.out.println("Deployment is not completed");
        }

        // Else-if chain
        if (!environmentAvailable) {
            System.out.println("Environment is unavailable");
        } else if (criticalBugs > 0) {
            System.out.println("Critical bugs were found");
        } else if (passRate >= 95) {
            System.out.println("Excellent test result");
        } else if (passRate >= 90) {
            System.out.println("Good test result");
        } else {
            System.out.println("Test result is not acceptable");
        }

        // Nested condition
        if (environmentAvailable) {

            if (statusesMatch) {
                System.out.println("Environment and status are valid");
            } else {
                System.out.println("Status is incorrect");
            }
        }

        // Combined logical condition
        boolean releaseReady =
                environmentAvailable
                && deploymentCompleted
                && criticalBugs == 0
                && passRate >= 90
                && statusesMatch;

        if (releaseReady) {
            System.out.println("Release is ready");
        } else {
            System.out.println("Release is not ready");
        }

        // Ternary operator
        String shortDecision =
                releaseReady ? "APPROVED" : "REJECTED";

        System.out.println("Total tests: " + totalTests);
        System.out.println("Passed tests: " + passedTests);
        System.out.println("Failed tests: " + failedTests);
        System.out.println("Pass rate: " + passRate + "%");
        System.out.println("Statuses match: " + statusesMatch);
        System.out.println("Decision: " + shortDecision);

    }


}
