package com.neural;

import com.math.*;

public class MyActivationFunction{
	protected String type;
	protected MyFunction myFun;
	protected MyFunction myFunDiff;
	public MyActivationFunction(){
		this.type = "sigmoid";
		this.myFun = new MyFunction(){
			public double exec(double x){
				return sigmoid(x,false);
			}
		};
		this.myFunDiff = new MyFunction(){
			public double exec(double x){
				return sigmoid(x,true);
			}
		};
	}
	public MyActivationFunction(String type){
		this.type = type;
		if(this.type.equals("relu")){
			this.myFun = new MyFunction(){
				public double exec(double x){
					return relu(x,false);
				}
			};
			this.myFunDiff = new MyFunction(){
				public double exec(double x){
					return relu(x,true);
				}
			};
		}
		else if(this.type.equals("tanh")){
			this.myFun = new MyFunction(){
				public double exec(double x){
					return tanh(x,false);
				}
			};
			this.myFunDiff = new MyFunction(){
				public double exec(double x){
					return tanh(x,true);
				}
			};
		}
		else if(this.type.equals("linear")){
			this.myFun = new MyFunction(){
				public double exec(double x){
					return linear(x,false);
				}
			};
			this.myFunDiff = new MyFunction(){
				public double exec(double x){
					return linear(x,true);
				}
			};
		}
		else{
			this.myFun = new MyFunction(){
				public double exec(double x){
					return sigmoid(x,false);
				}
			};
			this.myFunDiff = new MyFunction(){
				public double exec(double x){
					return sigmoid(x,true);
				}
			};
		}
	}
	public MyMatrix exec(MyMatrix m,boolean op){
		if(op)
			return MyMatrix.applyFun(this.myFunDiff,m);
		else
			return MyMatrix.applyFun(this.myFun,m);
	}

	public double sigmoid(double x,boolean diff){
		if(!diff)
			return 1/(1 + Math.exp(-x));
		return x*(1-x);
	}
	public double tanh(double x,boolean diff){
		if(!diff)
			return (1 - Math.exp(-x))/(1 + Math.exp(-x));
		return (1-Math.pow(x,2))/2.0;
	}
	public double relu(double x,boolean diff){
		if(!diff)
			return x>0?x: 0.01*x;
		return x>0? 1 :0.01;
	}
	public double linear(double x,boolean diff){
		if(!diff)
			return x;
		return 1;
	}
}