import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
* This program calculates mean, median and mode.
*
*
* @author  Logan S
* @version 1.0
*
* @since   2023-03-28
*/

public final class Arrays {

    /**
     * This is a private constructor.
     *
     * @exception IllegalStateException Utility class.
     * @see IllegalStateException
     */
    private Arrays() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * This is the main method.
     *
     * @param args Unused.
     */
    public static void main(String[] args) {
        // Pass path to file as parameter.
        final File file = new File("Unit1-07-set3.txt");
        final File fileOut = new File("output.txt");

        // Create a new list.
        final List<String> listStrings =
            new ArrayList<String>();

        // Declare variable.
        String stringList;

        try {
            // Create FileWriter object to write to file.
            final FileWriter fW = new FileWriter(fileOut);
            // Create Scanner object to read from file.
            final Scanner scanner = new Scanner(file);
            // Create PrintWriter object to write to file.
            final PrintWriter write = new PrintWriter(fW);

            while (scanner.hasNextLine()) {
                // Read line as string.
                stringList = scanner.next();
                // Adding each string to list.
                listStrings.add(stringList);
            }

            // Convert from list to array.
            final String[] arrayStr =
                listStrings.toArray(new String[0]);

            // Convert all elements in array to an integers.
            // To do so, loop through each element & convert
            // each string.
            final int[] arrayNum =
                new int[arrayStr.length];
            for (int counter = 0; counter < arrayNum.length; counter++) {
                arrayNum[counter] = Integer.parseInt(arrayStr[counter]);
            }

            // Sort each element in array.
            java.util.Arrays.sort(arrayNum);

            // Call function.
            final double mean = calcMean(arrayNum);
            final double median = calcMedian(arrayNum);
            final int[] mode = calcMode(arrayNum);

            // Display to user.
            System.out.println("Mean: " + mean);
            System.out.println("Median: " + median);
            System.out.println("Mode: "
                + java.util.Arrays.toString(mode));

            // Display in file.
            write.println("The mean is: " + mean);
            write.println("The median is: "
                + median);
            write.println("The mode is: "
                + java.util.Arrays.toString(mode));

            // Closes scanner & writer.
            write.close();
            scanner.close();
        } catch (IOException error) {
            System.out.println("An error occurred: "
                    + error.getMessage());
        }
    }

    /**
    * This function calculates the mean of the
    * numbers.
    *
    * @param arrayNum passed
    * @return mean.
    */
    public static double calcMean(int arrayNum[]) {
        // Declare variables.
        double sum = 0;
        double mean = 0;

        // Loop to calculate mean, access all elements.
        for (int num : arrayNum) {
            sum = sum + num;
            mean = sum / arrayNum.length;
        }
        // Return back to main.
        return mean;
    }

    /**
    * This function calculates the median of the
    * numbers.
    *
    * @param arrayNum Passed.
    * @return median.
    */
    public static double calcMedian(int arrayNum[]) {
        // Declare variables.
        double median = 0;
        final int arrayLength = arrayNum.length;

        // Calculate median.
        if (arrayLength % 2 == 0) {
            // Calculate average of two terms.
            median = (arrayNum[arrayLength / 2 - 1]
                    + arrayNum[arrayLength / 2]) / 2;
        } else {
            // Case if odd.
            median = arrayNum[arrayLength / 2];
        }
        // Return back to main.
        return median;
    }

    /**
    * This function calculates the mode of numbers.
    *
    * @param arrayNum passed.
    * @return mode.
    */

    public static int[] calcMode(int[] arrayNum) {
        // Declare variables.
        int maxCount = 0;
        int counter = 0;
        int counter2 = 0;
        final int num = arrayNum.length;

        // Create an arraylist.
        final ArrayList<Integer> listModes =
            new ArrayList<Integer>();

        // Find the maximum count of any repeating elements.
        for (counter = 0; counter < num; counter++) {
            int count = 0;
            for (counter2 = 0; counter2 < num; counter2++) {
                if (arrayNum[counter2] == arrayNum[counter]) {
                    count++;
                }
            }
            // Clear array if count > maxCount,
            // if it's equalled to add the element
            // to list.
            if (count > maxCount) {
                maxCount = count;
                listModes.clear();
                listModes.add(arrayNum[counter]);
            } else if (count == maxCount) {
                listModes.add(arrayNum[counter]);
            }
        }
        // Remove duplicates.
        if (listModes.size() > 1) {
            int number = 1;
            while (number < listModes.size()) {
                if (listModes.get(number) == listModes.get(number - 1)) {
                    listModes.remove(number);
                } else {
                    number++;
                }
            }
        }
        // Convert from list to array.
        final int[] arrayMode = new int[listModes.size()];
        for (int counter4 = 0; counter4 < arrayMode.length; counter4++) {
            arrayMode[counter4] = listModes.get(counter4);
        }
        // Return array back to main.
        return arrayMode;
    }
}
