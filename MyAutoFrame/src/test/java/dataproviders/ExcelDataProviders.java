package dataproviders;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExcelDataProviders {
    private final String datapath = "resources/testdata/";

    private String getDataFileName( Method method ) {
        String fullname = method.toGenericString();
        String withoutbrace  = fullname.split("\\(")[0];
        String[] tempArray = withoutbrace.trim().split(" ");
        String methodNameWithPackage = tempArray[ tempArray.length -1 ];
        int splipos = methodNameWithPackage.lastIndexOf(".");
        String filepath = methodNameWithPackage.substring( 0, splipos );
        return  filepath.replace(".", "/") + ".xlsx";
    }

    @DataProvider( name = "excelToHashMap" )
    public Object[][] excelToHashMapDataprovider( Method method ) throws IOException {

        List<HashMap<String,String>> listdataHashMap = new ArrayList();

        String sheetName = method.getName();
        String fileName = getDataFileName( method );

        FileInputStream file = new FileInputStream(new File( datapath  + fileName));

        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheet( sheetName );
        Row header = sheet.getRow( 0 );

        for(int i=1; i<=sheet.getLastRowNum(); i++){

            Row row = sheet.getRow(i);
            HashMap<String, String> tempHashMap = new HashMap<String, String>();

            for( int j = 0; j< row.getLastCellNum(); j++){
                Cell valueCell = row.getCell(j);
                Cell headerCell = header.getCell( j );
                tempHashMap.put( headerCell.toString() , valueCell.toString() );
            }

            listdataHashMap.add( tempHashMap );

        }

        file.close();

        Object[][] data = new Object[ listdataHashMap.size() ][];
        int counter = 0;

        for( HashMap<String, String> hash : listdataHashMap ) {
            data[ counter++ ] = new Object[] { hash };
        }

        return data;
    }
}
