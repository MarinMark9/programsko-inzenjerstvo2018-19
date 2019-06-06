package app;

public class Time {
	
	public static String categorizeTime(String line) {
		// 07/17/2010 08:49:00 PM
		if (line.equals("")) {
			return "None";
		}
		
		String timeText = line.split(" ")[1];
		String timeOffset = line.split(" ")[2];
		
		int time = Integer.parseInt(timeText.split(":")[0]);
		
		if (timeOffset.contentEquals("PM")) {
			time += 12;
		}
		
		String result;
		if ( 5 < time && time < 7) {
			result = "EARLY_MORNING";
		} else if (7 <= time && time <= 10) {
			result = "MORNING";
		} else if (10 < time && time <= 12) {
			result = "LATE_MORNING";
		} else if (12 < time && time < 14) {
			result = "EARLY_AFTERNOON";
		} else if (14 <= time && time <= 15) {
			result = "AFTERNOON";
		} else if (15 < time && time < 17) {
			result = "LATE_AFTERNOON";
		} else if (17 < time && time < 19) {
			result = "EARLY_EVENING";
		} else if (19 <= time && time <= 20) {
			result = "EVENING";
		} else if (20 < time && time < 22) {
			result = "LATE_EVENING";
		} else {
			result = "NIGHT";
		}
		return result;
	}

}
