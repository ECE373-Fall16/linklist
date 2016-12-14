public class Print { 
	int Year;
        int Month;
	int StartDay;

        public String[] months = {
            "",                               // leave empty so that months[1] = "January"
            "January", "February", "March",
            "April", "May", "June",
            "July", "August", "September",
            "October", "November", "December"
        };

        public int[] days = {
            0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
        };

        public void setMonth(int value) {
		Month = value;
	}

        public void setYear(int value) {
		Year = value;
	}

        public void setStartDay(int value) {
		StartDay = value;
	}

        public void printCalendar() {
          System.out.println("   " + months[Month] + " " + Year);

	  Day myday = new Day();
          // check for leap year
          if (Month == 2 && myday.isLeapYear(Year)) days[Month] = 29;

          // print the calendar
          for (int i = 0; i < StartDay; i++)
            System.out.print("   ");
          for (int i = 1; i <= days[Month]; i++) {
            System.out.printf("%2d ", i);
            if (((i + StartDay) % 7 == 0) || (i == days[Month])) System.out.println();
          }
	}
}
