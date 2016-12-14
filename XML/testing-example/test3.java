public class test3 { 

    public static void main(String[] args) {

        int month = Integer.parseInt(args[0]);    // month (Jan = 1, Dec = 12)
        int year = Integer.parseInt(args[1]);     // year

	Day myday = new Day();
        boolean b = myday.isLeapYear(year);
        
        System.out.println(b);
     }
}
