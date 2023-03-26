import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

public class MyClassLoader extends ClassLoader{
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException{
        File file = new File("c:/test/",name.replaceAll(".","/").concat(".class"));
        try{
            FileInputStream fileInputStream = new FileInputStream(file);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int b = 0;
            while ((b=fileInputStream.read())!=-1){
                byteArrayOutputStream.write(b);
            }
            byte[] bytes = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
            fileInputStream.close();
            return defineClass(name,bytes,0,bytes.length);
        }catch (Exception e){
            e.printStackTrace();
        }
        return super.findClass(name);
    }
}
