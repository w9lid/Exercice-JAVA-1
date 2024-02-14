public class TimeFormatter {

    public static String formatDuration(int seconds) {
        int[] timeParts = TimeUnitConverter.convertSecondsToTimeParts(seconds);
        return concatenateTimeParts(timeParts);
    }

    private static String concatenateTimeParts(int[] timeParts) {
        String[] units = {"année", "jour", "heure", "minute", "seconde"};
        StringBuilder timeString = new StringBuilder();

        for (int i = 0; i < timeParts.length; i++) {
            if (timeParts[i] > 0) {
                if (timeString.length() > 0) {
                    timeString.append(", ");
                }
                timeString.append(timeParts[i]).append(" ").append(units[i]);
                if (timeParts[i] > 1) {
                    timeString.append("s");
                }
            }
        }

        int lastComma = timeString.lastIndexOf(",");
        if (lastComma != -1) {
            timeString.replace(lastComma, lastComma + 1, " et");
        }

        return timeString.toString();
    }
}
