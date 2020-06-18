package com.boot.tools;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

import java.io.IOException;

public class staticTools {

    /**【语音播报方法】**/
    public static boolean speakingText(String readText) throws IOException {
        boolean isFinish = true;
        //ExceptionLog log = new ExceptionLog();
        ActiveXComponent sap = new ActiveXComponent("Sapi.SpVoice");
        try {
            //sap = new ActiveXComponent("Sapi.SpVoice");
            sap.setProperty("Volume",new Variant(100));              // 音量 0-100
            sap.setProperty("Rate",new Variant(-3));                 // 语音朗读速度 -10 到 +10
            Dispatch saps = sap.getObject();                            // 获取执行对象
            Dispatch.call(saps,"Speak",new Variant(readText));    // 执行朗读
            saps.safeRelease();                                         // 关闭执行对象
        }catch (Exception e){
            isFinish = false;
            //log.writeFile(e.getMessage());
            e.printStackTrace();
        }finally {
            if (sap != null){
                sap.safeRelease();                                      // 关闭执行对象
            }
        }
        return isFinish;
    }

}
