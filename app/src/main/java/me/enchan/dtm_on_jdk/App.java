package me.enchan.dtm_on_jdk;

import java.util.Scanner;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.ShortMessage;

public class App {

    private void run() throws Exception {
        // シンセサイザを取得し、開く
        final var synthesizer = MidiSystem.getSynthesizer();
        synthesizer.open();

        // レシーバを取得
        final var receiver = synthesizer.getReceiver();

        // メッセージを生成
        final int keyCode = 69; // A3
        final int velocity = 127; // 最大値
        final var noteOnMessage = new ShortMessage(ShortMessage.NOTE_ON, keyCode, velocity);
        final var noteOffMessage = new ShortMessage(ShortMessage.NOTE_OFF, keyCode, velocity);

        // レシーバに流し込む
        receiver.send(noteOnMessage, 0);
        receiver.send(noteOffMessage, 2_000_000);

        // Enterキーが押されるまで待機する
        System.out.println("type Enter key to exit");
        final var scanner = new Scanner(System.in);
        scanner.nextLine();
        scanner.close();

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
