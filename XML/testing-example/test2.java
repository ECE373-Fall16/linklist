public class test2 { 

    public static void main(String[] args) {

        int month = Integer.parseInt(args[0]);    // month (Jan = 1, Dec = 12)
        int year = Integer.parseInt(args[1]);     // year

	Day myday = new Day();
        int d = myday.getDay(month, 1, year);
        
        System.out.println(d);
     }
}
