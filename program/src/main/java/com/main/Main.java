package com.main;

import java.sql.SQLException;
import com.datastorage.SQLServerDatabase;
import com.presentation.GUI.GUI;

import javafx.application.Application;

public class Main {
    public static void main(String[] Args) {
        try {
            SQLServerDatabase.getDatabase().connect();
            System.out.println("connected!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Application.launch(GUI.class);
    }
}