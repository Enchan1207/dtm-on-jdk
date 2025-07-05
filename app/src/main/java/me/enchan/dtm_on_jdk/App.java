package me.enchan.dtm_on_jdk;

import java.util.Arrays;
import java.util.Scanner;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;

public class App {

    private void run() throws Exception {
        final var synthesizer = MidiSystem.getSynthesizer();
        synthesizer.open();

        // シーケンサを取得して開く
        final var sequencer = MidiSystem.getSequencer();
        sequencer.open();

        // シーケンスを生成する
        final var sequence = new Sequence(Sequence.PPQ, 1, 1);

        // シーケンスにトラックを作成する
        final var track = sequence.createTrack();

        // イベントを詰める
        final int[] notes = { 60, 62, 64, 65, 67, 69, 71, 72 };
        for (int i = 0; i < notes.length; i++) {
            track.add(createNoteOnEvent(notes[i], 127, i));
            track.add(createNoteOffEvent(notes[i], i + 1));
        }

        // シーケンサにシーケンスを割り当てる
        sequencer.setSequence(sequence);

        // 設定
        sequencer.setTempoInBPM(120);
        sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);

        // 開始
        sequencer.start();

        // Enterキーが押されるまで待機する
        System.out.println("type Enter key to exit");
        final var scanner = new Scanner(System.in);
        scanner.nextLine();
        scanner.close();

        // 後処理
        sequencer.stop();
        sequencer.close();
        synthesizer.close();
    }

    private MidiEvent createNoteOnEvent(int note, int velocity, int tick) {
        try {
            final var message = new ShortMessage(ShortMessage.NOTE_ON, note, velocity);
            final var event = new MidiEvent(message, tick);
            return event;
        } catch (Exception e) {
            return null;
        }
    }

    private MidiEvent createNoteOffEvent(int note, int tick) {
        try {
            final var message = new ShortMessage(ShortMessage.NOTE_OFF, note, 0);
            final var event = new MidiEvent(message, tick);
            return event;
        } catch (Exception e) {
            return null;
        }
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
