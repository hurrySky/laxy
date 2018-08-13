package com.sbs.common.exception;

import org.springframework.beans.factory.annotation.Autowired;

import com.sbs.system.user.entity.User;
import com.sbs.system.user.service.UserService;
import com.sbs.system.user.service.UserServiceImpl;

public class BaseExceptionTest {
	
	BaseExceptionTest b = new BaseExceptionTest();
	public static UserService userService;
	
	public static void main(String[] args) {
		Cat c1=null;
		int age = -1;
		try{
	        c1=new Cat("小喵",-2,true);
	    }catch(BaseException e){
	            System.out.println(e.toString());
	        }
	
	}
	
}

class Cat{
    
    private String name;
    private int age;
    private boolean SEX;
    public Cat() {
        
    }
    public Cat(String name, int age, boolean sEX) {
        this.name = name;
        if(age<0){
            throw new BaseException(null,"给定的年龄"+age+"是非法的！");
        }
        this.age = age;
        SEX = sEX;
    }
    public String toString(){
        return " "+name+" "+age+" "+(SEX?"公":"母");
    }
}