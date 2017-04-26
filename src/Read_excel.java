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
            //t.xlsΪҪ��ȡ��excel�ļ���
            book= Workbook.getWorkbook(new File("F:/kk.xls")); 
            //��õ�һ�����������(ecxel��sheet�ı�Ŵ�0��ʼ,0,1,2,3,....)
            sheet=book.getSheet(0); 
            //��ȡ���Ͻǵĵ�Ԫ��
            cell1=sheet.getCell(0,0);
            System.out.println("���⣺"+cell1.getContents()); 
            
            i=1;

            while(true)
            {
                //��ȡÿһ�еĵ�Ԫ�� 
                cell1=sheet.getCell(0,i);//���У��У�
                
                cell2=sheet.getCell(1,i);
                cell3=sheet.getCell(2,i);
                if("".equals(cell1.getContents())==true)    //�����ȡ������Ϊ��
                    break;
                System.out.println(cell1.getContents()+"\t"+cell2.getContents()+"\t"+cell3.getContents()); 
                i++;
            }
            book.close(); 
        }
        catch(Exception e)  { } 
    }
}