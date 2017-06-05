package com.id.sima.modul.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.id.sima.core.controller.CoreAction;
import com.id.sima.modul.model.Barang;
import com.id.sima.modul.service.BarangDao;

public class BarangController extends CoreAction{

	private static final long serialVersionUID = 1L;
	private Barang barang;
	private List<Barang> listBarang = new ArrayList<Barang>();
	private BarangDao barangDao = (BarangDao) new ClassPathXmlApplicationContext("config-db.xml").getBean("barangDao");
	
	public String searchAllBarang(){
		System.out.println("jalankan method serachAllBarang");
		
		/*if(barang.getNamaBarang().equalsIgnoreCase("")){
			barang = new Barang();
			barang.setNamaBarang("");
		}
		*/
		listBarang = barangDao.selectAllRecord(barang);
		return SUCCESS;
	}
	
	public String searchWhereKode(){
		System.out.println("jalankan method searchWhereKode");
		
		barang = barangDao.selectWhereKode(barang);		
		return SUCCESS;
	}
	
	public String searchKodeToJson(){
		System.out.println("jalankan method searchKode");
		
		barang = barangDao.selectWhereKode(barang);
		return SUCCESS;
	}
	
	public String searchList(){
		System.out.println("jalankan method searchList");
		
		listBarang = barangDao.selectList();
		return SUCCESS;
	}
	
	public String addBarang(){
		System.out.println("jalankan method addBarang");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String d[] = sdf.format(new Date()).split("/"),date="";
		date+=d[0]+d[1]+d[2];
		int x = barangDao.count(barang);
		x++;
		
		if(x <99 && x>=10){
			date+="00"+x;
		}else if(x>99 && x<1000){
			date+="0"+x;
		}else if(x>=999){
			date+=x;
		}else{
			date+="000"+x;
		}
		
		try {
			barang.setKodeBarang(date);
			barangDao.insertRecord(barang);
		} catch (Exception e) {
			addFieldError("invaliBarang", "Gagal menambahkan data dikarenakan "+e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	public String editBarang(){
		System.out.println("jalankan method editBarang");
		
		try {
			barangDao.updateRecord(barang);
		} catch (Exception e) {
			addFieldError("invaliBarang", "Gagal merubah data dikarenakan "+e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	public String deletBarang(){
		System.out.println("jalankan method deletBarang");
		
		try {
			barangDao.deletRecord(barang);
		} catch (Exception e) {
			addFieldError("invaliBarang", "Gagal menghapus data dikarenakan "+e);
			return ERROR;
		}
		return SUCCESS;
	}
	public Barang getBarang() {
		return barang;
	}
	public void setBarang(Barang barang) {
		this.barang = barang;
	}
	public List<Barang> getListBarang() {
		return listBarang;
	}
	public void setListBarang(List<Barang> listBarang) {
		this.listBarang = listBarang;
	}
	public BarangDao getBarangDao() {
		return barangDao;
	}
	public void setBarangDao(BarangDao barangDao) {
		this.barangDao = barangDao;
	}
}