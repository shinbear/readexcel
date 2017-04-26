import java.io.File;
import jxl.*; 
import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.DateFormat;
import jxl.write.DateTime;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.Boolean;
import jxl.write.NumberFormat;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class Read_excel{
    public static void main(String[] args) {
        int i;
        Sheet sheet;
        Workbook book;
        Cell cell1,cell2,cell3;
        //System.out.println("*****");
        try { 
            //t.xls为要读取的excel文件名
            book= Workbook.getWorkbook(new File("F:/kk.xls")); 
            //获得第一个工作表对象(ecxel中sheet的编号从0开始,0,1,2,3,....)
            sheet=book.getSheet(0); 
            //获取左上角的单元格
            cell1=sheet.getCell(0,0);
            System.out.println("标题："+cell1.getContents()); 
            
            i=1;

            while(true)
            {
                //获取每一行的单元格 
                cell1=sheet.getCell(0,i);//（列，行）
                
                cell2=sheet.getCell(1,i);
                cell3=sheet.getCell(2,i);
                if("".equals(cell1.getContents())==true)    //如果读取的数据为空
                    break;
                System.out.println(cell1.getContents()+"\t"+cell2.getContents()+"\t"+cell3.getContents()); 
                i++;
            }
            book.close(); 
        }
        catch(Exception e)  { } 
    }
}