public class CircularTour {
    static class Pump {
        int petrol, distance;
        Pump(int p, int d) { petrol = p; distance = d; }
    }

    static int circularTour(Pump[] pumps) {
        int n = pumps.length;
        int totalSurplus = 0;  // overall fuel balance
        int currSurplus  = 0;  // current window balance
        int start        = 0;  // candidate starting index

        for (int i = 0; i < n; i++) {
            int fuel = pumps[i].petrol - pumps[i].distance;
            totalSurplus += fuel;
            currSurplus  += fuel;

            // If current surplus goes negative, reset start to next pump
            if (currSurplus < 0) {
                start = i + 1;
                currSurplus = 0;
            }
        }
        // If total surplus >= 0, a valid start exists
        return totalSurplus >= 0 ? start : -1;
    }

    public static void main(String[] args) {
        Pump[] pumps = {
            new Pump(4, 6),
            new Pump(6, 5),
            new Pump(7, 3),
            new Pump(4, 5)
        };
        int result = circularTour(pumps);
        if (result != -1)
            System.out.println("Start from pump index: " + result); // 1
        else
            System.out.println("No valid starting point exists.");
    }
}