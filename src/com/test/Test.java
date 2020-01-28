package com.test;

import com.neural.*;
import com.math.*;

public class Test{
	public static void main(String args[]){
		MyNeuralNetwork nn = new MyNeuralNetwork();
		MyMatrix input = new MyMatrix(4,2);
		MyMatrix output = new MyMatrix(4,1);
		double[][] din = {{-1,-1},{-1,1},{1,-1},{1,1}};
		double[][] dout = {{3},{1},{1},{-5}};
		input.setData(din);
		output.setData(dout);
		
		nn.addLayer(2,5,"relu");
		nn.addLayer(5,1,"linear");

		nn.train(input,output,3000,0.1);

		System.out.println("" + nn.forward(input));
	}
}