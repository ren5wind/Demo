package com.example.administrator.demo;

import java.util.Locale;

import android.content.Context;
import android.media.AudioManager;
import android.speech.tts.TextToSpeech;
import android.widget.Toast;
 
public class TextSpeaker {	
	public static int errorCount=0;
    private Context context;
    private TextToSpeech tts;
    public boolean isInited = false;
        public TextSpeaker(final Context context) {        
    	this.context = context;
        tts = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            
        	@Override
            public void onInit(int status) {
                // TODO Auto-generated method stub
                if (status == TextToSpeech.SUCCESS)
                {
                    int result = tts.setLanguage(Locale.CHINA);
                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED)
                    {
                        Toast.makeText(context, "系统语音包不支持中文，请安装语音包...",
                                Toast.LENGTH_SHORT).show();
                    }
                    isInited = true;
                }
                System.out.println(status);
            }
        });
    }
    
    public boolean isSpeaking_(){
    	return tts.isSpeaking();
    }
    public void speak(String text) {    	
    	int res=tts.speak(text, TextToSpeech.QUEUE_ADD, null);
        
        if(res!=0){
            tts.speak("错误语音", TextToSpeech.QUEUE_ADD, null);
        	errorCount++;
    	}        
    }
   public  boolean isSpeaking() {
        final AudioManager am = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
        if (am == null) {
           
            return false;
        }
        return am.isMusicActive();//.isFmActive();
    }
}