import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVFileParser {

    public static List<List<String>> parseCsvFile(String filePath) {
        List<List<String>> rows = new ArrayList<>();
        StringBuilder currentValue = new StringBuilder();
        boolean inQuotes = false;
        List<String> currentRow = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                for (char character : line.toCharArray()) {
                    if (inQuotes) {
                        if (character == '"') {
                            if (currentValue.length() > 0 && currentValue.charAt(currentValue.length() - 1) == '"') {
                                currentValue.append('"');
                            } else {
                                inQuotes = false;
                            }
                        } else {
                            currentValue.append(character);
                        }
                    } else {
                        switch (character) {
                            case ',':
                                currentRow.add(currentValue.toString().trim());
                                currentValue.setLength(0);
                                break;
                            case '"':
                                inQuotes = true;
                                currentValue.append('"');
                                break;
                            default:
                                currentValue.append(character);
                        }
                    }
                }
                if (!inQuotes) {
                    currentRow.add(currentValue.toString().trim().replace("\\", "\\\\"));
                    currentValue.setLength(0);
                    rows.add(new ArrayList<>(currentRow));
                    currentRow.clear();
                } else {
                    currentValue.append("\\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (currentValue.length() > 0 || currentRow.size() > 0) {
            currentRow.add(currentValue.toString().trim().replace("\\", "\\\\"));
            rows.add(currentRow);
        }

        return rows;
    }
}
