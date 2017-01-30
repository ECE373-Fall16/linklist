public class test1 { 

    public static void main(String[] args) {

        int month = Integer.parseInt(args[0]);    // month (Jan = 1, Dec = 12)
         if (month < 1 || month > 12) {
          System.out.println("Month OutOfBoundsError");
          System.exit(1);
        }

        int year = Integer.parseInt(args[1]);     // year

	int day = Integer.parseInt(args[2]);	  // for output value testing
        boolean leap = Boolean.parseBoolean(args[3]); // for output value testing

        PrintStub toScreen = new PrintStub();
        toScreen.setYear(year);
        toScreen.setMonth(month);
        
	// get starting day
	DayStub myday = new DayStub();
	myday.setDay(day);
	myday.setLeap(leap);
      	int d = myday.getDay(month, 1, year);
        if (d < 1 || d > 31) {
          System.out.println("Day OutOfBoundsError");
          System.exit(1);
        }

        toScreen.setStartDay(d);

        toScreen.printCalendar();
     }
}
