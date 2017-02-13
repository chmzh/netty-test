package com.cmz.proto;

import com.google.protobuf.GeneratedMessage;

import protobuf.clazz.Protocol;

public enum CmdMapping {
	
	Login1(10001,null);
	
	private int cmdid;
	private GeneratedMessage msg;
	private CmdMapping(int cmdid,GeneratedMessage msg) {
		this.cmdid = cmdid;
		this.msg = msg;
	}
}
