package com.omg.evn.util.sysutil;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.Alignment;
import jxl.write.Label;
import jxl.write.VerticalAlignment;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.collections.BidiMap;

/**
 * @author : Aiowang
 * @Description: 用于excel统计表格生成
 * @JDK version ：jdk1.6
 */
public class StatisticsDown {
    private String sheetHead_1; // 标题一

    private String sheetHead_time; // 标题一下的时间。

    private List<String> sheetHead_2; // 标题二，所有显示字段

    private Map sheetHead_rel; // 标题分类对应关系。

    private BidiMap filedTitle_rel; // 中英分类对应关系。

    private int headRowHight = 600;// 标题行高

    private int contentRowHight = 300;// 标题行高

    private int contentRowStart = 1;// 开始行数

    private int cloumnWidthMaxSum = 80;// 列宽

    private int cloumnWidthMax = 20;// 列宽

    private int cloumnWidthMin = 8;// 列宽

    private static WritableCellFormat headerFormat_1 = null;// 表格标题

    private static WritableCellFormat headerFormat_time = null;// 表格标题

    private static WritableCellFormat headerFormat_2 = null;// 表格二级标题

    private static WritableCellFormat headerFormat_3 = null;// 表格三级标题

    private static WritableCellFormat contentFormat = null;// 正文

    /**
     * @param sheetHead_1 一级标题
     * @param sheetHead_time 时间（用于信箱时间的显示）
     * @param sheetHead_2 三级标题
     * @param sheetHead_rel 二、三级标题对应关系
     * @param filedTitle_rel 三级标题中英对应关系
     */
    public StatisticsDown(String sheetHead_1, String sheetHead_time, List<String> sheetHead_2, Map sheetHead_rel,
            BidiMap filedTitle_rel) {
        super();
        this.sheetHead_1 = sheetHead_1;
        this.sheetHead_time = sheetHead_time;
        this.sheetHead_2 = sheetHead_2;
        this.sheetHead_rel = sheetHead_rel;
        this.filedTitle_rel = filedTitle_rel;
    }

    /**
     * @Description: 获取系统当前时间，用于excel表格文件名生成（文件名+时间）
     * @param ：参数
     * @return：返回
     * @throws ：抛出异常
     */
    public static String getDateTime() {
        Date nowDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String dataTime = sdf.format(nowDate);
        return dataTime;
    }

    /**
     * @Description: 用于excel表格列宽的控制
     * @param ：参数
     * @return：返回
     * @throws ：抛出异常
     */
    
    /**
     * 
     * @Description: TODO
     * @param ：@param writSheet
     * @param ：@param getHead
     * @param ：@param resultList
     * @return：返回
     * @throws ：抛出异常
     */
    public void setMyColumnWidth(WritableSheet writSheet, List<String> getHead, List resultList) {
        int mycloumnWidthSum = getHead.size() * cloumnWidthMax;
        if (mycloumnWidthSum > cloumnWidthMaxSum) {
            cloumnWidthMaxSum = mycloumnWidthSum; // 根据选择字段数，自动化总列宽
        }
        String cloumnStringSum = "";
        int resultStringLength = cloumnWidthMax;
        int cloumn = 2; // 每个字符的长度。默认为1
        for (int i = 0; i < getHead.size(); i++) {
            for (int j = 0; j < resultList.size(); j++) {
                Map resultMapTemp = (Map) resultList.get(j);
                j = resultList.size() + 1;
                if (resultMapTemp.get(getHead.get(i)) != null) {
                    cloumnStringSum = cloumnStringSum + (String) resultMapTemp.get(getHead.get(i));
                    cloumn = cloumnWidthMaxSum / cloumnStringSum.length(); // 每个字符拥有的长度
                    resultStringLength = ((String) resultMapTemp.get(getHead.get(i))).length();
                }

                resultStringLength = resultStringLength * cloumn;
                if (resultStringLength > cloumnWidthMax) {
                    resultStringLength = cloumnWidthMax;
                } else if (resultStringLength < cloumnWidthMin) {
                    resultStringLength = cloumnWidthMin;
                }
                writSheet.setColumnView(i, resultStringLength);
            }
        }

    }

    // 初始化样式
    public static void initCellFormat() {
        try {
            // 设置excel标题一显示样式
            WritableFont tableFont_1 = new WritableFont(WritableFont.createFont("宋体"), 16, WritableFont.BOLD, false,
                    UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
            headerFormat_1 = new WritableCellFormat(tableFont_1);
            headerFormat_1.setVerticalAlignment(VerticalAlignment.CENTRE);
            headerFormat_1.setAlignment(Alignment.CENTRE);
            headerFormat_1.setBorder(jxl.format.Border.TOP, jxl.format.BorderLineStyle.THIN);// 设置上边框
            headerFormat_1.setBorder(jxl.format.Border.LEFT, jxl.format.BorderLineStyle.THIN);// 设置左边框
            headerFormat_1.setBorder(jxl.format.Border.RIGHT, jxl.format.BorderLineStyle.THIN);// 设置右边框

            // 设置时间显示样式
            WritableFont timefont = new WritableFont(WritableFont.createFont("宋体"), 12, WritableFont.NO_BOLD, false,
                    UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
            headerFormat_time = new WritableCellFormat(timefont);
            headerFormat_time.setVerticalAlignment(VerticalAlignment.CENTRE);
            headerFormat_time.setAlignment(Alignment.RIGHT);
            headerFormat_time.setBorder(Border.LEFT, BorderLineStyle.THIN);// 设置左边框
            headerFormat_time.setBorder(Border.RIGHT, BorderLineStyle.THIN);// 设置左边框
            headerFormat_time.setBorder(Border.BOTTOM, BorderLineStyle.THIN);// 设置左边框

            // 设置二级标题
            WritableFont tableFont_2 = new WritableFont(WritableFont.createFont("宋体"), 12, WritableFont.BOLD);
            headerFormat_2 = new WritableCellFormat(tableFont_2);
            headerFormat_2.setAlignment(Alignment.CENTRE);
            headerFormat_2.setVerticalAlignment(VerticalAlignment.CENTRE);
            headerFormat_2.setBorder(Border.ALL, BorderLineStyle.THIN);// 边框
            headerFormat_2.setWrap(true);

            // 设置三级标题
            WritableFont tableFont_3 = new WritableFont(WritableFont.createFont("宋体"), 12, WritableFont.BOLD);
            headerFormat_3 = new WritableCellFormat(tableFont_3);
            headerFormat_3.setAlignment(Alignment.CENTRE);
            headerFormat_3.setVerticalAlignment(VerticalAlignment.CENTRE);
            headerFormat_3.setBorder(Border.ALL, BorderLineStyle.THIN);// 边框
            headerFormat_3.setWrap(true);

            // 设置正文
            WritableFont centerFont = new WritableFont(WritableFont.createFont("宋体"), 10, WritableFont.NO_BOLD);
            contentFormat = new WritableCellFormat(centerFont);
            contentFormat.setAlignment(Alignment.CENTRE);
            contentFormat.setBorder(Border.ALL, BorderLineStyle.THIN);// 边框
            contentFormat.setWrap(true);
            contentFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            contentFormat.setAlignment(Alignment.CENTRE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 构建标题，根据传过来的list动态构建
    public List<String> getHead(List<String> getHeadList) {
        List<String> sheetHeadAll = this.sheetHead_2; // 获取所有标题
        List<String> headList = new ArrayList<String>();
        // headList = null;
        List<String> getFiledList = new ArrayList<String>();
        // getFiledList = null;
        // System.out.println("filedTitle_rel------" +
        // filedTitle_rel.toString());
        for (String string : getHeadList) {
            // System.out.println(string);
            // System.out.println("filedTitle_rel------" +
            // this.filedTitle_rel.get(string));
            getFiledList.add((String) (this.filedTitle_rel.get(string)));

        }
        if (getFiledList != null) {
            for (String StringTemp : sheetHeadAll) {
                for (String eachs : getFiledList) {
                    if (StringTemp.equals(eachs)) {
                        headList.add(eachs);
                    }
                }

            }
        }
        // System.out.println("headList---------" + headList);
        return headList;
    }

    // 表格生成，分2部分，一是表头，一是内容，需要传createHead返回来的list及list，map数据。
    public boolean createTable(HttpServletResponse response, List<String> getHead, List resultList) {
        try {
            // 构建excel表
            String dataTime = getDateTime();
            String fileName = this.sheetHead_1 + dataTime + ".xls";
            fileName = new String(fileName.getBytes("GB2312"), "8859_1");
            OutputStream os = response.getOutputStream();// 取得输出流
            response.reset();// 清空输出流
            response.setHeader("Content-disposition", "attachment; filename=" + fileName);// 设定输出文件头

            response.setContentType("application/msexcel");// 定义输出类型
            WritableWorkbook wbook = Workbook.createWorkbook(os); // 建立excel文件
            String tmptitle = this.sheetHead_1; // 标题
            WritableSheet wsheet = wbook.createSheet(tmptitle, 0); // sheet名称
            // 初始化表格样式
            initCellFormat();

            // 第一步构建表头，需要确定，一级标题，二级标题，三级标题，是否有时间显示
            // 1、一级标题，需要确定占几行columnSum.设置样式。
            // System.out.println(getHead.size()+"------------");
            int columnSum = getHead.size();
            wsheet.mergeCells(0, 0, columnSum - 1, 0);// 合并单元格
            // 设置行高
            wsheet.setRowView(0, headRowHight, false);
            wsheet.addCell(new Label(0, 0, this.sheetHead_1, headerFormat_1));

            // 第二步判断是否有时间显示
            if (sheetHead_time != null) {
                wsheet.mergeCells(0, contentRowStart, columnSum - 1, contentRowStart);// 合并单元格
                // 设置行高
                wsheet.setRowView(contentRowStart, contentRowHight, false);
                wsheet.addCell(new Label(0, contentRowStart, this.sheetHead_time, headerFormat_time));
                contentRowStart = contentRowStart + 1;
            }

            // 第三步，主体表头形成
            List<String> mygetHead = getHead(getHead);

            createHead(wsheet, mygetHead);

            // 第四步，数据写入
            for (int i = 0; i < resultList.size(); i++) {
                Map resultMapTemp = (Map) resultList.get(i);
                for (int j = 0; j < mygetHead.size(); j++) {
                    // 设置行高
                    wsheet.setRowView(contentRowStart + 2, contentRowHight, false);
                    wsheet.addCell(new Label(j, contentRowStart + 2 + i, (String) resultMapTemp
                            .get((String) (this.filedTitle_rel.getKey(mygetHead.get(j)))), contentFormat));
                }
            }
            // 设置列宽
            setMyColumnWidth(wsheet, getHead, resultList);

            // 主体内容生成结束
            wbook.write(); // 写入文件
            wbook.close();
            os.close(); // 关闭流
            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

    }

    // 主体二级，三级标题构建。
    public void createHead(WritableSheet writSheet, List<String> getHead) throws Exception {
        // 第一步，构建二级标题
        String head_2valuetemp = (String) this.sheetHead_rel.get(getHead.get(0));
        int colsum = getHead.size();
        int colsumeach = 0;
        int head_2colend = 0;
        int head_2colstart = 0;
        for (String getHeadString : getHead) {
            colsumeach = colsumeach + 1;
            // System.out.println(head_2valuetemp + colsumeach);
            if (head_2valuetemp.equals((String) this.sheetHead_rel.get(getHeadString))) {
                head_2colend = head_2colend + 1;
                if (head_2colend == colsum) {
                    writSheet.mergeCells(head_2colstart, contentRowStart, head_2colend - 1, contentRowStart);// 合并单元格
                    // 设置行高
                    writSheet.setRowView(contentRowStart, contentRowHight, false);
                    writSheet.addCell(new Label(head_2colstart, contentRowStart, head_2valuetemp, headerFormat_2));
                    head_2valuetemp = (String) this.sheetHead_rel.get(getHeadString);
                }
            } else {
                if (head_2colend != 0 && head_2colend - head_2colstart != 1 && colsumeach != colsum) {
                    writSheet.mergeCells(head_2colstart, contentRowStart, head_2colend - 1, contentRowStart);// 合并单元格
                    // 设置行高
                    writSheet.setRowView(contentRowStart, contentRowHight, false);
                    writSheet.addCell(new Label(head_2colstart, contentRowStart, head_2valuetemp, headerFormat_2));
                    head_2valuetemp = (String) this.sheetHead_rel.get(getHeadString);
                    head_2colstart = head_2colend;
                    head_2colend = head_2colend + 1;

                } else if (head_2colend - head_2colstart == 1 && colsumeach != colsum) {
                    writSheet.mergeCells(head_2colstart, contentRowStart, head_2colend - 1, contentRowStart);// 合并单元格
                    // 设置行高
                    writSheet.setRowView(contentRowStart, contentRowHight, false);
                    writSheet.addCell(new Label(head_2colstart, contentRowStart, head_2valuetemp, headerFormat_2));
                    head_2valuetemp = (String) this.sheetHead_rel.get(getHeadString);
                    head_2colstart = head_2colend;
                    head_2colend = head_2colend + 1;
                } else if (colsumeach == colsum) {

                    writSheet.mergeCells(head_2colstart, contentRowStart, head_2colend - 1, contentRowStart);// 合并单元格
                    // 设置行高
                    writSheet.setRowView(contentRowStart, contentRowHight, false);
                    writSheet.addCell(new Label(head_2colstart, contentRowStart, head_2valuetemp, headerFormat_2));
                    head_2valuetemp = (String) this.sheetHead_rel.get(getHeadString);
                    // System.out.println(head_2valuetemp + head_2colend);
                    head_2colstart = head_2colend;
                    writSheet.mergeCells(head_2colstart, contentRowStart, head_2colend, contentRowStart);// 合并单元格
                    // 设置行高
                    writSheet.setRowView(contentRowStart, contentRowHight, false);
                    writSheet.addCell(new Label(head_2colstart, contentRowStart, head_2valuetemp, headerFormat_2));

                }

            }

        }

        // 第三步，构建三级标题
        for (int i = 0; i < getHead.size(); i++) {
            // 设置行高
            writSheet.setRowView(contentRowStart + 1, contentRowHight * 2, false);
            writSheet.addCell(new Label(i, contentRowStart + 1, getHead.get(i), headerFormat_3));
        }
    }

}
