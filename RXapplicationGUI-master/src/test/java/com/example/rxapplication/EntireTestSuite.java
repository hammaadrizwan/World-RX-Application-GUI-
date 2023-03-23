package com.example.rxapplication;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AddDriverTest.class,
        DeleteNUpdateDriverTest.class
})
public class EntireTestSuite {
}