import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiUnavailableException;


public class NotePlayer {
	public static final int VELOCITY = 64;
	public static final int MIDI_CHANNEL = 0;
	public static final int INSTRUMENT = 25;
	
	private Synthesizer synth;
	
	public NotePlayer() {
		try {
			this.synth = MidiSystem.getSynthesizer();
			MidiChannel[] channels = synth.getChannels();
			MidiChannel channel = channels[NotePlayer.MIDI_CHANNEL];
			channel.programChange(INSTRUMENT);
		} catch (MidiUnavailableException e) {
			System.out.println("Failed to set up Synthesizer");
		}
		
	}
	
	public void playNote(int noteNum, int duration) {
		try {
			synth.open();
		} catch (MidiUnavailableException e){
			System.out.println("Could not open Synthesizer");
		}
		
		MidiChannel[] channels = synth.getChannels();
		MidiChannel channel = channels[NotePlayer.MIDI_CHANNEL];
		channel.noteOn(noteNum, NotePlayer.VELOCITY);
		
		try
		{
			Thread.sleep(duration);
		}
		catch (InterruptedException e){}
		//channel.noteOff(noteNum);
		synth.close();
	}
		
	public static void main(String[] args) {
		NotePlayer player = new NotePlayer();
		for (int i = 60; i < 127; i++) {
			player.playNote(i,200);
			
		}
		
//		try {
//			Thread.sleep(5);
//		}
//		catch (InterruptedException e){}
	}


}
