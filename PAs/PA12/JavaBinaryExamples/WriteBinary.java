import java.io.*;

public class WriteBinary {
    public static void main(String [] args) {

        // The name of the file to create.
        String fileName = "temp.dat";

        try {
            // Put some bytes in a buffer so we can
            // write them. Usually this would be
            // image data or something. Or it might
            // be unicode text.
            int []iValues = {123, 10, 5};
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
		    DataOutputStream dos = new DataOutputStream(baos);

		    try
    		{
        		for(int i : iValues)
            		dos.writeInt(i);
		    }
		    catch(Exception ex)
		    {
		        ex.printStackTrace();
		    }
		    byte[] arr = baos.toByteArray();

            FileOutputStream outputStream = new FileOutputStream(fileName);

            // write() writes as many bytes from the buffer
            // as the length of the buffer. You can also
            // use
            // write(buffer, offset, length)
            // if you want to write a specific number of
            // bytes, or only part of the buffer.
            //outputStream.write(buffer);
            outputStream.write(arr);

            // Always close files.
            outputStream.close();

            System.out.println("Wrote " + arr.length +
                " bytes");
        }
        catch(IOException ex) {
            System.out.println(
                "Error writing file '"
                + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
    }
}