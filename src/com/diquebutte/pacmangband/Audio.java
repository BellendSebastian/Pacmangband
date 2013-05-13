package com.diquebutte.pacmangband;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class Audio {
	public void playMp3(String filename) {
		try {
			InputStream in = getClass().getResourceAsStream(String.format("/assets/sound/%s.mp3", filename));
			Player player = new Player(in);
			player.play();
		} catch (JavaLayerException e) {
			e.printStackTrace();
		}
	}
	
	public void playMidi(String filename) {
		try {
			Sequence sequence = MidiSystem.getSequence(new File(String.format("assets/music/%s.mid", filename)));
			Sequencer sequencer = MidiSystem.getSequencer();
			sequencer.open();
			sequencer.setSequence(sequence);
			sequencer.start();
		} catch (MalformedURLException e) {
	    } catch (IOException e) {
	    } catch (MidiUnavailableException e) {
	    } catch (InvalidMidiDataException e) { }
	}
}
