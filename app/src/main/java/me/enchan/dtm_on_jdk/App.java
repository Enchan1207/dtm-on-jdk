package me.enchan.dtm_on_jdk;

import javax.sound.midi.MidiSystem;

public class App {

    private void run() throws Exception {
        final var synthesizer = MidiSystem.getSynthesizer();

        final var deviceInfo = synthesizer.getDeviceInfo();
        final var name = deviceInfo.getName();
        final var description = deviceInfo.getDescription();

        System.out.println("name       : " + name);
        System.out.println("description: " + description);
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
