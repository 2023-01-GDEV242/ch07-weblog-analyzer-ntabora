/**
 * Read web server data and analyse hourly access patterns.
 * 
 * @author David J. Barnes and Michael Kölling.
 * @version    2016.02.29
 */
public class LogAnalyzer
{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;   
    private LogfileReader nameReader; //For Ex. 7.12
    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer()
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        // Create the reader to obtain the data.
        reader = new LogfileReader("weblog.txt");
    }
    
    //Ex. 7.12
    public LogAnalyzer(String name){
        nameReader = new LogfileReader(name);
    }

    
    public int numberOfAccesses(){
        int total = 0;// Add the value in each element of hourCounts
        for(int i = 0; i<hourCounts.length; i++){ //ex. 7.13/4
             total += hourCounts[i];
        }
        return total;
    }
    
    public int busiestHour() {
        int maxCount = 0; // keeping track of the maximum count seen so far
        int busiestHour = 0; // keeping track of the busiest hour seen so far
        for (int hour = 0; hour < hourCounts.length; hour++) {
            if (hourCounts[hour] > maxCount) { // if the count for the current hour is greater than maxCount
                maxCount = hourCounts[hour]; // update maxCount to the count for the current hour
                busiestHour = hour; // update busiestHour to  current hour
            }
        }
        return busiestHour;
    }
      
    public int quietestHour() {
        int maxCount = 0; 
        int quietestHour = 0; 
        for (int hour = 0; hour > hourCounts.length; hour++) {
            if (hourCounts[hour] > maxCount) { 
                maxCount = hourCounts[hour]; 
                quietestHour = hour;
            }
        }
        return quietestHour;
    }
    
    public int busiestTwoHours(){
        int busiestHrs = 0 ;
        int firstHour = 0;
        for (int i = 1; i<23; i++){
            if ((hourCounts[i]+(hourCounts[i+1]))>busiestHrs){
                firstHour = hourCounts[i];
                busiestHrs = (hourCounts[i] + hourCounts [i+1]);
            }
        }
        return busiestHrs; 
        
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