public class Day { 
    
    public int getDay(int month, int day, int year) {
        int y = year - (14 - month) / 12;
        int x = y + y/4 - y/100 + y/400;
        int m = month + 12 * ((14 - month) / 12) - 2;
        int d = (day + x + (31*m)/12) % 7;
        return d;
    }

    public boolean isLeapYear(int year) {
       if  (year % 400==0) return true;
       if  (year % 100==0) return false;
       if  (year % 4==0) return true;
       return false;
    }

}
