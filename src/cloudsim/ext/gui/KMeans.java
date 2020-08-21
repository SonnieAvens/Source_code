
package cloudsim.ext.gui;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Stream;

public class KMeans {

    static void makeClustering() throws IOException
    {
        	String filePath = "H:\\Scheduling_Heterogeneous_IoT_Tasks\\";		
		String fileName = "ontology.txt";
               Scanner sc = new Scanner(System.in);		
		int records = 30;
		int xAttribute = Integer.parseInt("1");
		int yAttribute = Integer.parseInt("1");;
		double[][] points = new double[records][2];
		sortPointsByX(points);
		int maxIterations = Integer.parseInt("20");;
		int clusters = Integer.parseInt("11");;
		double[][] means = new double[clusters][2];
		for(int i=0; i<means.length; i++) {
			means[i][0] = points[(int) (Math.floor((records*1.0/clusters)/2) + i*records/clusters)][0];
			means[i][1] = points[(int) (Math.floor((records*1.0/clusters)/2) + i*records/clusters)][1];
		}
		ArrayList<Integer>[] oldClusters = new ArrayList[clusters];
		ArrayList<Integer>[] newClusters = new ArrayList[clusters];

		for(int i=0; i<clusters; i++) {
			oldClusters[i] = new ArrayList<Integer>();
			newClusters[i] = new ArrayList<Integer>();
		}
		formClusters(oldClusters, means, points);
		int iterations = 0;
		while(true) {
			updateMeans(oldClusters, means, points);
			formClusters(newClusters, means, points);

			iterations++;

			if(iterations > maxIterations || checkEquality(oldClusters, newClusters))
				break;
			else
				resetClusters(oldClusters, newClusters);
		}             
                
                System.out.println("\nData Clustering Process Completed Successfully by using k means........");
                             
    }
		static int getRecords(String filePath, String fileName) throws IOException {
		int records = 1;
		BufferedReader br = new BufferedReader(new FileReader(filePath + fileName + ".csv"));
		while (br.readLine() != null)
			records++;
		br.close();
		return records;
	}       
	static void readRecords(String filePath, String fileName, double[][] points, int xAttribute, int yAttribute) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filePath + fileName + ".csv"));
		String line=br.readLine();
		int i = 0;
		while ((line = br.readLine()) != null) {
			points[i][0] = Double.parseDouble(line.split(",")[xAttribute]);
			points[i++][1] = Double.parseDouble(line.split(",")[yAttribute]);
		}
		br.close();
	}
	static void sortPointsByX(double[][] points) {
		double[] temp;

		// Bubble Sort
		for(int i=0; i<points.length; i++)
		    for(int j=1; j<(points.length-i); j++)
			if(points[j-1][0] > points[j][0]) {
			    temp = points[j-1];
			    points[j-1] = points[j];
			    points[j] = temp;
			}
	}

	static void updateMeans(ArrayList<Integer>[] clusterList, double[][] means, double[][] points) {
		double totalX = 0;
		double totalY = 0;
		for(int i=0; i<clusterList.length; i++) {
			totalX = 0;
			totalY = 0;
			for(int index: clusterList[i]) {
				totalX += points[index][0];
				totalY += points[index][1];
			}
			means[i][0] = totalX/clusterList[i].size();
			means[i][1] = totalY/clusterList[i].size();
		}
	}

	static void formClusters(ArrayList<Integer>[] clusterList, double[][] means, double[][] points) {
		double distance[] = new double[means.length];
		double minDistance = 999999999;
		int minIndex = 0;

		for(int i=0; i<points.length; i++) {
			minDistance = 999999999;
			for(int j=0; j<means.length; j++) {
				distance[j] = Math.sqrt(Math.pow((points[i][0] - means[j][0]), 2) + Math.pow((points[i][1] - means[j][1]), 2));
				if(distance[j] < minDistance) {
					minDistance = distance[j];
					minIndex = j;
				}
			}
			clusterList[minIndex].add(i);
		}
	}

	static boolean checkEquality(ArrayList<Integer>[] oldClusters, ArrayList<Integer>[] newClusters) {
		for(int i=0; i<oldClusters.length; i++) {
			// Check only lengths first
			if(oldClusters[i].size() != newClusters[i].size())
				return false;

			// Check individual values if lengths are equal
			for(int j=0; j<oldClusters[i].size(); j++)
				if(oldClusters[i].get(j) != newClusters[i].get(j))
					return false;
		}

		return true;
	}

	static void resetClusters(ArrayList<Integer>[] oldClusters, ArrayList<Integer>[] newClusters) {
		for(int i=0; i<newClusters.length; i++) {
			// Copy newClusters to oldClusters
			oldClusters[i].clear();
			for(int index: newClusters[i])
				oldClusters[i].add(index);

			// Clear newClusters
			newClusters[i].clear();
		}
	}

	static void displayOutput(ArrayList<Integer>[] clusterList, double[][] points) {
		for(int i=0; i<clusterList.length; i++) {
			String clusterOutput = "\n\n[";
			for(int index: clusterList[i])
				clusterOutput += "(" + points[index][0] + ", " + points[index][1] + "), ";
			System.out.println(clusterOutput.substring(0, clusterOutput.length()-2) + "]");
		
                }
	}
}
