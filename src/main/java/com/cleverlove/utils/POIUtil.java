package com.cleverlove.utils;


import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class POIUtil {
//    // 读取.xls文档
//    public static List<Student> readHSSFWorkbook(File file) {
//        List<Student> list = new ArrayList<>();
//        InputStream inputStream = null;
//        // 创建HSSFWorkbook对象
//        HSSFWorkbook workbook = null;
//        try {
//            inputStream = new FileInputStream(file);
//            workbook = new HSSFWorkbook(inputStream);
//            // 创建HSSFSheet对象
//            HSSFSheet sheet = workbook.getSheetAt(0);
//            // 获取表格行数
//            int rowNum = sheet.getLastRowNum();
//            System.out.println("rowNum=" + rowNum);
//            for (int i = 0; i <= rowNum; i++) {
//                HSSFRow row = sheet.getRow(i);
//                if (row == null) {
//                    continue;
//                }
//                Student student = new Student();
//                // 获取每行列数
//                int colNum = row.getLastCellNum();
//                String[] map = new String[colNum];
//                for (int j = 0; j < colNum; j++) {
//                    HSSFCell cell = row.getCell(j);
//                    if (cell == null) {
//                        continue;
//                    }
//                    map[j] = getHSSFCellValue(cell).toString();
//                }
//                student.setId(map[0]);
//                student.setName(map[1]);
//                student.setSex(map[2]);
//                student.setGrade(map[3]);
//                System.out.println(student);
//                list.add(student);
//            }
//        } catch (FileNotFoundException e) {
//            System.out.println("FileNotFoundException 加载数据失败");
//            e.printStackTrace();
//        } catch (IOException e) {
//            System.out.println("IOException 加载数据失败");
//            e.printStackTrace();
//        }
//        return list;
//    }

    private static Object getHSSFCellValue(HSSFCell cell) {
        Object value = null;
        switch (cell.getCellType()) {
            case _NONE:
                break;
            case NUMERIC: // 数字
                // 如果为时间格式的内容
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    // 注：format格式 yyyy-MM-dd hh:mm:ss 中小时为12小时制，若要24小时制，则把小h变为H即可，yyyy-MM-dd
                    // HH:mm:ss
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    value = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue())).toString();
                    break;
                } else {
                    value = new DecimalFormat("0").format(cell.getNumericCellValue());
                }
                break;
            case STRING: // 字符串
                value = cell.getStringCellValue();
                break;
            case BOOLEAN: // Boolean
                value = cell.getBooleanCellValue();
                break;
            case FORMULA: // 公式
                value = cell.getCellFormula();
                break;
            case BLANK: // 空值
                value = "";
                break;
            case ERROR: // 故障
                value = cell.getErrorCellValue();
                break;
            default:
                value = "未知类型";
                break;
        }
        return value;
    }
}
