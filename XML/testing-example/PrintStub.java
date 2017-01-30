public class PrintStub { 
	int Year;
        int Month;
	int StartDay;

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
          System.out.println(StartDay + " " + Month + " " + Year);
	}
}
