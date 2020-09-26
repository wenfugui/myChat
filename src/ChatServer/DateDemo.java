package ChatServer;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateDemo {
    public static String getDate(){
        SimpleDateFormat ft = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
        return ft.format(new Date());
    }
}
