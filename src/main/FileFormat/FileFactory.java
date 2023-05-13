package main.FileFormat;
public class FileFactory {
    public static FormatFile makeFile(String fileType){
        if (fileType.contains(".xml"))
            return new XmlFile();
        else if (fileType.contains(".json"))
            return new JsonFile();
        else if (fileType.contains(".csv"))
            return new CsvFile();
        else
            return null;

    }
}
