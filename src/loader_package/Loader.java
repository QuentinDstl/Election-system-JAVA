// https://www.mets-blog.com/java-read-excel-xlsx-file-part-1/
package loader_package;

import java.io.IOException;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class Loader implements LoaderInterface {
    
    private static int getSizeofSheet(int sheetType) throws IllegalArgumentException {
        switch (sheetType) {
            case ELECTOR_SHEET:
                return ELECTOR_SHEET_SIZE;
            case CANDIDAT_SHEET:
                return CANDIDAT_SHEET_SIZE;
            case STATE_SHEET:
                return STATE_SHEET_SIZE;
            case OFFICIAL_SHEET:
                return OFFICIAL_SHEET_SIZE;
            case ELECTION_SHEET:
                return ELECTION_SHEET_SIZE;
            default:
                throw new IllegalArgumentException("sheetType is not inside [0,4]");
        }
    }
    
    private static void addToDataBase(int sheetType, String[] data) {
        
        switch (sheetType) {
            case ELECTOR_SHEET:
                for (String data1 : data) {
                    //add_elector(data[1],data[2],data[3])
                    System.out.print(data1 + " - ");
                }
                break;
            case CANDIDAT_SHEET:
                for(int i =0; i< data.length; i++) {
                    System.out.print(data[i] + " - ");
                }
                break;
            case STATE_SHEET:
                for(int i =0; i< data.length; i++) {
                    System.out.print(data[i] + " - ");
                }
                break;
            case OFFICIAL_SHEET:
                for(int i =0; i< data.length; i++) {
                    System.out.print(data[i] + " - ");
                }
                break;
            case ELECTION_SHEET:
                for(int i =0; i< data.length; i++) {
                    System.out.print(data[i] + " - ");
                }
                break;
            default:
                throw new IllegalArgumentException("sheetType is not inside [0,4]");
        }
    }
    
    private static ArrayList<InputStream> getSheetsXML(ZipFile zipFile) throws IOException  {

        Enumeration<? extends ZipEntry> enumeration = zipFile.entries();
        ArrayList<InputStream> sheets = new ArrayList<>();
        ZipEntry zipEntry = null;

        while(enumeration.hasMoreElements()){

            zipEntry = enumeration.nextElement();

            if(zipEntry.getName().contains("sheet")){
                sheets.add(zipFile.getInputStream((zipEntry)));
            }
        }
        return sheets;
    }

    private static List<String> getSharedStrings(ZipFile zipFile) throws IOException, XMLStreamException  {

        Enumeration<? extends ZipEntry> enumeration = zipFile.entries();
        InputStream strings = null;
        ZipEntry zipEntry = null;

        while(enumeration.hasMoreElements()){

        zipEntry = enumeration.nextElement();

            if(zipEntry.getName().contains("sharedStrings")){

                strings = zipFile.getInputStream(zipEntry);
                break;
            }
        }

        /* Now we have the xml stream with all unique strings, I'll use stax to read the xml and add them to a list */
        ArrayList<String> stringList = new ArrayList<>();
        XMLStreamReader xmlStreamReader = XMLInputFactory.newInstance().createXMLStreamReader(strings);

        while (xmlStreamReader.hasNext()) {

            xmlStreamReader.next();                                         // go to next event
            /* the current event is characters and the content is not all white space */
            if ((xmlStreamReader.getEventType() == XMLStreamConstants.CHARACTERS)
                    && (xmlStreamReader.getText().trim().length() > 0)) {
                stringList.add(xmlStreamReader.getText());
            }
        }
        xmlStreamReader.close();

        return stringList;
    }

    private static void loadSheet(InputStream input, List<String> sharedStrings, int sheetType)
            throws XMLStreamException, FactoryConfigurationError, IOException, IllegalArgumentException {

        int sizeOfCells = getSizeofSheet(sheetType);
        String[] cellValues = new String[getSizeofSheet(sheetType)];
        int count = 0;
        boolean isString = false, saved = true;
        XMLStreamReader xmlStreamReader = XMLInputFactory.newInstance().createXMLStreamReader(input);

        while (xmlStreamReader.hasNext()) {
            xmlStreamReader.next();

            if (xmlStreamReader.getEventType() == XMLStreamConstants.START_ELEMENT) {
                if (xmlStreamReader.getLocalName().equals("c") && xmlStreamReader.getAttributeCount() > 0) {
                    for (int i = 0; i < xmlStreamReader.getAttributeCount(); i++) {
                        if( xmlStreamReader.getAttributeLocalName(i).equals("t")
                            && xmlStreamReader.getAttributeValue(i).equals("s")) {
                            isString = true;
                        }
                    }
                }
            }
            if ((xmlStreamReader.getEventType() == XMLStreamConstants.CHARACTERS)
                    && (xmlStreamReader.getText().trim().length() > 0)) {
                if(isString){
                    int sharedStringKey = Integer.valueOf(xmlStreamReader.getText());
                    cellValues[count%sizeOfCells] = sharedStrings.get(sharedStringKey);
                    isString = false;

                    count++;
                    if(count%sizeOfCells == 0)
                        saved = false;
                }
                /* the data is not a string */
                else {
                    cellValues[count%sizeOfCells] = xmlStreamReader.getText();

                    count++;
                    if(count%sizeOfCells == 0)
                        saved = false;
                }
            }
            if(!saved) {
                addToDataBase(sheetType, cellValues);
                saved = true;
            }
        }
    }
    
    public static void loadXLSX(String filename) {
        
        try (ZipFile zipFile = new ZipFile(filename)) {
            ArrayList<InputStream> list = getSheetsXML(zipFile);
            List<String> input = getSharedStrings(zipFile);
            for(int i = 0; i<5; i++) {
                loadSheet(list.get(i), input, i);   // iterate in a loop for all sheets
            }
        }
        catch (IOException | XMLStreamException | FactoryConfigurationError e) {
        }
    }
}

