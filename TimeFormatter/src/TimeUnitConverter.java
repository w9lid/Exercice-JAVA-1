public class TimeUnitConverter {

    public static int[] convertSecondsToTimeParts(int seconds) {
        int years = seconds / (365 * 24 * 60 * 60);
        seconds %= (365 * 24 * 60 * 60);
        int days = seconds / (24 * 60 * 60);
        seconds %= (24 * 60 * 60);
        int hours = seconds / (60 * 60);
        seconds %= (60 * 60);
        int minutes = seconds / 60;
        seconds %= 60;

        return new int[]{years, days, hours, minutes, seconds};
    }
}
