package loader_package;

public class LoadingInfo {
    private int m_numberElement;
    private int m_sheetNumber;
    private static int m_numberState;

    public LoadingInfo() {
        m_numberElement = 0;
        m_sheetNumber = 0;
    }
    private void add() {
        m_numberElement++;
    }
    
    public void printLoadInfo(String fileName, int sheetNumber) {
        System.out.print(String.format("\033[2J"));
        System.out.print("Loading data from " + fileName + "\n");
    }

    public void addLoadInfo(int sheetNumber) {

        if(sheetNumber != m_sheetNumber) {
            if(m_sheetNumber == 2)
                m_numberState = m_numberElement;
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

    public static int getStateNumber() {
        return m_numberState;
    }
}
