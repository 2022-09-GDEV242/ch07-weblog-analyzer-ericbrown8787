/**
 * Read web server data and analyse hourly access patterns.
 * 
 * @author Eric Brown
 * @version    10-12-2022
 */
public class LogAnalyzer
{
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
     * Return the index of the busiest hour in the hourCounts array. 
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
