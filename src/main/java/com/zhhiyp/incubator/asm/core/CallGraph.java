package com.zhhiyp.incubator.asm.core;

import jdk.internal.org.objectweb.asm.tree.ClassNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhiyp
 * @date 2018/10/10 0010 19:59
 * 储存方法调用关系
 */
public class CallGraph {
	/**
	 * className <methodSignature,node>
	 * methodSignature = methodName + # + desc
	 */
	private Map<String, Map<String,CallMethodNode>> invokeMap = new HashMap<>();
	private Map<String, ClassNode> classMap = new HashMap<>();
	private Map<String, Map<String,CallMethodNode>> virtualInvokeMap = new HashMap<>();

	private static CallGraph callGraph = new CallGraph();

	private CallGraph(){}

	public static CallGraph getInstance(){
		return callGraph;
	}

	public  Map<String, Map<String, CallMethodNode>> getInvokeMap() {
		return invokeMap;
	}

	public void putClass(String className){
		invokeMap.put(className,new HashMap<>());
	}

	public void putMethod(String className,String methodSig,CallMethodNode methodNode){
		if(!invokeMap.containsKey(className)){
			putClass(className);
		}
		invokeMap.get(className).put(methodSig,methodNode);
	}

	public void putVirtualMethod(String className,String methodSig,CallMethodNode methodNode){
		if(!virtualInvokeMap.containsKey(className)){
			virtualInvokeMap.put(className,new HashMap<>());
		}
		virtualInvokeMap.get(className).put(methodSig,methodNode);
	}

	public void putClasSNode(String className,ClassNode classNode){
		classMap.put(className,classNode);
	}

	public ClassNode getClassNode(String className){
		return classMap.get(className);
	}
}
