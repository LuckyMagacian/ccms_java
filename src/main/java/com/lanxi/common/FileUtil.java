package com.lanxi.common;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1 on 2016/11/9.
 */
public class FileUtil {
    public static  File[] getFiles(String dirPath,String fileFormat){
        File[] files=null;
        File file=new File(dirPath);
        if(file.isDirectory()){
            files=file!=null&&file.exists()?file.listFiles():null;
            if(null==fileFormat)
                return files;
            else{
                if(files!=null){
                    List<File> tempList=new ArrayList<>();
                    for(File each:files)
                        if(each.getName().endsWith(fileFormat))
                            tempList.add(each);
                    return tempList.toArray(new File[tempList.size()]);
                }
            }
        }
		return files;
    }


}
