package util;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 矩阵的基本运算
 *
 * @author MengQi
 * 2017-10-11 13:01
 */
@SuppressWarnings(value = "unused")
public class Matrix implements MatrixInterface {

    private Logger logger=Logger.getLogger(this.getClass().getName());
    private int rowNum;
    private int columnNum;
    private String[][] matrix;

    public Matrix() {
        super();
    }

    /**
     * 构造函数
     *
     * @param matrix 矩阵
     */
    public Matrix(String[][] matrix) {
        this.matrix = matrix;
        this.rowNum = matrix.length;
        if (matrix.length == 0) this.columnNum = 0;
        else this.columnNum = matrix[0].length;
    }

    /**
     * @return 获取行数
     */
    public int getRowNum() {
        return rowNum;
    }

    /**
     * 设置行数
     *
     * @param rowNum 行数
     */
    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    /**
     * @return 获取列数
     */
    public int getColumnNum() {
        return columnNum;
    }

    /**
     * @param columnNum 设置列数
     */
    public void setColumnNum(int columnNum) {
        this.columnNum = columnNum;
    }

    /**
     * @return 获取矩阵
     */
    public String[][] getMatrix() {
        return matrix;
    }

    /**
     * 设置矩阵
     *
     * @param matrix 矩阵
     */
    public void setMatrix(String[][] matrix) {
        this.matrix = matrix;
        this.rowNum = matrix.length;
        this.columnNum = matrix[0].length;
    }

    /**
     * 打印显示
     */
    public void print() {
        for (int i = 0; i < this.rowNum; i++) {
            for (int j = 0; j < this.columnNum; j++) {
                System.out.print(this.matrix[i][j] + " ");
            }
            System.out.println();
        }
    }


    /**
     * 保存
     *
     * @param filePath 路径
     */
    public void save(String filePath) {
        FileWriter writer = null;

        try {
            writer = new FileWriter(new File(filePath));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < this.rowNum; i++) {
                for (int j = 0; j < this.columnNum; j++) {
                    sb.append(this.matrix[i][j]).append(",");
                }
                sb.append("\r\n");
            }
            writer.write(sb.toString());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 获取行
     *
     * @param rowIndex 行索引
     * @return 行数组
     */
    public String[] getRow(int rowIndex) {
        int rowCount = this.matrix.length;
        if (rowIndex < rowCount) {
            return this.matrix[rowIndex];
        } else return null;
    }


    /**
     * 获取列
     *
     * @param columnIndex 列索引
     * @return 列数组
     */
    public String[] getColumn(int columnIndex) {
        int rowCount = this.matrix.length;
        String[] column = new String[rowCount];
        for (int i = 0; i < rowCount; i++) {
            column[i] = this.matrix[i][columnIndex];
        }
        return column;
    }


    /**
     * 判断数组是否全为零
     *
     * @param ss 目标数组
     * @return 结果
     */
    public boolean isZero(List<String> ss) {
        boolean flag = true;
        for (String s : ss) {
            if (Double.parseDouble(s) != 0) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    /**
     * @return 转为JsonArray
     */
    public JsonArray toJsonArray() {
        Gson gson = new Gson();
        return gson.fromJson(gson.toJson(this.matrix), JsonArray.class);
    }


    /**
     * 获取行两成为二维数组
     *
     * @param row1 第一行索引
     * @param row2 第二行索引
     * @return 二维数组
     */
    public String[][] getTwoRowToMatrix(int row1, int row2) {
        String[] r1 = this.getRow(row1);
        String[] r2 = this.getRow(row2);
        String[][] re = new String[2][this.getColumnNum()];
        re[0] = r1;
        re[1] = r2;
        return re;
    }


    /**
     * 获取两列为二维数组
     *
     * @param column1 第一列索引
     * @param column2 第二列索引
     * @return 二维数组
     */
    public String[][] getTwoColToMatrix(int column1, int column2) {

        String[] c1 = this.getColumn(column1);
        String[] c2 = this.getColumn(column2);
        String[][] re = new String[2][this.getColumnNum()];

        re[0] = c1;
        re[1] = c2;
        return re;
    }


    /**
     * 替换行
     *
     * @param index 行索引
     * @param data  新的数组
     */
    public void replaceRow(int index, String[] data) {
        System.arraycopy(data, 0, this.matrix[index], 0, this.matrix[index].length);
    }

    /**
     * 替换列
     *
     * @param index 列索引
     * @param data  新的数组
     */
    public void replaceColumn(int index, String[] data) {
        for (int i = 0; i < this.rowNum; i++) {
            for (int j = 0; j < this.columnNum; j++) {
                if (j == index) this.matrix[i][j] = data[i];
            }
        }
    }


    /**
     * 移除行
     *
     * @param index 行索引
     * @return 二维数组
     */
    public String[][] removeRow(int index) {
        String[][] re = new String[this.rowNum - 1][this.columnNum];
        int count = 0;
        for (int i = 0; i < this.rowNum; i++) {
            if (i == index) continue;
            re[count++] = this.getRow(i);
        }
        return re;
    }

    /**
     * 移除列
     *
     * @param index 列索引
     * @return 二维数组
     */
    public String[][] removeColumn(int index) {
        String[][] re = new String[this.rowNum][this.columnNum - 1];
        int count = 0;
        for (int i = 0; i < this.rowNum; i++) {
            for (int j = 0; j < this.columnNum; j++) {

                if (j == index) continue;
                if (j < index) re[i][j] = this.matrix[i][j];
                if (j > index) re[i][j - 1] = this.matrix[i][j];
            }
        }
        return re;
    }

    /**
     * 矩阵转置
     *
     * @return 二维数组
     */
    public String[][] trans() {
        String[][] result_arr = new String[this.columnNum][this.rowNum];
        //*******进行元素倒置******/
        for (int i = 0; i < this.rowNum; i++) {
            for (int j = 0; j < this.matrix[i].length; j++) {
                result_arr[j][i] = this.matrix[i][j]; //转置核心
            }
        }
        return result_arr;
    }


    /**
     * 新增行
     *
     * @param row 一维数组
     * @return 二维数组
     */
    public String[][] addOneRow(String[] row) {
        String[][] re = new String[this.rowNum + 1][this.columnNum];
        for (int i = 0; i < this.rowNum + 1; i++) {
            if (i == this.rowNum) {
                re[i] = row;
            } else if (i < this.rowNum) {
                re[i] = this.matrix[i];
            }
        }
        return re;
    }

    /**
     * 新增列
     *
     * @param column 一维数组
     * @return 二维数组
     */
    public String[][] addOneColumn(String[] column) {
        String[][] re = new String[this.rowNum][this.columnNum + 1];
        Matrix trancMatrix = new Matrix(this.trans());
        Matrix ma = new Matrix(trancMatrix.addOneRow(column));
        return ma.trans();
    }


    /**
     * 插入一行到自身
     *
     * @param index 行索引
     * @param row   行数组
     */
    public void insertOneRowToSelf(int index, String[] row) {
        String[][] re = new String[this.rowNum + 1][this.columnNum];
        for (int i = 0; i < this.rowNum + 1; i++) {
            if (i < index) {
                re[i] = this.matrix[i];
            } else if (i == index) {
                re[i] = row;
            } else {
                re[i] = this.matrix[i - 1];
            }
        }
        this.setMatrix(re);
    }

    /**
     * 插入一列到自身
     *
     * @param index  列索引
     * @param column 列数组
     */
    public void insertOneColumnToSelf(int index, String[] column) {
        String[][] re = new String[this.rowNum][this.columnNum + 1];
        Matrix trancMatrix = new Matrix(this.trans());
        trancMatrix.insertOneRowToSelf(index, column);
        trancMatrix.trans();
        this.setMatrix(trancMatrix.getMatrix());
    }


    /**
     * 增加一行到自身
     *
     * @param row 行数组
     */
    public void addOneRowToSelf(String[] row) {
        String[][] re = new String[this.rowNum + 1][this.columnNum];
        for (int i = 0; i < this.rowNum + 1; i++) {
            if (i == this.rowNum) {
                re[i] = row;
            } else if (i < this.rowNum) {
                re[i] = this.matrix[i];
            }
        }
        this.setMatrix(re);
    }

    /**
     * 新增一列到自身
     *
     * @param column 列数组
     */
    public void addOneColumnToSelf(String[] column) {
        String[][] re = new String[this.rowNum][this.columnNum + 1];
        Matrix trancMatrix = new Matrix(this.trans());
        Matrix ma = new Matrix(trancMatrix.addOneRow(column));
        this.setMatrix(ma.trans());
    }


    /**
     * 从自己移除列
     *
     * @param index 行索引
     */
    public void removeSelfColumn(int index) {
        String[][] re = new String[this.rowNum][this.columnNum - 1];
        int count = 0;
        for (int i = 0; i < this.rowNum; i++) {
            for (int j = 0; j < this.columnNum; j++) {
                if (j == index) continue;
                if (j < index) re[i][j] = this.matrix[i][j];
                if (j > index) re[i][j - 1] = this.matrix[i][j];
            }
        }

        Matrix matrixTemp = new Matrix(re);
        this.matrix = null;
        this.matrix = matrixTemp.matrix;
        this.columnNum = matrixTemp.columnNum;
        this.rowNum = matrixTemp.rowNum;
    }


    /**
     * 从自己移除行
     *
     * @param index 列索引
     */
    public void removeSelfRow(int index) {
        String[][] re = new String[this.rowNum - 1][this.columnNum];
        int count = 0;
        for (int i = 0; i < this.rowNum; i++) {
            if (i == index) continue;
            re[count++] = this.getRow(i);
        }
        Matrix matrixTemp = new Matrix(re);
        this.matrix = null;
        this.matrix = matrixTemp.matrix;
        this.columnNum = matrixTemp.columnNum;
        this.rowNum = matrixTemp.rowNum;
    }


    /**
     * 将list数据转为二维数组
     *
     * @param dataList list数据
     * @return 二维数组
     */
    public String[][] transDataToMatrix(List<String> dataList) {
        List<List<String>> dataMatrix = new ArrayList<>();
        for (String dataline : dataList) {
            dataline = dataline.replaceAll("\t", " ");
            String[] line = dataline.split(" ");
            List<String> list = new ArrayList<>();
            for (String s : line) {
                if (!s.equals("")) {
                    list.add(s);
                }
            }
            dataMatrix.add(list);
        }
        return listListToMatrix(dataMatrix);
    }


    /**
     * 二维数组转Matrix
     *
     * @param lists 二维list数据
     * @return 二维数组
     */
    public String[][] listListToMatrix(List<List<String>> lists) {

        int size = lists.size();
        int len = lists.get(0).size();
        System.out.println("size=" + size + ",  len=" + len);
        String[][] matrix = new String[size][len];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < len; j++) {
                matrix[i][j] = lists.get(i).get(j);
            }
        }

        return matrix;

    }


    @Override
    public int getRowSize() {
        return 0;
    }

    @Override
    public int getColumnSize() {
        return 0;
    }

    @Override
    public Matrix getSubMatrix(int x1, int y1, int x2, int y2) {
        rangeCheck(x1, y1);
        rangeCheck(x2, y2);
        int rowCount = x2 - x1;
        int columnCount = y2 - y1;
        String[][] subMatrix = new String[rowCount][columnCount];
        for (int i = x1; i < x2; i++) {
            System.arraycopy(matrix[x1], y1, subMatrix[i - x1], 0, columnCount);
        }
        return new Matrix(subMatrix);
    }

    @Override
    public Matrix getSubMatrix(String rowFormat, String columnFormat) throws IndexIllegalException {
        Integer[] indexRowIntegers = analysisComplexIndex(rowFormat, getRowSize());
        Integer[] indexColumnIntegers = analysisComplexIndex(columnFormat, getColumnSize());
        int m = indexRowIntegers.length;
        int n = indexColumnIntegers.length;
        String[][] objects = new String[m][n];
        int i, j;
        for (i = 0; i < m; i++) {
            for (j = 0; j < n; j++) {
                objects[i][j] = matrix[indexRowIntegers[i]][indexColumnIntegers[j]];
            }
        }
        return new Matrix(objects);
    }

    @Override
    public Matrix getSubRowMatrix(String rowFormat) throws IndexIllegalException {
        Integer[] indexIntegers = analysisComplexIndex(rowFormat, getRowSize());
        String[][] objects = new String[indexIntegers.length][getColumnSize()];
        for (int i = 0; i < indexIntegers.length; i++) {
            objects[i]=this.getRow(indexIntegers[i]);
        }
        return new Matrix(objects);
    }

    @Override
    public Matrix getSubColumnMatrix(String columnFormat) throws IndexIllegalException {

        String[][] trans = this.trans();
        Matrix newM = new Matrix(trans);
        Matrix sub = newM.getSubRowMatrix(columnFormat);
        return new Matrix(sub.trans());
    }


    @Override
    public void rangeCheck(int m, int n) {
        rowCheck(m);
        columnCheck(n);
    }

    @Override
    public void rowCheck(int m) {
        if (m >= getRowSize() || m < 0) {

            throw new IndexOutOfBoundsException("out of row length:" + m);
        }
    }

    @Override
    public void columnCheck(int n) {
        if (n >= getColumnSize() || n < 0) {

            throw new IndexOutOfBoundsException("out of column length:" + n);

        }
    }


    /**
     * 分析复杂的子矩阵索引规则，返回所有行下标
     *
     * @param complexIndex 复制字符串
     * @return 整型下标数组
     * @throws IndexIllegalException 索引字符串不符合规则异常
     */
    public Integer[] analysisComplexIndex(String complexIndex, int num) throws IndexIllegalException {

        String[] complexIndexs = complexIndex.split(",");
        for (String complexIndex1 : complexIndexs) {
            if (!isLegal(complexIndex1)) throw new IndexIllegalException("索引字符串不符合规则");
        }
        List<Integer> list = new ArrayList<>();
        for (String s : complexIndexs) {
            if (s.length() == 1) list.add(Integer.parseInt(s));
            else {
                String[] subs = s.split("-");
                if (subs[0].equals("*")) {
                    for (int i = 0; i < Integer.parseInt(subs[1]); i++) {
                        list.add(i);
                    }
                } else if (subs[1].equals("*")) {
                    for (int i = Integer.parseInt(subs[0]); i < num; i++) {
                        list.add(i);
                    }
                } else {
                    for (int i = Integer.parseInt(subs[0]); i < Integer.parseInt(subs[1]); i++) {
                        list.add(i);
                    }
                }
            }
        }
        Integer[] integers = new Integer[list.size()];
        list.toArray(integers);
        System.out.println(list);
        return integers;
    }

    /**
     * 判断输入的复制的子矩阵匹配规则是否合法
     *
     * @param complex 复制字符串
     * @return 正则表达式是否匹配
     */
    private boolean isLegal(String complex) {
        String s = "^(\\d+-\\d+|\\d+-\\*|\\*-\\d+|\\d+)$";
        Pattern pattern = Pattern.compile(s);
        Matcher matcher = pattern.matcher(complex);
        return matcher.find();
    }

}
