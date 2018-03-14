package ca.ualberta.cs.lonelytwitter;


import java.util.Date;

// Declaration access can be weaker - Fix #1
interface Tweetable {
    // Modifier public is reduncant for interface methods - Fix #3
    String getMessage();

    Date getDate();

}
