package com.neural;

import com.math.*;

public class MyLayer{
	protected int nInputs;
	protected int nOutputs;
	protected MyMatrix w;
	protected MyMatrix bias;
	protected MyMatrix input;
	protected MyMatrix output;
	protected MyMatrix delta;
	protected MyActivationFunction actFun;
	private final MyMatrix ones;
	private MyMatrix one;

	public MyLayer(int i,int o){
		this.nInputs = i;
		this.nOutputs = o;
		this.w = new MyMatrix(i,o);
		this.w.random();
		this.bias = new MyMatrix(1,o);
		this.bias.random();
		this.ones = new MyMatrix(i,o);
		this.ones.ones();
		this.actFun = new MyActivationFunction();
	}
	public MyLayer(int i,int o,String type){
		this.nInputs = i;
		this.nOutputs = o;
		this.w = new MyMatrix(i,o);
		this.w.random();
		this.bias = new MyMatrix(1,o);
		this.bias.random();
		this.ones = new MyMatrix(i,o);
		this.ones.ones();
		this.actFun = new MyActivationFunction(type);
	}
	public MyMatrix forward(MyMatrix inputData){
		this.input = inputData;
		this.one = new MyMatrix(inputData.getRow(),1);
		this.one.ones();
		this.output = actFun.exec(inputData.dot(this.w).sum(this.one.dot(this.bias)),false);
		return this.output;
	}
	public MyMatrix backward(MyMatrix err){
		this.delta = actFun.exec(this.output,true).times(err);
		return this.delta.dot(this.w.transpose());
	}
	public void update(){
		this.w = this.w.diff(this.input.transpose().dot(this.delta));
		this.bias = this.bias.diff(this.one.transpose().dot(this.delta));
	}
	public void update(double alpha){
		this.w = this.w.diff(this.input.transpose().dot(this.delta).times(alpha));
		this.bias = this.bias.diff(this.one.transpose().dot(this.delta).times(alpha));
	}
}