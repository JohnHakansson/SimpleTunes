package simpleTunes;

import java.io.Serializable;

public class RemoveShapeMessage implements Serializable{
	private static final long serialVersionUID = 6117382832070187915L;
	private MusicShapeMessage MusicShapeMessage;
	
	public RemoveShapeMessage(MusicShapeMessage MusicShapeMessage) {
		this.MusicShapeMessage = MusicShapeMessage;
	}
	
	public MusicShapeMessage getShape() {
		return MusicShapeMessage;
	}
}
