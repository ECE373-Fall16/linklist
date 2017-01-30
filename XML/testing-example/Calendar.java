public class Calendar { 

   /***************************************************************************
    *  Given the month, day, and year, return which day
    *  of the week it falls on according to the Gregorian calendar.
    *  For month, use 1 for January, 2 for February, and so forth.
    *  Returns 0 for Sunday, 1 for Monday, and so forth.
    ***************************************************************************/

    public static void main(String[] args) {

        int month = Integer.parseInt(args[0]);    // month (Jan = 1, Dec = 12)	
        int year = Integer.parseInt(args[1]);     // year

        Print toScreen = new Print();
        toScreen.setYear(year);
        toScreen.setMonth(month);
        
	// get starting day
	Day myday = new Day();
      	int d = myday.getDay(month, 1, year);
        toScreen.setStartDay(d);

        toScreen.printCalendar();
     }
}
