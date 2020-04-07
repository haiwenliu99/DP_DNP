package util;



public interface MatrixInterface {

    /**
     * @return 行数
     */
    int getRowSize();

    /**
     * @return 列数
     */
    int getColumnSize();

    /**
     * @param x1 左上x
     * @param y1 左上y
     * @param x2 右下x
     * @param y2 右下y
     * @return 子矩阵
     */
    Matrix getSubMatrix(int x1, int y1, int x2, int y2);


    /**
     * @param rowFormat    行格式
     * @param columnFormat 列格式
     * @return 子矩阵
     * @throws IndexIllegalException 异常
     */
    Matrix getSubMatrix(String rowFormat, String columnFormat) throws IndexIllegalException;

    /**
     * @param rowFormat 行格式 :  0-9,9,10-*
     * @return 子矩阵
     * @throws IndexIllegalException 异常
     */
    Matrix getSubRowMatrix(String rowFormat) throws IndexIllegalException;

    /**
     * @param columnFormat 列格式 :  0-9,9,10-*
     * @return 子矩阵
     * @throws IndexIllegalException 异常
     */
    Matrix getSubColumnMatrix(String columnFormat) throws IndexIllegalException;

    /**
     * @return 转置矩阵
     */
    String[][] trans();


    /**
     * 范围检查
     *
     * @param m 行
     * @param n 列
     */
    void rangeCheck(int m, int n);

    /**
     * 检查行
     *
     * @param m 行
     */
    void rowCheck(int m);


    /**
     * 检查列
     *
     * @param n 列
     */
    void columnCheck(int n);
}
