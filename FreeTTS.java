package com.example.dictionary;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
public class FreeTTS {

    Voice voice;

    public FreeTTS() {

        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        voice = VoiceManager.getInstance().getVoice("kevin16");

        if (voice != null) {
            voice.allocate();
        } else {
            throw new IllegalStateException("Cannot find voice: kevin16");
        }
    }

    public void play(String word) {
        try {
            voice.setRate(100);
            voice.setPitch(100);
            voice.setVolume(10);
            voice.speak(word);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
