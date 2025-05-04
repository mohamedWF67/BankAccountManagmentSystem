package com.mycompany.bankaccountmanagmentsystem.ThemeManger;

import java.io.OutputStream;
import java.io.PrintStream;

public class ErrorHandler {
    static PrintStream error;

    public static void DisableErrors(){
        error = System.err;
        System.setErr(new PrintStream(new OutputStream() {
            public void write(int b) {} // do nothing
        }));
    }
    public static void EnableErrors(){
        System.setErr(error);
    }
}
