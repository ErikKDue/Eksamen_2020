package dat19d.group.six.motorhomerental.model;

import java.time.LocalDate;

public class SeasonChecker {
    static LocalDate peakSeasonStart = LocalDate.parse("2007-06-01");
    static LocalDate peakSeasonEnd= LocalDate.parse("2007-08-31");

    static LocalDate lowSeasonStart= LocalDate.parse("2007-12-12");
    static LocalDate lowSeasonEnd= LocalDate.parse("2007-02-28");


    enum Season{
        PEAK,
        MID,
        LOW
    }

    public static Season evaluateSeason(LocalDate date){
        //if date falls between peakstart and peakend
        //if (!(date.isBefore(peakSeasonStart) || date.isAfter(peakSeasonEnd))){
        //if (!(date.getDayOfYear()< peakSeasonStart.getDayOfYear() || date.getDayOfYear() > peakSeasonEnd.getDayOfYear())){
         if(doesDateFallBetween(date, peakSeasonStart, peakSeasonEnd)){
            return Season.PEAK;
        }

        if(doesDateFallBetween(date, lowSeasonStart, lowSeasonEnd)){
            return Season.LOW;
        }


        return Season.MID;

    }

    public static boolean doesDateFallBetween(LocalDate testDate, LocalDate startDate, LocalDate endDate){
        if(startDate.getDayOfYear()>endDate.getDayOfYear()){
            if (testDate.getDayOfYear()>=startDate.getDayOfYear() || testDate.getDayOfYear()<= endDate.getDayOfYear()){
                return true;
            }
        }
       else if(!(testDate.getDayOfYear()< startDate.getDayOfYear() || testDate.getDayOfYear() > endDate.getDayOfYear())){
            return true;
        }

        return false;
    }
}
