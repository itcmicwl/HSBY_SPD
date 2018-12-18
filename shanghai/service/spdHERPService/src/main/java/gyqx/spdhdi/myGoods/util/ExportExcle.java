package gyqx.spdhdi.myGoods.util;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.util.StringUtils;

import common.utils.EntityUtils;
import gyqx.spdhdi.po.ProvGoodsInfo;

public class ExportExcle<T> {
	public void exportExcel(String[] headers, Collection<T> dataset,
			OutputStream out) throws Exception{
		exportExcel("测试POI导出EXCEL文档", headers, dataset, out, "yyyy-MM-dd");
		//JOptionPane.showMessageDialog(null, "导出成功!");
	}
	
	public void exportExcel(String title, String[] headers,
			Collection<T> dataset, OutputStream out, String pattern)  throws Exception{
	
		   
		
		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 生成一个表格
		HSSFSheet sheet = workbook.createSheet(title);
		// 设置表格默认列宽度为15个字节
		sheet.setDefaultColumnWidth((short) 15);
		// 生成一个样式
		HSSFCellStyle style = workbook.createCellStyle();
		// 设置这些样式
		style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
	
		// 生成一个字体
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.VIOLET.index);
		font.setFontHeightInPoints((short) 12);
	
		// 把字体应用到当前的样式
		style.setFont(font);
		// 生成并设置另一个样式
		HSSFCellStyle style2 = workbook.createCellStyle();
		style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
	
	
		// 声明一个画图的顶级管理器
		HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
		// 定义注释的大小和位置,详见文档
		HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0,
				0, 0, 0, (short) 4, 2, (short) 6, 5));
		// 设置注释内容
		comment.setString(new HSSFRichTextString("可以在POI中添加注释！"));
		// 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.
		comment.setAuthor("leno");
		// 产生表格标题行
		HSSFRow row = sheet.createRow(0);
		for (short i = 0; i < headers.length; i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellStyle(style);
			HSSFRichTextString text = new HSSFRichTextString(headers[i]);
			cell.setCellValue(text);
		}
		// 遍历集合数据，产生数据行
		Iterator<T> it = dataset.iterator();
		int index = 0;
		while (it.hasNext()) {
			index++;
			row = sheet.createRow(index);
			T t = (T) it.next();
			 Field[] fields = t.getClass().getDeclaredFields();
			for(int j=0;j<headers.length;j++){
				   for(int k=0;k<fields.length;k++){
					
					    String  theField = fields[k].toString();
						theField =theField.substring(theField.lastIndexOf(".")+1,theField.length());
						String theFieldtTitle=	EntityUtils.getFieldCName(t.getClass(), theField);
						   if(theFieldtTitle.equals(headers[j])){
								HSSFCell cell = row.createCell(j);
								cell.setCellStyle(style2);
								Field field =fields[k];
								String fieldName = field.getName();
						
								String getMethodName = "get"+ fieldName.substring(0, 1).toUpperCase()+ fieldName.substring(1);
									try {
										    Class tCls = t.getClass();
											Method getMethod = tCls.getMethod(getMethodName,new Class[] {});
											Object value = getMethod.invoke(t, new Object[] {});
											// 判断值的类型后进行强制类型转换
											String textValue = null;
										    if (value instanceof Date) {
												Date date = (Date) value;
												SimpleDateFormat sdf = new SimpleDateFormat(pattern);
												textValue = sdf.format(date);
											} else if (value instanceof byte[]) {
												// 有图片时，设置行高为60px;
												row.setHeightInPoints(60);
												// 设置图片所在列宽度为80px,注意这里单位的一个换算
												sheet.setColumnWidth(j, (short) (35.7 * 80));
												// sheet.autoSizeColumn(i);
												byte[] bsValue = (byte[]) value;
												HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0,
														1023, 255, (short) 6, index, (short) 6, index);
											
												patriarch.createPicture(anchor, workbook.addPicture(
														bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));
											} else {
												// 其它数据类型都当作字符串简单处理
											
												if(!StringUtils.isEmpty(value))
												textValue = value.toString();
											}
											// 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
											if (textValue != null) {
												Pattern p = Pattern.compile("^//d+(//.//d+)?$");
												Matcher matcher = p.matcher(textValue);
												if (matcher.matches()) {
													// 是数字当作double处理
													cell.setCellValue(Double.parseDouble(textValue));
												} else {
													HSSFRichTextString richString = new HSSFRichTextString(
															textValue);
													HSSFFont font3 = workbook.createFont();
													font3.setColor(HSSFColor.BLUE.index);
													richString.applyFont(font3);
													cell.setCellValue(richString);
												}
						    	             }
								} catch (SecurityException e) {
									e.printStackTrace();
								} catch (NoSuchMethodException e) {
									e.printStackTrace();
								
								} catch (IllegalArgumentException e) {
									e.printStackTrace();
								} catch (IllegalAccessException e) {
									e.printStackTrace();
								} catch (InvocationTargetException e) {
									e.printStackTrace();
								} finally {
									// 清理资源
								}
									   
						
			 }
						   
						
				   }
			}
			
			
			
								
			}
						
						   
						
				
	
		
		try {
			workbook.write(out);
			out.close();
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
