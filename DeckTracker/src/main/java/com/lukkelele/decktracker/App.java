package com.lukkelele.decktracker;

import com.lukkelele.decktracker.LogHandler;

public class App 
{
    public static void main( String[] args )
    {
      com.lukkelele.decktracker.LogHandler logHandler = new LogHandler();
      System.out.println(logHandler.jsonFile);
    }
}
