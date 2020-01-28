package com.neural;

import java.util.*;
import com.math.*;

public class MyNeuralNetwork{
	protected ArrayList<MyLayer> layers;
	public MyNeuralNetwork(){
		this.layers = new ArrayList<MyLayer>();
	}
	public void addLayer(int i,int o){
		this.layers.add(new MyLayer(i,o));
	}
	public void addLayer(int i,int o,String type){
		this.layers.add(new MyLayer(i,o,type));
	}
	public MyMatrix forward(MyMatrix inputData){
		MyMatrix tmp;
		tmp = inputData;
		for(MyLayer l : this.layers)
			tmp = l.forward(tmp);
		return tmp;
	}
	public void backward(MyMatrix err){
		MyMatrix tmp;
		tmp = err;
		for(int i=this.layers.size()-1;i>=0;i--)
			tmp = this.layers.get(i).backward(tmp);
	}
	public void update(){
		for(MyLayer l : this.layers)
			l.update();
	}
	public void update(double alpha){
		for(MyLayer l : this.layers)
			l.update(alpha);
	}
	public void train(MyMatrix inputData,MyMatrix outputData,int maxIt){
		MyMatrix tmp;
		for(int i = 0;i<maxIt;i++){
			tmp = this.forward(inputData);
			this.backward(tmp.diff(outputData));
			this.update();
		}
	}
	public void train(MyMatrix inputData,MyMatrix outputData,int maxIt,double alpha){
		MyMatrix tmp;
		for(int i = 0;i<maxIt;i++){
			tmp = this.forward(inputData);
			this.backward(tmp.diff(outputData));
			this.update(alpha);
		}
	}
}