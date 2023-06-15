import java.util.Calendar;
import java.util.Date;

public class demo {
    public static void main(String[] args) {
        Calendar c1 = Calendar.getInstance();
        long a = 1662906721024L;
        c1.setTimeInMillis(a);
        // System.out.println(new Date(1662906721024));
        // 1662906938414
        System.out.println(c1.getTime());
    }
}
