package com.cmz.proto;

import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.GeneratedMessage.GeneratedExtension;

import protobuf.clazz.Protocol;
import protobuf.clazz.Protocol.Request;

public enum CmdMapping {
	
	Login1(10001,Protocol.login);
	
	private int cmdid;
	private GeneratedExtension msg;
	private CmdMapping(int cmdid,GeneratedExtension msg) {
		this.cmdid = cmdid;
		this.msg = msg;
	}
	
	public static GeneratedExtension getByCmdId(int cmdid){
		GeneratedExtension obj = null;
		for (CmdMapping cmd:CmdMapping.values()) {
			if(cmd.cmdid == cmdid){
				obj = cmd.msg;
				break;
			}
		}
		return obj;
	}
}
