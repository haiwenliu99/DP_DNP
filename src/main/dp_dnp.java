package main;

import mechanisms.ExponentialMechanism;
import mechanisms.LaplaceMechanism;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataset;
import util.CommonUtil;
import util.ImagePojo;
import util.JFreeCharUtil;
import util.Matrix;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;



//算法dp_dnp的完整执行过程

public class dp_dnp {

	public static void main(String[] args) throws Exception {

		testDPHist();

		// testDP_RMSE();


		int k = 2;
		String destPath = "/Users/liuran/Downloads/DP_DNP/src/dataset/as-733/as-733.tar.gz";

		Scanner kb = null;
		int j = 1;
		try { kb = new Scanner(new File(destPath)); }
		catch (FileNotFoundException e) {
			System.out.println("file not found.");
			System.exit(0);
			j++;
		}


		int imageCount = 1;

		List<String> sList = new ArrayList<>();
		StringJoiner stringJoiner = new StringJoiner(",");
//		speedArray.forEach(jsonElement -> {
//			speedList.add(String.format("%.4f", jsonElement.getAsDouble()));
//			stringJoiner.add(String.format("%.1f", jsonElement.getAsDouble()));
//		});

		for (int i = 0; i < j; i++) {
			String[][] arr = getTableData(destPath, sList.get(j));

			List<ImagePojo> imageList = getImages(arr, imageCount, destPath);
		}
	}



	public static int PE_BinaryCount(int[] binaryData, double[] eArrASE) {

		// "budget" (in the context of DP) is always 1
		double budget = 1;

		// "sensitivity" here is also in the context of DP; for DP, the
		// "sensitivity" is encoded in "score"
		double sensitivity = 1;

		return ExponentialMechanism.run(scoreBinaryCount_v2(binaryData, eArrASE), budget, sensitivity);

	}
	


	// version 2 improve computational efficiency
	// binary data, count "1"
	// output the score corresponding to each count r in [0,sizeOfData]
	public static double[] scoreBinaryCount_v2(int[] data, double[] eArrASE) {

		int maxR_count = data.length;
		double[] scoreArr = new double[maxR_count + 1];

		int realCount = 0;

		// ★ for binary 0/1, count 1
		for (int i = 0; i < eArrASE.length; i++) {
			realCount = realCount + data[i];
		}

		// // need to change 0->1
		// ArrayList<Double> scoreArr_0_1 = new ArrayList<Double>();

		// // need to change 1->0
		// ArrayList<Double> scoreArr_1_0 = new ArrayList<Double>();

		double acc_0_1 = 0.0;
		double acc_1_0 = 0.0;

		int count_0_1 = 0;
		int count_1_0 = 0;

		for (int i = 0; i < data.length; i++) {
			if (data[i] == 1) {

				acc_1_0 = acc_1_0 - eArrASE[i];
				// scoreArr_1_0.add(acc_1_0);

				scoreArr[realCount - 1 - count_1_0] = acc_1_0;

				count_1_0 = count_1_0 + 1;

			} else {

				acc_0_1 = acc_0_1 - eArrASE[i];
				// scoreArr_0_1.add(acc_0_1);
				scoreArr[realCount + 1 + count_0_1] = acc_0_1;

				count_0_1 = count_0_1 + 1;
			}
		}

		return scoreArr;

	}


	// the same data set, the same privacy setting
	public static void testVaryThreshold() throws Exception {

		int testTimes = 3000;

		// get dataset
		int datasize = 1000;
		double p = 0.2;
		int[] data = getBinaryData(datasize, p);
		int realCount = calRawCountBinary(data);
		System.err.println("realCount=" + realCount);

		// get privacy setting
		double e_cons = 0.001;
		double e_moderate = 0.05;
		double e_libral = 0.1;
		double frac_c = 0.5;
		double frac_m = 0.3;
		double[] eArrASE = geteArr_ASE(datasize, e_cons, e_moderate, e_libral, frac_c, frac_m);

		// results
		ArrayList<double[]> doubleArrList = new ArrayList<double[]>();

		int testThrNum = 30;
		String[] labelOfEleInList = new String[testThrNum];
		double t_inv = 0.01;
		for (int i = 1; i <= testThrNum; i++) {
			double t = 0.01 + t_inv * i;
			labelOfEleInList[i - 1] = String.valueOf(CommonUtil.keepNdecimalDouble(t, 2));
			doubleArrList.add(testThrehold(data, eArrASE, testTimes, t));

		}

		String[] labelOfInsidElement = { "PE", "Sample-Lap", "Sample-PE", "Lap" };

		// String oString = CommonUtility.convertArrListToTable("varying
		// threshold", doubleArrList, "row", labelOfEleInList,
		// labelOfInsidElement);

		// System.out.println(oString);

	}

	public static void testVaryDatasize() throws Exception {
		int datasize_start = 500;
		int datasize_inv = 500;
		double p = 0.15;

		// privacy setting
		double e_cons = 0.01;
		double e_moderate = 0.5;
		double e_libral = 1.0;
		double frac_c = 0.5;
		double frac_m = 0.3;

		// System.out.println(CommonUtility.getStat(eArrASE));
		// System.out.println(Arrays.toString(eArrASE));

		int testTimes = 10;

		double Sample_threshold = 0;
		double[] cand_sample_t = { 0.1, 0.09, 0.08, 0.08, 0.06, 0.05, 0.04, 0.03, 0.02, 0.01 };

		int testDataSizeNum = 10;
		String[] labelOfEleInList = new String[testDataSizeNum];
		ArrayList<double[]> doubleArrList = new ArrayList<double[]>();
		for (int i = 0; i < testDataSizeNum; i++) {
			int datasize = datasize_start + datasize_inv * i;
			// == generate data
			int[] data = getBinaryData(datasize, p);

			// == generate privacy preferences
			double[] eArrASE = geteArr_ASE(datasize, e_cons, e_moderate, e_libral, frac_c, frac_m);

			labelOfEleInList[i] = "size=" + datasize;
			Sample_threshold = 0.08;
			doubleArrList.add(testThrehold(data, eArrASE, testTimes, Sample_threshold));
		}

		String[] labelOfInsidElement = { "PE", "Sample-Lap", "Sample-PE", "Lap" };

		String oString = CommonUtil.convertArrListToTable("varying datasize", doubleArrList, "row", labelOfEleInList,
				labelOfInsidElement);

		System.out.println(oString);
	}

	// p: data density, how many "1"

	public static double[] testThrehold(int[] data, double[] eArrASE, int testTimes, double Sample_threshold)
			throws Exception {

		boolean log = false;
		double sensitivity = 1;

		// 4 methods: PE, Sample-Lap, Sample-PE, Laplace
		double[] re = new double[4];

		int realCount = calRawCountBinary(data);

		if (log) {
			System.err.println("realCount=" + realCount);
		}

		int[] realAns = new int[testTimes];
		for (int i = 0; i < testTimes; i++) {
			realAns[i] = realCount;
		}

		// ★★★ result of PE NOTE: budget & sensitivity are 1.
		double[] re_PE = new double[testTimes];
		double[] re_Sample_lap = new double[testTimes];
		double[] re_Sample_PE = new double[testTimes];
		double[] re_lap = new double[testTimes];

		for (int i = 0; i < testTimes; i++) {
			re_PE[i] = PE_BinaryCount(data, eArrASE);
			re_Sample_lap[i] = Sample_count_lap(Sample_threshold, data, eArrASE, sensitivity);
			re_Sample_PE[i] = Sample_count_PE(Sample_threshold, data, eArrASE, sensitivity);
			re_lap[i] = LaplaceMechanism.addLaplaceNoise(realCount, CommonUtil.minElemInArr(eArrASE), sensitivity);
		}

		re[0] = CommonUtil.computeRMSE(re_PE, realAns);
		re[1] = CommonUtil.computeRMSE(re_Sample_lap, realAns);
		re[2] = CommonUtil.computeRMSE(re_Sample_PE, realAns);
		re[3] = CommonUtil.computeRMSE(re_lap, realAns);

		return re;
	}

	public static int calRawCountBinary(int[] data) {
		// == get real count
		int realCount = 0;
		// ★ for binary 0/1, count 1
		for (int i = 0; i < data.length; i++) {
			realCount = realCount + data[i];
		}
		return realCount;
	}

	public static double[] testEachPara(int size, double p, double e_min, double e_moderate, double e_libral,
			double frac_c, double frac_m, int testTimes, double Sample_threshold, double sensitivity) throws Exception {

		boolean log = false;

		int[] data = getBinaryData(size, p);
		double[] eArrASE = geteArr_ASE(size, e_min, e_moderate, e_libral, frac_c, frac_m);

		// 4 methods: PE, Sample-Lap, Sample-PE, Laplace
		double[] re = new double[4];

		int realCount = calRawCountBinary(data);

		if (log) {
			System.err.println("realCount=" + realCount);
		}

		int[] realAns = new int[testTimes];
		for (int i = 0; i < testTimes; i++) {
			realAns[i] = realCount;
		}

		long t0 = System.currentTimeMillis();

		// ★★★ result of PE NOTE: budget & sensitivity are 1.
		double[] re_PE = new double[testTimes];
		for (int i = 0; i < testTimes; i++) {
			int returnedCount = PE_BinaryCount(data, eArrASE);
			re_PE[i] = returnedCount;
		}

		long t1 = System.currentTimeMillis();

		re[0] = CommonUtil.computeRMSE(re_PE, realAns);

		if (log) {
			System.out.println("PE:" + re[0] + " time:" + (t1 - t0));
		}

		// ★★★ result of Sample-Lap

		// System.err.println("threshold=" + threshold);
		double[] re_Sample_lap = new double[testTimes];
		for (int i = 0; i < testTimes; i++) {
			re_Sample_lap[i] = Sample_count_lap(Sample_threshold, data, eArrASE, sensitivity);
		}
		long t2 = System.currentTimeMillis();

		re[1] = CommonUtil.computeRMSE(re_Sample_lap, realAns);

		if (log) {
			System.out.println("Sample-Lap:" + re[1] + " time:" + (t2 - t1));
		}

		// ★★★ result of Sample-PE (the same threhold as Sample-Lap)

		// double threshold = CommonUtility.getStat_mean(eArrASE);
		// System.err.println("threshold=" + threshold);
		double[] re_Sample_PE = new double[testTimes];
		for (int i = 0; i < testTimes; i++) {
			double returnedCount = Sample_count_PE(Sample_threshold, data, eArrASE, sensitivity);

			re_Sample_PE[i] = returnedCount;
		}
		long t3 = System.currentTimeMillis();

		re[2] = CommonUtil.computeRMSE(re_Sample_PE, realAns);
		if (log) {
			System.out.println("Sample-PE:" + re[2] + " time:" + (t3 - t2));
		}

		// ★★★ result of Laplace Mechanism
		double[] re_lap = new double[testTimes];

		double budget = eArrASE[0];
		for (int i = 0; i < testTimes; i++) {
			re_lap[i] = LaplaceMechanism.addLaplaceNoise(realCount, budget, sensitivity);
		}

		long t4 = System.currentTimeMillis();

		re[3] = CommonUtil.computeRMSE(re_lap, realAns);
		if (log) {
			System.out.println("Laplace:" + re[3] + " time:" + (t4 - t3));
		}

		return re;
	}

	public static void testDP_RMSE() throws Exception {

		int datasize = 10000;
		double sensitivity = 1;

		// == generate data
		double p = 0.15;
		int[] data = getBinaryData(datasize, p);

		// == generate privacy preferences
		double e_cons = 0.01;
		double e_moderate = 0.5;
		double e_libral = 1.0;

		double frac_c = 0.2;
		double frac_m = 0.37;

		double[] eArrASE = geteArr_ASE(datasize, e_cons, e_moderate, e_libral, frac_c, frac_m);
		// System.out.println(CommonUtility.getStat(eArrASE));
		// System.out.println(Arrays.toString(eArrASE));

		int realCount = calRawCountBinary(data);
		System.err.println("realCount=" + realCount);

		int testTimes = 1000;
		int[] realAns = new int[testTimes];
		for (int i = 0; i < testTimes; i++) {
			realAns[i] = realCount;
		}

		long t0 = System.currentTimeMillis();

		// ★★★ result of PE NOTE: budget & sensitivity are 1.
		double[] re_DP = new double[testTimes];
		for (int i = 0; i < testTimes; i++) {
			int returnedCount = PE_BinaryCount(data, eArrASE);
			re_DP[i] = returnedCount;
		}

		long t1 = System.currentTimeMillis();
		System.out.println("PE:" + CommonUtil.computeRMSE(re_DP, realAns) + " time:" + (t1 - t0));

		// ★★★ result of Sample-Lap
		double threshold = 0.1;
		// System.err.println("threshold=" + threshold);
		double[] re_Sample_lap = new double[testTimes];
		for (int i = 0; i < testTimes; i++) {
			re_Sample_lap[i] = Sample_count_lap(threshold, data, eArrASE, sensitivity);
		}
		long t2 = System.currentTimeMillis();
		System.out.println("Sample-Lap:" + CommonUtil.computeRMSE(re_Sample_lap, realAns) + " time:" + (t2 - t1));

		// ★★★ result of Sample-PE (the same threhold as Sample-Lap)

		// double threshold = CommonUtility.getStat_mean(eArrASE);
		// System.err.println("threshold=" + threshold);
		double[] re_Sample_PE = new double[testTimes];
		for (int i = 0; i < testTimes; i++) {
			double returnedCount = Sample_count_PE(threshold, data, eArrASE, sensitivity);

			re_Sample_PE[i] = returnedCount;
		}
		long t3 = System.currentTimeMillis();
		System.out.println("Sample-PE:" + CommonUtil.computeRMSE(re_Sample_PE, realAns) + " time:" + (t3 - t2));

		// ★★★ result of Laplace Mechanism
		double[] re_lap = new double[testTimes];
		double budget = eArrASE[0];
		for (int i = 0; i < testTimes; i++) {
			re_lap[i] = LaplaceMechanism.addLaplaceNoise(realCount, budget, sensitivity);
		}

		long t4 = System.currentTimeMillis();

		System.out.println("Laplace:" + CommonUtil.computeRMSE(re_lap, realAns) + " time:" + (t4 - t3));


	}

	// out put into Mathematica to see the histogram
	public static void testDPHist() throws IOException {

		int sensitivity = 1;

		// get data, E
		int datasize = 1000;
		double p = 0.5;
		int[] data = getBinaryData(datasize, p);

		// == generate privacy preferences
		double e_cons = 0.01;
		double e_moderate = 0.5;
		double e_libral = 1.0;
		double frac_c = 0.1;
		double frac_m = 0.37;
		double[] eArrASE = geteArr_ASE(datasize, e_cons, e_moderate, e_libral, frac_c, frac_m);
		// == generate over.

		int realCount = calRawCountBinary(data);

		System.out.println("realCount =" + realCount);

		// test PE
		StringBuffer o = new StringBuffer();
		for (int i = 0; i < 1000; i++) {
			int returnedID = PE_BinaryCount(data, eArrASE);
			o.append(returnedID).append(",");
		}
		CommonUtil.writeTxtToDisk(o.toString(), "/Users/liuran/Downloads/1.txt");
	}

	// game synthetic binary data "0"s and "1"s
	// p is fraction of "1"
	public static int[] getBinaryData(int size, double p) {
		int[] data = new int[size];

		for (int i = 0; i < size; i++) {
			data[i] = new Random().nextInt(100);
		}

		double threhold = p * 100;
		for (int i = 0; i < size; i++) {
			if (data[i] < threhold) {
				data[i] = 1;
			} else {
				data[i] = 0;
			}
		}

		return data;
	}


	// new version, more control
	public static double[] geteArr_ASE(int size, double e_min, double e_moderate, double e_libral, double frac_c,
			double frac_m) {
		double[] eArr = new double[size];

		for (int i = 0; i < size; i++) {
			double cc = new Random().nextDouble();
			if (cc <= frac_c) {
				eArr[i] = ThreadLocalRandom.current().nextDouble(e_min, e_moderate);
			} else if (cc <= frac_c + frac_m) {
				eArr[i] = ThreadLocalRandom.current().nextDouble(e_moderate, e_libral);
			} else {
				eArr[i] = e_libral;

			}

		}

		Arrays.sort(eArr); // ASE
		return eArr;

	}

	public static double Sample_count_PE(double threshold, int[] rawData, double[] eArr, double sensitivity)
			throws Exception {

		double[][] newDataAndeArr = Sample_data(threshold, rawData, eArr);
		double[] newData = newDataAndeArr[0];
		double[] neweArr = newDataAndeArr[1];

		int realCount = 0;
		for (int i = 0; i < newData.length; i++) {
			realCount = realCount + (int) newData[i];
		}

		return PE_BinaryCount(CommonUtil.doubleArr2intArr(newData), neweArr);

	}

	public static double Sample_count_lap(double threshold, int[] rawData, double[] eArr, double sensitivity)
			throws Exception {
		double[][] newDataAndeArr = Sample_data(threshold, rawData, eArr);
		double[] newData = newDataAndeArr[0];

		int realCount = 0;
		for (int i = 0; i < newData.length; i++) {
			realCount = realCount + (int) newData[i];
		}

		double e = threshold; // already ASE

		return LaplaceBinaryData(CommonUtil.doubleArr2intArr(newData), e, sensitivity);

	}

	public static double LaplaceBinaryData(int[] data, double budget, double sensitivity) throws Exception {

		int realCount = 0;
		for (int i = 0; i < data.length; i++) {
			realCount = realCount + (int) data[i];
		}

		return LaplaceMechanism.addLaplaceNoise(realCount, budget, sensitivity);

	}

	// sample mechanism return a new dataset
	public static double[][] Sample_data(double threshold, int[] rawData, double[] eArr) {
		int[] selectOrNot = new int[rawData.length];

		for (int i = 0; i < rawData.length; i++) {
			if (eArr[i] >= threshold) {
				selectOrNot[i] = 1;
			} else {
				double pr = (Math.exp(eArr[i]) - 1) / (Math.exp(threshold) - 1);
				if (new Random().nextInt(100000) < pr * 100000) {
					selectOrNot[i] = 1;
				} else {
					selectOrNot[i] = 0;
				}
			}
		}

		int sizeOfNewData = calRawCountBinary(selectOrNot);

		// new data & eArr
		double[][] newDataAndeArr = new double[2][sizeOfNewData];
		int idxOfNewData = 0;
		for (int i = 0; i < selectOrNot.length; i++) {
			if (selectOrNot[i] == 1) {
				newDataAndeArr[0][idxOfNewData] = rawData[i];
				newDataAndeArr[1][idxOfNewData] = eArr[i];
				idxOfNewData++;
			}
		}

		return newDataAndeArr;
	}


	private static List<ImagePojo> getImages(String[][] weiyi, int imageCount, String destPath) {
		String imageFolder = destPath + File.separator + "images";
		File file = new File(imageFolder);
		if (!file.exists()) {
			file.mkdir();
		}

		List<ImagePojo> imagePojoList = new ArrayList<>();

		imageCount = generateImage(weiyi, imageCount, imageFolder, imagePojoList);

		System.out.println("图片数量：" + imageCount);


		return imagePojoList;
	}


	private static int generateImage(String[][] data, int imageCount, String imageFolder, List<ImagePojo> list) {
		Matrix matrix = new Matrix(data);
		String[] title = matrix.getRow(0);
		String[][] dataset = matrix.removeRow(0);
		for (int i = 1; i < data[0].length; i++) {

			String name = "图" + imageCount + title[i] + "标记";
			JFreeCharUtil jFreeCharUtil = new JFreeCharUtil();
			jFreeCharUtil.title = "";
			jFreeCharUtil.xAxis_name = title[0];
			jFreeCharUtil.yAxis_name = "图" + title[i];
			jFreeCharUtil.picName = imageFolder + File.separator + name + ".png";//生成图片路径
			jFreeCharUtil.curve_num = 1;//曲线数目
			//第一条曲线（角标从1开始）
			jFreeCharUtil.lineCount = 1;
			jFreeCharUtil.curve_yAxis_name[1] = "图" + title[i];//曲线名称
			Matrix dM = new Matrix(dataset);
			Matrix pureData = new Matrix(dM.getTwoColToMatrix(0, i));
			Matrix transdata = new Matrix(pureData.trans());

			XYDataset datas = jFreeCharUtil.createXYDataset(transdata);
//
			//步骤3：根据Dataset 生成JFreeChart对象，以及做相应的设置

			JFreeChart freeChart = jFreeCharUtil.createChart(datas);

			//步骤4：将JFreeChart对象输出到文件，Servlet输出流等

			jFreeCharUtil.saveAsFile(freeChart, jFreeCharUtil.picName, 420, 280);
			ImagePojo imagePojo = new ImagePojo();
			imagePojo.setImageName(name);
			//imagePojo.setFormat(XWPFDocument.PICTURE_TYPE_PNG);
			imagePojo.setImageUrl(imageFolder + File.separator + name + ".png");
			imageCount++;
			list.add(imagePojo);
		}
		return imageCount;
	}
	private static String[][] getTableData(String destPath, String hangsu) throws IOException {
		String filePath = destPath + File.separator + "dataset" + File.separator + "的计算结果" + File.separator  + "具体的文件.TXT";
		//ReadFileToMatrix readFileToMatrix = new ReadFileToMatrix();
		File file = new File(filePath);
		//String[][] data = readFileToMatrix.readDataFromText(file, "GBK");

		String[] title = {"x", "y"};
		//replaceTitle(data, title);
		return null;
	}

}
