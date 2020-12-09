package loader_package;

public class LoadingInfo {
    private int m_numberElement;
    private int m_sheetNumber;

    public LoadingInfo() {
        m_numberElement = 0;
        m_sheetNumber = 0;
    }
    private void add() {
        m_numberElement++;
    }
    
    public void printLoadInfo(String fileName, int sheetNumber) {
        System.out.print(String.format("\033[H\033[2J"));
        System.out.flush();
        System.out.print("Loading data from " + fileName + "\n");
    }

    public void addLoadInfo(int sheetNumber) {

        if(sheetNumber != m_sheetNumber) {
            System.out.println("");
            m_sheetNumber = sheetNumber;
            m_numberElement = 0;
        }

        this.add();
        for(int i=0; i<40; i++)
            System.out.print("\b");
        sheetNumber++;
        System.out.print("-> " + m_numberElement + " row load from sheet number " + sheetNumber);
    }
}
