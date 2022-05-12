package Tiger2022.offer;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class readFile {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //String str = reader.readLine();
        //System.out.println(str);
        try {
            //统计E盘下面test.txt中的q字符出现的次数
            System.out.println("E盘下面test.txt中的q字符出现的次数为：");
            count("E:\\test.txt", "信息");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void count(String filename, String target) throws FileNotFoundException, IOException {
        FileReader reader = new FileReader(filename);
        BufferedReader br = new BufferedReader(reader);
        //BufferedWriter bw = new BufferedWriter(new FileWriter("aaa.txt"));
        int line = 1;
        while (br.ready()) {
            String s = br.readLine();
            if (s.indexOf(target) > 0) {
                System.out.println("第" + line + "行" + "---->" + s);
//                bw.write(s);
//                bw.newLine();
            }
            ++line;
        }
        br.close();
        reader.close();
        //bw.close();
    }

    public static void count2(String filename, String target) throws FileNotFoundException, IOException {

        // 读取文件内容到Stream流中，按行读取
        Stream<String> lines = Files.lines(Paths.get(filename));

        // 随机行顺序进行数据处理
        lines.forEach(s -> {
            if (s.indexOf(target) > 0) {
                System.out.println(s);
//                bw.write(s);
//                bw.newLine();
            }
        });
    }
}
