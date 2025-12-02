package com.utils.graphics;
import com.utils.*;
import com.qol.storeDB.IODB;


public class Title {
    public Title() {
        IODB io = new IODB();
        io.println(
                " _____           _             _   _ _ _ _       \n" +
                        "|  __ \\         | |           | \\ | (_) | |      \n" +
                        "| |  \\/ ___  ___| |_ ___  _ __|  \\| |_| | | ___  \n" +
                        "| | __ / _ \\/ __| __/ _ \\| '__| . ` | | | |/ _ \\ \n" +
                        "| |_\\ \\  __/\\__ \\ || (_) | |  | |\\  | | | | (_) |\n" +
                        " \\____/\\___||___/\\__\\___/|_|  \\_| \\_/_|_|_|\\___/ \n" +
                        "                                                 \n" +
                        "                                                 "
        );
    }
}
