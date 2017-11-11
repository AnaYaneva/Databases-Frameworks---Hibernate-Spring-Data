package orm.scanner;

import annotations.Entity;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EntityScanner {
    public static List<Class> getAllEntities(String startPath) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        List<Class> result=new ArrayList<>();
        File dir=new File(startPath);
        List<File> filesList=new ArrayList<>();
        //filesList.addAll(Arrays.asList(dir.listFiles()));

        for(File file:filesList){
            if (file.isFile()){
                String fileName=file.getName();
                if (!fileName.endsWith(".class")){
                    continue;
                }

                Class myClass=Class.forName(fileName.substring(0,fileName.length()-6));
                myClass.newInstance();
                Constructor[] cons=myClass.getConstructors();

                if (cons.length==0){
                    continue;
                }

                Constructor c=cons[0];
                Object o=c.newInstance();

                if (!o.getClass().isAnnotationPresent(Entity.class)){
                    continue;
                }
                result.add(o.getClass());
            }else{
                result.addAll(getAllEntities(startPath+File.separator+file.getName()));
            }
        }

        return result;
    }
}
