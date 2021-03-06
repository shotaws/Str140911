import java.io.*;
import java.util.ArrayList;

public class Str140911 {

	public static boolean isNumber(String val) {
	    try {
	        Integer.parseInt(val);
	        return true;
	    } catch (NumberFormatException nfex) {
	        return false;
	    }
	}
	
	public static boolean isFloat(String val) {
	    try {
	        Float.parseFloat(val);
	        return true;
	    } catch (NumberFormatException nfex) {
	        return false;
	    }
	}
	
	//指定した目的地かどうか
	public static boolean isCheck(String val, ArrayList<Integer> array) {
		int check = 0;

	    try {
	        check = Integer.parseInt(val);
	    } catch (NumberFormatException nfex) {
	    }
	    
	    //線形探索
	    for(int c : array)
	    	if(check == c) return true;

		return false;
	}
	
	public static void Load_Write(BufferedReader br, BufferedWriter[] bw , int exist, ArrayList<Integer> array) {
		try {
	        String line;
	        String[] strAry;
	        
	        String tmp = null;
	        float tmpf = 0.0f;
	        int tmpi = 0, count = 0;
	        int i = 0, j = 0, flag = 0, flag2 = 0;
	        
	        while ((line = br.readLine()) != null) {
	            System.out.println(line);
	            strAry = line.split(" ");
	            for(String s : strAry){
	            	//System.out.println(s+isNumber(s));
	            	if(i < 2){
	        			bw[0].write(s + " ");
	        			//System.out.println(s);
	        			//あて先と途中の結果
	        			//項目に関してiは++されない
	            		if(isNumber(s)){
	            			i++;
	            			//指定した目的地か
	                  		if(i == 1 && exist==1 && isCheck(s, array))
	                  			flag2 = 1;
	            		}
	            		else if(isFloat(s))
	            			i++;
	
	            		if(i == 1){
	            			j++;                			
	            			
	            			//項目による分類
	            			if(j == 2){
	            				//一番最初の項目
	            				if(tmp == null)
	            					tmp = new String(s);
	            				//2番目以降
	            				else{
	            					//違う項目が来たらファイル書き込み
	            					if(!tmp.equals(s)){
	            						//平均(int)スループット
	            						if(flag == 1)
	            							bw[1].write(tmp + " " + (float)tmpi/count);
	            						//平均(float)遅延
	            						else
	            							bw[1].write(tmp + " " + tmpf/count);
	            		                bw[1].newLine();
	            		                bw[1].newLine();
	            		                
	            						tmp = new String(s);
	            						
	            						count = 0;
	            						tmpi = 0;
	            						tmpf = 0.0f;
	            						//flag=0;
	            					}
	            					flag = 0;
	            				}
	            			}
	            		}
	            		
	            		//if(!tmp.equals(s))に引っかからない，つまり同じ項目の間は足し続ける
	            		else{
	            			//sumスループット
	            			if(isNumber(s)){
	            				tmpi += Integer.parseInt(s);
	            				//System.out.println(tmpi);
	            				count++;
	            				flag = 1;
	            			}      
	            			//sum遅延
	            			else if(isFloat(s)){
	            				tmpf += Float.parseFloat(s);
	            				//System.out.println(tmpf);
	            				count++;
	            				flag = 2;
	            			} 
	            		}
	        		}
	            }
	            
	            //指定した目的地の行のみ出力
	  			if(exist == 1 && flag2 == 1){
					bw[2].write(line);
					bw[2].newLine();
				}
	            
	            flag2 = 0;
	            i = 0;
	            j = 0;
	            //for(int i = 0; i < 6; i++)
	            //	bw.write(strAry[i] + " ");
	            bw[0].newLine();
	
	            //break;
	        }
	        
	        //最後のを出力
	        if(flag == 1)
				bw[1].write(tmp + " " + (float)tmpi/count);
			else
				bw[1].write(tmp + " " + tmpf/count);
	        bw[1].newLine();
	        
		}catch (IOException e) {
            System.out.println(e);
        }
	}
	
	public static int m(String[] args, int mode) {
        int exist = 1;
        
        FileReader[] in = new FileReader[2];
        FileWriter[] out = new FileWriter[3];
        BufferedReader [] br = new BufferedReader[2];
        BufferedWriter[] bw = new BufferedWriter[3];
        
        String line;
        
        exist = mode;
        
        //System.out.println("exist"+exist);
        
		try {
			if(exist==1)
				in[0] = new FileReader(args[0]);
			else
				in[0] = new FileReader("ELOther.txt");
			
            in[1] = null;
            br[1] = null;

            //Numbers.txtがないとELOther.txtは生成されない
            try {
            	in[1] = new FileReader("Numbers.txt");
            	br[1] = new BufferedReader(in[1]);
            }catch(FileNotFoundException e){
            	exist = 0;
            }
            
            //System.out.println("sexist"+exist);
            
            //String[] strFile;
            //strFile = args[0].split("\\.");
            out[0] = new FileWriter("EL.txt"); //中間値を削ったもの
            out[1] = new FileWriter("AVE.txt"); //平均を出したもの
            out[2] = null;
            bw[2] = null;
            
            if(exist == 1){
            	out[2] = new FileWriter("ELOther.txt"); //指定した目的地以外を削ったもの
            	bw[2] = new BufferedWriter(out[2]);
            }
            
            //動かない（exeでは生成されない）
            //FileWriter out = new FileWriter("EL_" + strFile[0] + ".txt");
            //FileWriter out2 = new FileWriter("AVE_" + strFile[0] + ".txt");
            br[0] = new BufferedReader(in[0]);
            bw[0] = new BufferedWriter(out[0]);
            bw[1] = new BufferedWriter(out[1]);
                        
            // 目的地を振り分けるための配列
            ArrayList<Integer> array = null;
            if(exist == 1){
            	array = new ArrayList<Integer>();
            	//array.add(1);//送信元のノードID
	            while ((line = br[1].readLine()) != null)
	            	array.add(Integer.parseInt(line));
	            
			    for(int c : array)
			    	System.out.println(c);
            }
            
            Load_Write(br[0], bw, exist, array);
            
            bw[0].flush();
            bw[1].flush();
            br[0].close();
            in[0].close();
            out[0].close();
            out[1].close();
            
            if(exist == 1){
                br[1].close();
                in[1].close();
            	bw[2].flush();
            	out[2].close();
            }
            
        } catch (IOException e) {
            System.out.println(e);
        }
		
		return exist;
	}
	
	public static void main(String[] args){
		int exist;
		
		exist=m(args, 1);
		
		if(exist == 1)
			m(args, exist + 1);
	}
}
