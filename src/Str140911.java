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
	
	//�w�肵���ړI�n���ǂ���
	public static boolean isCheck(String val, ArrayList<Integer> array) {
		int check = 0;

	    try {
	        check = Integer.parseInt(val);
	    } catch (NumberFormatException nfex) {
	    }
	    
	    //���`�T��
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
	        			//���Đ�Ɠr���̌���
	        			//���ڂɊւ���i��++����Ȃ�
	            		if(isNumber(s)){
	            			i++;
	            			//�w�肵���ړI�n��
	                  		if(i == 1 && exist==1 && isCheck(s, array))
	                  			flag2 = 1;
	            		}
	            		else if(isFloat(s))
	            			i++;
	
	            		if(i == 1){
	            			j++;                			
	            			
	            			//���ڂɂ�镪��
	            			if(j == 2){
	            				//��ԍŏ��̍���
	            				if(tmp == null)
	            					tmp = new String(s);
	            				//2�Ԗڈȍ~
	            				else{
	            					//�Ⴄ���ڂ�������t�@�C����������
	            					if(!tmp.equals(s)){
	            						//����(int)�X���[�v�b�g
	            						if(flag == 1)
	            							bw[1].write(tmp + " " + (float)tmpi/count);
	            						//����(float)�x��
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
	            		
	            		//if(!tmp.equals(s))�Ɉ���������Ȃ��C�܂蓯�����ڂ̊Ԃ͑���������
	            		else{
	            			//sum�X���[�v�b�g
	            			if(isNumber(s)){
	            				tmpi += Integer.parseInt(s);
	            				//System.out.println(tmpi);
	            				count++;
	            				flag = 1;
	            			}      
	            			//sum�x��
	            			else if(isFloat(s)){
	            				tmpf += Float.parseFloat(s);
	            				//System.out.println(tmpf);
	            				count++;
	            				flag = 2;
	            			} 
	            		}
	        		}
	            }
	            
	            //�w�肵���ړI�n�̍s�̂ݏo��
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
	        
	        //�Ō�̂��o��
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

            //Numbers.txt���Ȃ���ELOther.txt�͐�������Ȃ�
            try {
            	in[1] = new FileReader("Numbers.txt");
            	br[1] = new BufferedReader(in[1]);
            }catch(FileNotFoundException e){
            	exist = 0;
            }
            
            //System.out.println("sexist"+exist);
            
            //String[] strFile;
            //strFile = args[0].split("\\.");
            out[0] = new FileWriter("EL.txt"); //���Ԓl�����������
            out[1] = new FileWriter("AVE.txt"); //���ς��o��������
            out[2] = null;
            bw[2] = null;
            
            if(exist == 1){
            	out[2] = new FileWriter("ELOther.txt"); //�w�肵���ړI�n�ȊO�����������
            	bw[2] = new BufferedWriter(out[2]);
            }
            
            //�����Ȃ��iexe�ł͐�������Ȃ��j
            //FileWriter out = new FileWriter("EL_" + strFile[0] + ".txt");
            //FileWriter out2 = new FileWriter("AVE_" + strFile[0] + ".txt");
            br[0] = new BufferedReader(in[0]);
            bw[0] = new BufferedWriter(out[0]);
            bw[1] = new BufferedWriter(out[1]);
                        
            // �ړI�n��U�蕪���邽�߂̔z��
            ArrayList<Integer> array = null;
            if(exist == 1){
            	array = new ArrayList<Integer>();
            	//array.add(1);//���M���̃m�[�hID
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