package util.orderData;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Start {

	public static void main(String[] args) {
		String csvFile = "src/main/orderdata.csv";
        String line = "";
        String csvSplitBy = ";";
        Set<String> uniqueCustomerIds = new HashSet<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            // Assuming the customerId column is the third column (index 2)
            while ((line = br.readLine()) != null) {
                String[] data = line.split(csvSplitBy);
                if (data.length > 2) { // Ensure there are enough columns
                    uniqueCustomerIds.add(data[2]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int uniqueCustomerCount = uniqueCustomerIds.size();
        System.out.println("Number of unique customerIds: " + uniqueCustomerCount);
        uniqueCustomerIds.stream().forEach(e -> System.out.println(e));

	}

}
