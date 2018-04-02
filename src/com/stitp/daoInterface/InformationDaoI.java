package com.stitp.daoInterface;

import java.util.ArrayList;

import com.stitp.model.Information;

public interface InformationDaoI {
	
	void add(Information information);//������Ƹ��Ϣ
	
	void update(Information information);//������Ƹ��Ϣ
	
	void update(String nameOrID);//������Ƹ��Ϣ
	
	void delete();//ɾ��������Ƹ��Ϣ
	
	void delete(Information information);//ɾ��������Ƹ��Ϣ
	
	void delete(String nameOrId);//�������ֻ�IDɾ����Ƹ��Ϣ
	
	ArrayList< Information> query();//��ѯ������Ƹ��Ϣ
	
	Information query(String nameOrID);//�������ֻ�ID�ò�ѯ��Ƹ��Ϣ
	
	
}
