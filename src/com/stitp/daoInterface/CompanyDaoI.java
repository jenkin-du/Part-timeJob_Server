package com.stitp.daoInterface;

import java.util.ArrayList;

import com.stitp.model.Company;

public interface CompanyDaoI {
	
    boolean add(Company company);//���ӹ�˾��Ϣ
    
    void update(Company company);//������Ϣ
    
    ArrayList<Company>  query();//��ѯ���
    
    Company query(String nameOrID); //����ID��ѯ��Ϣ

    void delete();//ɾ��������Ϣ
    
    void delete(Company company);//ɾ��������Ϣ
    
    void delete(String nameOrID);//�������ֻ�IDɾ����Ƹ��Ϣ
	
}
