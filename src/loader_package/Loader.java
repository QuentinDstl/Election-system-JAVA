// https://www.mets-blog.com/java-read-excel-xlsx-file-part-1/
// https://docs.oracle.com/javase/tutorial/java/generics/bounded.html
package loader_package;

import config_package.Log;
import dao_package.*;
import java.io.IOException;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    private static <T extends DAO> void addToDataBase(int sheetType, String[] data,T t) {
        
        try {
            switch (sheetType) {
                case ELECTOR_SHEET:
                    t.addToTable(data[0],data[1],data[2],data[3],data[4],data[5]);
                    break;
                case CANDIDAT_SHEET:
                    t.addToTable(data[0],data[1],data[2],data[3],data[4]);
                    break;
                case STATE_SHEET:
                    t.addToTable(data[0],data[1],data[2],data[3]);
                    break;
                case OFFICIAL_SHEET:
                    t.addToTable(data[0],data[1],data[2]);
                    break;
                case ELECTION_SHEET:
                    t.addToTable(data[0]);
                    break;
                default:
                    throw new IllegalArgumentException("sheetType is not inside [0,4]");
            }
        }
        catch (SQLException | ArrayIndexOutOfBoundsException e) {
            Log.add("error SQL in addToDataBase in the Loader : " + e.getMessage());
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

    private static <T extends DAO> void loadSheet(InputStream input, List<String> sharedStrings, int sheetType, T t)
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
                addToDataBase(sheetType, cellValues, t);
                saved = true;
            }
        }
    }
    
    public static void loadXLSX(String filename) {
        
        Log.add("start laodXLSX");
        
        try (ZipFile zipFile = new ZipFile(filename)) {
            ArrayList<InputStream> list = getSheetsXML(zipFile);
            List<String> input = getSharedStrings(zipFile);
            
            final ElectorDAOImpl electorDAOImpl = new ElectorDAOImpl();
            initInTable(ELECTOR_SHEET, input, list, electorDAOImpl);
            
            final CandidateDAOImpl candidateDAOImpl = new CandidateDAOImpl();
            initInTable(CANDIDAT_SHEET, input, list, candidateDAOImpl);
            
            final StateDAOImpl stateDAOImpl = new StateDAOImpl();
            initInTable(STATE_SHEET, input, list, stateDAOImpl);
            
            final OfficialDAOImpl officialDAOImpl = new OfficialDAOImpl();
            initInTable(OFFICIAL_SHEET, input, list, officialDAOImpl);
            
            final ElectionDAOImpl electionDAOImpl = new ElectionDAOImpl();
            initInTable(ELECTION_SHEET, input, list, electionDAOImpl);
        }
        catch (IOException | XMLStreamException | FactoryConfigurationError e) {
            Log.add("The loadding of the xlsx file have problem : " + e.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
        }
        Log.add("end laodXLSX");
    }
    
    private static <T extends DAO> void initInTable(int sheetType, List<String> input, ArrayList<InputStream> list, T t)
            throws FactoryConfigurationError, IOException, SQLException, XMLStreamException {
        t.dropTable();
        t.createTable();
        loadSheet(list.get(sheetType), input, sheetType, t);
    }
}