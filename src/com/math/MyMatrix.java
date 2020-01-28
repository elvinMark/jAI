package com.math;

import java.util.*;

public class MyMatrix{
	protected int rows;
	protected int cols;
	protected double[][] data;

	public MyMatrix(int rows,int cols){
		this.rows = rows;
		this.cols = cols;
		this.data = new double[rows][cols];
	}
	public MyMatrix(int[] dim){
		this.rows = dim[0];
		this.cols = dim[1];
		this.data = new double[this.rows][this.cols];
	}

	public String toString(){
		String s = "MyMatrix\n";
		for(double[] row : this.data){
			for(double elem : row)
				s += elem + " ";
			s += "\n";
		}
		return s;
	}
	public int[] getSize(){
		int[] dim;
		dim = new int[2];
		dim[0] = this.rows;
		dim[1] = this.cols;
		return dim;
	}
	public int getRow(){
		return this.rows;
	}
	public int getCol(){
		return this.cols;
	}
	public void random(){
		Random r = new Random();
		for(int i = 0;i<this.rows;i++)
			for(int j = 0;j<this.cols;j++)
				this.data[i][j] = r.nextDouble();
	}
	public void setData(double[][] data){
		this.data = data;
	}
	public void ones(){
		for(int i = 0;i<this.rows;i++)
			for(int j = 0;j<this.cols;j++)
				this.data[i][j] = 1;	
	}
	public void zeros(){
		for(int i = 0;i<this.rows;i++)
			for(int j = 0;j<this.cols;j++)
				this.data[i][j] = 0;	
	}
	public MyMatrix transpose(){
		MyMatrix tmp = new MyMatrix(this.cols,this.rows);
		for(int i = 0;i<this.cols;i++)
			for(int j = 0;j<this.rows;j++)
				tmp.data[i][j] = this.data[j][i];	
		return tmp;
	}
	public MyMatrix sum(MyMatrix m){
		MyMatrix tmp = new MyMatrix(this.rows,this.cols);
		for(int i = 0;i<this.rows;i++)
			for(int j = 0;j<this.cols;j++)
				tmp.data[i][j] = this.data[i][j] + m.data[i][j];
		return tmp;
	}
	public MyMatrix diff(MyMatrix m){
		MyMatrix tmp = new MyMatrix(this.rows,this.cols);
		for(int i = 0;i<this.rows;i++)
			for(int j = 0;j<this.cols;j++)
				tmp.data[i][j] = this.data[i][j] - m.data[i][j];
		return tmp;
	}
	public MyMatrix times(MyMatrix m){
		MyMatrix tmp = new MyMatrix(this.rows,this.cols);
		for(int i = 0;i<this.rows;i++)
			for(int j = 0;j<this.cols;j++)
				tmp.data[i][j] = this.data[i][j] * m.data[i][j];
		return tmp;
	}
	public MyMatrix times(double num){
		MyMatrix tmp = new MyMatrix(this.rows,this.cols);
		for(int i = 0;i<this.rows;i++)
			for(int j = 0;j<this.cols;j++)
				tmp.data[i][j] = this.data[i][j]*num;
		return tmp;
	}
	public MyMatrix dot(MyMatrix m){
		MyMatrix tmp = new MyMatrix(this.rows,m.cols);
		double s;
		for(int i = 0;i<this.rows;i++){
			for(int j = 0;j<m.cols;j++){
				s = 0;
				for(int k = 0;k<this.cols;k++)
					s += this.data[i][k] * m.data[k][j];
				tmp.data[i][j] = s;
			}
		}
		return tmp;
	}
	public static MyMatrix exp(MyMatrix m){
		MyMatrix tmp = new MyMatrix(m.getSize());
		for(int i = 0;i<tmp.rows;i++){
			for(int j = 0;j<tmp.cols;j++)
				tmp.data[i][j] = Math.exp(m.data[i][j]);
		}
		return tmp;
	}
	public static MyMatrix applyFun(MyFunction f,MyMatrix m){
		MyMatrix tmp = new MyMatrix(m.getSize());
		for(int i = 0;i<tmp.rows;i++){
			for(int j = 0;j<tmp.cols;j++)
				tmp.data[i][j] = f.exec(m.data[i][j]);
		}
		return tmp;	
	}
}