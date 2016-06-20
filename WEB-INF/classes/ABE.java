package com;
import bswabe.Bswabe;
import bswabe.BswabeCph;
import bswabe.BswabeCphKey;
import bswabe.BswabeElementBoolean;
import bswabe.BswabeMsk;
import bswabe.BswabePrv;
import bswabe.BswabePub;
import bswabe.SerializeUtils;
public class ABE {
	static BswabePub public_key;
	static BswabePrv private_key;
public static void generateKey(String attr[])throws Exception{
	public_key = new BswabePub();
	BswabeMsk master_key = new BswabeMsk();
	Bswabe.setup(public_key,master_key);
	private_key = Bswabe.keygen(public_key,master_key,attr);
}
public static byte[] encrypt(String policy,BswabePub pub)throws Exception{
	BswabeCphKey crypted = Bswabe.enc(pub,policy);
	BswabeCph cph = crypted.cph;
	byte arr[] = SerializeUtils.bswabeCphSerialize(cph);
	return arr;
}
public static boolean decrypt(BswabePub pub,BswabePrv prv,byte arr[])throws Exception{
	boolean flag=false;
	BswabeCph cph = SerializeUtils.bswabeCphUnserialize(pub,arr);
	BswabeElementBoolean result = Bswabe.dec(pub,prv,cph);
	if(result.b == true)
		flag = true;
	return flag;
}
}
