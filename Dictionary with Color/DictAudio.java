package uet.oop.ourtreedictionary;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
public class DictAudio {

    Voice voice;

    public DictAudio() {
        // Sử dụng giọng nữ "kevin16" (bạn có thể thay đổi thành giọng khác nếu muốn)
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
            // Đặt tốc độ nói chậm hơn (giảm giá trị để làm chậm hơn)
            voice.setRate(100);
            voice.setPitch(100);
            voice.setVolume(10);
            voice.speak(word);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
