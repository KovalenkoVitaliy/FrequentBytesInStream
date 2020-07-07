import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class FrequentBytes {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String namefile = reader.readLine();
        FileInputStream filereader = new FileInputStream(namefile);
        ArrayList<Integer> arrlist = new ArrayList<>();
        int bytenow =0;

        while (filereader.available() > 0) {
            bytenow = filereader.read();
            arrlist.add(bytenow);
        }

        filereader.close();
        reader.close();

        Collections.sort(arrlist);


        HashMap<Integer, Integer> map = new HashMap<>();
        int count =1;
        for(int i=0; i<arrlist.size()-1; i++) {
            if (arrlist.get(i) == arrlist.get(i+1) && i!=arrlist.size()-1-1) {
                count++;
            } else if (arrlist.get(i) == arrlist.get(i+1) && i==arrlist.size()-1-1) {
                count++;
                map.put(arrlist.get(i), count);
                count = 1;
            } else if (arrlist.get(i)!=arrlist.get(i+1) && i==arrlist.size()-1-1 && arrlist.get(i)==arrlist.get(i-1)){
                count=1;
                map.put(arrlist.get(i+1), count);
            } else if (arrlist.get(i)!=arrlist.get(i+1) && i==arrlist.size()-1-1 && arrlist.get(i)!=arrlist.get(i-1)) {
                count=1;
                map.put(arrlist.get(i+1), count);
                map.put(arrlist.get(i), count);
            } else {
                map.put(arrlist.get(i), count);
                count = 1;
            }
        }

        int bytecur = 0;
        int povtorbytecur =0;

        Iterator<Map.Entry<Integer, Integer>> x = map.entrySet().iterator();
        Map.Entry<Integer, Integer> pair = x.next();
        int bytemax = pair.getKey();
        int povtorbytemax = pair.getValue();

        for (Map.Entry<Integer, Integer> y: map.entrySet()) {
            bytecur = y.getKey();
            povtorbytecur = y.getValue();
            if (povtorbytecur>= povtorbytemax) {
                bytemax =  bytecur;
                povtorbytemax = povtorbytecur;
            }
        }

        int value = 0;
        int key = 0;
        for (Map.Entry<Integer, Integer> y: map.entrySet()) {
            value = y.getValue();
            key = y.getKey();
            if (povtorbytemax == value) {
                System.out.print(key + " ");
            }
        }
    }
}

