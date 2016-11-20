package com.scht.util;

import com.scht.admin.bean.Bank;
import com.scht.common.ConfigHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/13.
 */
public class BankUtil {

    public static List<Bank> loadBanks(){
        try {
            List<Bank> list = new ArrayList<>();
            String banks = ConfigHelper.GetInstance().GetConfig("Banks");
            String[] arrays = banks.split(",");
            for (String key : arrays) {
                String[] values = key.split("-");
                Bank bank = new Bank();
                bank.setId(values[0]);
                bank.setName(values[1]);
                list.add(bank);
            }
            return list;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static String getBankNameById(String id){
        try {
            String name = "";
            String banks = ConfigHelper.GetInstance().GetConfig("Banks");
            String[] arrays = banks.split(",");
            for (String key : arrays) {
                String[] values = key.split("-");
                if(values[0].equals(id)){
                    name = values[1];
                    break;
                }
            }
            return name;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    public static void main(String[] args){
        List list = loadBanks();
        System.out.println(list);
        System.out.println(getBankNameById("1"));
    }
}
