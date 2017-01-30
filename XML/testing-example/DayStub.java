public class DayStub { 

    int d = 1;
    boolean leap = true;
    
    public void setDay(int value) {
      d=value;
    }

    public void setLeap(boolean value) {
      leap=value;
    }
   
    public int getDay(int month, int day, int year) {
        return d;
    }

    public boolean isLeapYear(int year) {
	return leap;
    }

}
