package dat19d.group.six.motorhomerental.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class SeasonCheckerTests {

    @Test
    void SeasonCheckerReturnsRightSeason(){
        LocalDate testLowDate1 = LocalDate.parse("2001-01-01"); //12-12, 02-28
        LocalDate testLowDate2 = LocalDate.parse("2001-02-28");
        LocalDate testLowDate3 = LocalDate.parse("2001-12-12");

        LocalDate testMidDate1 = LocalDate.parse("2001-05-01");
        LocalDate testMidDate2 = LocalDate.parse("2001-05-31");
        LocalDate testMidDate3 = LocalDate.parse("2001-09-01");
        LocalDate testMidDate4 = LocalDate.parse("2001-03-01");
        LocalDate testMidDate5 = LocalDate.parse("2001-12-11");

        LocalDate testPeakDate1 = LocalDate.parse("2001-07-01");
        LocalDate testPeakDate2 = LocalDate.parse("2001-06-01");
        LocalDate testPeakDate3 = LocalDate.parse("2001-08-31");


        assertEquals(SeasonChecker.evaluateSeason(testLowDate1), SeasonChecker.Season.LOW);
        assertEquals(SeasonChecker.evaluateSeason(testLowDate2), SeasonChecker.Season.LOW);
        assertEquals(SeasonChecker.evaluateSeason(testLowDate3), SeasonChecker.Season.LOW);

        assertEquals(SeasonChecker.evaluateSeason(testMidDate1), SeasonChecker.Season.MID);
        assertEquals(SeasonChecker.evaluateSeason(testMidDate2), SeasonChecker.Season.MID);
        assertEquals(SeasonChecker.evaluateSeason(testMidDate3), SeasonChecker.Season.MID);
        assertEquals(SeasonChecker.evaluateSeason(testMidDate4), SeasonChecker.Season.MID);
        assertEquals(SeasonChecker.evaluateSeason(testMidDate5), SeasonChecker.Season.MID);

        assertEquals(SeasonChecker.evaluateSeason(testPeakDate1), SeasonChecker.Season.PEAK);
        assertEquals(SeasonChecker.evaluateSeason(testPeakDate2), SeasonChecker.Season.PEAK);
        assertEquals(SeasonChecker.evaluateSeason(testPeakDate3), SeasonChecker.Season.PEAK);

    }
}
