/**
 * Read web server data and analyse hourly access patterns.
 * 
 * @author Eric Brown
 * @version    10-12-2022
 */
public class LogAnalyzer
{   
    private int[] yearCounts;
    private int[] monthCounts;
    private int[] dayCounts;
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;

    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer()
    { 
        // Create the array object to hold the hourly
        // access counts.
        monthCounts = new int[12];
        dayCounts = new int[28];
        hourCounts = new int[24];
        // Create the reader to obtain the data.
        reader = new LogfileReader("demo.log");
    }
    
    /**
     * Create an object to analyze hourly web accesses from a specific file.
     * Exercise 7.12
     * @param file The file to analyze. 
     */
    public LogAnalyzer(String file)
    {
        // Create the array object to hold the hourly
        // access counts.
        monthCounts = new int[12];
        dayCounts = new int[28];
        hourCounts = new int[24];
        // Create the reader to obtain the data.
        reader = new LogfileReader(file);
    }

    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
        reader.reset();
    }
    
    /**
     * Analyze the daily access data from the log file.
     */
    public void analyzeDailyData()
    {   
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int day = entry.getDay() - 1;
            dayCounts[day]++;
        }
        reader.reset();
    }
    
    /**
     * Analyze the monthly access data from the log file.
     */
    public void analyzeMonthlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int month = entry.getMonth() - 1;
            monthCounts[month]++;
        }
        reader.reset();
    }
    
    /**
     * Return the number of accesses recorded in the log file.
     * Exercise 7.14
     * @return The number of accesses recorded in the log file..*/
    public int numberOfAccesses()
    {
        int total = 0;
        for(int index = 0; index < hourCounts.length;index++)
        {
            total += hourCounts[index];
        }
        return total;
    }
    
    /**
     * Return the index of the busiest hour. 
     * Exercise 7.15
     * @return The index of the busiest hour.
     */
    public int busiestHour()
    {
        int busiest = 0;
        for (int index = 0; index < hourCounts.length; index++)
        {
            if (hourCounts[index] > hourCounts[busiest]){
            busiest = index;
            } 
        }
        return busiest;
    }
    
    /**
     * Return the index of the quietest hour.
     * Exercise 7.16
     * @return The index of the quietest hour.
     */
    public int quietestHour()
    {
        int quietest = 0;
        for (int index = 0; index < hourCounts.length; index++)
        {
            if (hourCounts[index] < hourCounts[quietest]){
            quietest = index;
            } 
        }
        return quietest;
    }
    
    /**
     * Return the index of the busiest hour. 
     * Exercise 7.19
     * @return The index of the busiest hour.
     */
    public int busiestDay()
    {
        int busiest = 0;
        for (int index = 0; index < dayCounts.length; index++)
        {
            if (dayCounts[index] > dayCounts[busiest]){
            busiest = index;
            } 
        }
        return busiest;
    }
    
    /**
     * Return the index of the quietest day.
     * Exercise 7.19
     * @return The index of the quietest day.
     */
    public int quietestDay()
    {
        int quietest = 0;
        for (int index = 0; index < dayCounts.length; index++)
        {
            if (dayCounts[index] < dayCounts[quietest]){
            quietest = index;
            } 
        }
        return quietest;
    }
    
    /**
     * Return the first index of the busiest two-hour period.
     * Exercise 7.18
     * @return The index of the first hour of the busiest two-hour period.
     */
    public int busiestTwoHour()
    {
        int busiest = 0;
        int twoHrTotal;
        int previousTotal = 0;
        int nextIndex;
        for (int index = 0; index < hourCounts.length; index++)
        {   
            if(index < 23){
                nextIndex = index + 1;}
            else{
                nextIndex = 0;
            }
            twoHrTotal= hourCounts[index] + hourCounts[nextIndex];
            if (twoHrTotal > previousTotal){
                busiest = index;
            }
            previousTotal = twoHrTotal;
        }
        return busiest;
    }
    
    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printHourlyCounts()
    {
        System.out.println("Hr: Count");
        for(int hour = 0; hour < hourCounts.length; hour++) {
            System.out.println(hour + ": " + hourCounts[hour]);
        }
    }
    
    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }
}
