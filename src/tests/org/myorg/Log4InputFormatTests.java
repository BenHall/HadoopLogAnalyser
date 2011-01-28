package tests.org.myorg;

import org.junit.Test;
import org.myorg.Log4netLogParser;

import static org.junit.Assert.assertEquals;

public class Log4InputFormatTests {
   @Test
   public void Returns_IP_address_from_Log4net_line() {

       String logEntry  = "RANDOM EXCEPTION!!" +
               "   benh :: ERROR :: UnknownException handled \n" +
               "Logger: Some Logger\n" +
               "URL: Some/Url/\n" +
               "Referrer: Some/Referrer/\n" +
               "IP: 127.0.0.1\n" +
               "User-Agent: Mozilla/5.0 (Windows; U; Windows NT 5.2; en-US) AppleWebKit/534.13 (KHTML, like Gecko) Chrome/9.0.597.67 Safari/534.13\n" +
               "Thread: 11\n" +
               "Date: 2011-01-25 12:40:15,255\n" +
               "NDC: (null)";

       Log4netLogParser logParser = new Log4netLogParser();
       String ip = logParser.GetIP(logEntry);
       assertEquals("127.0.0.1", ip);

   }
}
