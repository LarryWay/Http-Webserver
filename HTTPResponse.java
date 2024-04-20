import java.nio.file.Files;
import java.io.File;




public class HTTPResponse {

    private final String VERSION = "HTTP/1.1";
    private final String CHARSET = "UTF-8";
    private StatusCode status;
    private Integer contentBytesLength;
    private String content;
    private byte[] contentBytes;

    
    // In the future, this should not be the constructor
    public HTTPResponse(File index){
        status = StatusCode.OK_200;
        try{
            content = Files.readString(index.toPath());
            contentBytes = content.getBytes(CHARSET);
        }catch(Exception e){
            e.printStackTrace();
        }
        contentBytesLength = contentBytes.length;
    }



/* 
    -- Alternative constructor, but wont be pretty with html files
    public HTTPResponse(File file){
        status = StatusCode.OK_200;
        try(BufferedInputStream buff = new BufferedInputStream(new FileInputStream(file))){
            contentBytes = buff.readAllBytes();
            contentBytesLength = contentBytes.length;
        }catch(Exception e){

        }
    }
*/


    public HTTPResponse(byte[] rawBytes){
        contentBytes = rawBytes;
        content = String.valueOf(contentBytes);
        contentBytesLength = contentBytes.length;
        status = StatusCode.OK_200;
    }




    public HTTPResponse(StatusCode s){
        status = s;
        contentBytesLength = 0;
    }



    
    public byte[] toBytes(){
        StringBuilder builder = new StringBuilder();
        builder.append(VERSION);
        builder.append(StatusCode.statusToString(status));
        builder.append("\r\n");
        builder.append("Content Length: ");
        builder.append(Integer.toString(contentBytesLength));
        builder.append("\r\n\r\n");
        builder.append(content);

        try{
            return builder.toString().getBytes(CHARSET);
        }catch(Exception e){
            e.printStackTrace();
            return new byte[0];
        }
    }

    public byte[] toBytes(boolean useRawBytes){
        StringBuilder builder = new StringBuilder();
        builder.append(VERSION);
        builder.append(StatusCode.statusToString(status));
        builder.append("\r\n");
        builder.append("Content Length: ");
        builder.append(Integer.toString(contentBytesLength));
        builder.append("\r\n\r\n");

        byte[] headerBytes;

        try{
            headerBytes = builder.toString().getBytes(CHARSET);

            byte[] combinedBytes = new byte[contentBytes.length + headerBytes.length];
            System.arraycopy(headerBytes, 0, combinedBytes, 0, headerBytes.length);
            System.arraycopy(contentBytes, 0, combinedBytes, headerBytes.length, contentBytesLength);
            return combinedBytes;
        }catch(Exception e){
            e.printStackTrace();
            return new byte[0];
        }

    }

}


/* TODO
 * - Add content-type affiliation
 * - fix the toBytes scheme
 * 
 */