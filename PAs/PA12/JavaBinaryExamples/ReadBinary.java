import java.io.*;

public class ReadBinary {
    public static void main(String [] args) {

        // The name of the file to create.
        String fileName = "temp.dat";

        try {
            byte[] buffer = new byte[100];
            FileInputStream inputStream =
                new FileInputStream(fileName);

            int b = inputStream.read(buffer);

            // Always close files.
            inputStream.close();

            System.out.println("Read " + b + " bytes");
            for (int i = 0; i < b; i++) {
            	System.out.printf("%hh ", buffer[i]);
            }
            System.out.println();
            
            // a bit of work to convert to int (4 bytes per int value)
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '"
                + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
    }
}