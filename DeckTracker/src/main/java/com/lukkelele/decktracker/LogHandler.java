package com.lukkelele.decktracker;

import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import com.google.gson.Gson;


class LogHandler {

  Gson jsonHandler;
  File jsonFile;
  String filePath = "./cards.json";

  public LogHandler() {
    System.out.println("LogHandler object created");
    this.jsonHandler = new Gson();
    this.jsonFile = this.openFile(filePath);
  }

  private File openFile(String path) {
    File file = new File(path);
    return file;
  }


}
