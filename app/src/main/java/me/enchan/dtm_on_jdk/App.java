package me.enchan.dtm_on_jdk;

import java.util.Scanner;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.ShortMessage;

public class App {

    private void run() throws Exception {
        // シンセサイザを取得し、開く
        final var synthesizer = MidiSystem.getSynthesizer();
        synthesizer.open();

        // チャンネル0を取得
        final var channel = synthesizer.getChannels()[0];
        channel.programChange(25);

        // チャンネル0の発声を開始
        final int velocity = 127; // 最大値
        channel.noteOn(60, velocity); // C3
        channel.noteOn(64, velocity); // E3
        channel.noteOn(67, velocity); // G3

        // Enterキーが押されるまで待機する
        System.out.println("type Enter key to exit");
        final var scanner = new Scanner(System.in);
        scanner.nextLine();
        scanner.close();

        // 発声を終了
        channel.allNotesOff();

        // 後処理
        synthesizer.close();
    }

    public static void main(String[] args) {
        App app = new App();
        try {
            app.run();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
