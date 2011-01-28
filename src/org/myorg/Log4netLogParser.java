package org.myorg;

import sun.misc.Regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Log4netLogParser {
    public String GetIP(String logEntry) {
        Pattern pattern = Pattern.compile("IP: (.*)", Pattern.MULTILINE);
        Matcher matcher =  pattern.matcher(logEntry);

        if(matcher.find())
            return matcher.group(1);

        return "";
    }
}
